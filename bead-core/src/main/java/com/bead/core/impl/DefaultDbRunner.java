package com.bead.core.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bead.common.Configuration;
import com.bead.common.EngineProtocols;
import com.bead.common.Inject;
import com.bead.core.DbRunner;

public class DefaultDbRunner implements DbRunner {
	
	private ExecutorService executors = Executors.newCachedThreadPool();
	
	@Inject 
	private ProtocolBasedEngineFactory factory;
	
	@Inject
	private Configuration config;

	@Override
	public void run() {
		for(EngineProtocols engine : config.getEnginesToStart()) {
			executors.submit(() ->	{
				factory.getEngineByProtocol(engine).start();
				return null;
			});	
		}
	}

}
