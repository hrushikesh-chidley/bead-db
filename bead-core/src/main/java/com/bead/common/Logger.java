package com.bead.common;

public final class Logger {
	
	private Logger(final Class<?> className) {
		super();
	}
	
	public static synchronized final Logger getLooger(final Class<?> className) {
		return new Logger(className);
	}
	
	public final void debug(final String msg) {
	}

}
