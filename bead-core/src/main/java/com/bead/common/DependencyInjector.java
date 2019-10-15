package com.bead.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;

public class DependencyInjector {

	private static final DependencyInjector injector = new DependencyInjector();

	private Map<String, Object> objectMap = new HashMap<>(37);

	private DependencyInjector() {
		super();
	}

	public static DependencyInjector getInstance() {
		return injector;
	}

	public final void inject() {
		final ClassLoader classLoader = this.getClass().getClassLoader();
		final Reflections reflections = new Reflections("com.bead", new FieldAnnotationsScanner(), new SubTypesScanner());
		final Set<Field> fieldsToBeInjected = reflections.getFieldsAnnotatedWith(Inject.class);
		fieldsToBeInjected.forEach(field -> {
			try {
				Object declaringInstance = getInstanceOfClass(field.getDeclaringClass(), classLoader);
				field.setAccessible(true);
				field.set(declaringInstance, getInstanceOfClass(getClass(field.getType(), reflections), classLoader));
			} catch (IllegalArgumentException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
				//do nothing
				e.printStackTrace();
			}
		});
	}
	
	private Class<?> getClass( final Class<?> cls, final Reflections reflections) {
		if(cls.isInterface()) {
			final Set<?> implementations = reflections.getSubTypesOf(cls);
			if(implementations.size() != 1) {
				throw new IllegalStateException("Could not inject "+cls.getName()+". None/multiple implementations found!");
			}
			return (Class<?>)implementations.iterator().next();
		}
		return cls;
	}

	private synchronized Object getInstanceOfClass(final Class<?> cls, final ClassLoader classLoader)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		final String className = cls.getName();
		final Object object = objectMap.get(className);
		if (object != null) {
			return object;
		}
		Object newObject = classLoader.loadClass(className).newInstance();
		objectMap.put(className, newObject);
		return newObject;
	}
	
	@SuppressWarnings("unchecked")
	public final <T> T getBean(final Class<T> cls) {
		return (T)objectMap.get(cls.getName());
	}

}
