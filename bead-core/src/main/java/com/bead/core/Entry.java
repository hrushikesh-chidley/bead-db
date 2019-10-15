package com.bead.core;

import java.io.Serializable;
import java.time.Instant;

public interface Entry extends Serializable {
	
	public long serialNumber();
	
	public Instant createdAt();
	
	public EntryTypes type();
	
	public String hash();
	
	public AbstractEntry getNext();

}
