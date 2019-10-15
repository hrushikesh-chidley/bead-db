package com.bead.core;

import com.bead.common.Throttlings;

public interface ProtocolBasedEngine {

	void start() throws Exception;
	
	void stop();
	
	void throttle(Throttlings throttling);
	
}
