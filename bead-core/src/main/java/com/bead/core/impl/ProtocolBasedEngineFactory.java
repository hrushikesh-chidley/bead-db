package com.bead.core.impl;

import com.bead.common.EngineProtocols;
import com.bead.common.Inject;
import com.bead.core.ProtocolBasedEngine;

public class ProtocolBasedEngineFactory {
	
	@Inject
	private DefaultHttpEngine httpEngine;
	
	@Inject
	private DefaultTcpEngine tcpEngine;
	
	public final ProtocolBasedEngine getEngineByProtocol(EngineProtocols protocol) {
		switch(protocol) {
		case HTTP :
			return httpEngine;
		default : 
			return tcpEngine;
		}
	}

}
