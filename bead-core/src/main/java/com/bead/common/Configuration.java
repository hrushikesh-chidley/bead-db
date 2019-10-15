package com.bead.common;

public class Configuration {
	
	private EngineProtocols [] engines = new EngineProtocols[] {EngineProtocols.HTTP, EngineProtocols.TCP};
	
	public Configuration() {
		super();
	}
	
	public final EngineProtocols [] getEnginesToStart() {
		return engines;
	}
	
	

}
