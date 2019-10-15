package com.bead.core;

import java.time.Instant;

public abstract class AbstractEntry implements Entry {
	
	private static final long serialVersionUID = -4161463837850915512L;

	private long serialNumber;
	private Instant createdAt;
	private EntryTypes type;
	
	private String hash;
	
	private AbstractEntry next;

	@Override
	public final long serialNumber() {
		return serialNumber;
	}

	@Override
	public final Instant createdAt() {
		return createdAt;
	}

	@Override
	public final EntryTypes type() {
		return type;
	}
	
	@Override
	public final String hash() {
		return hash;
	}
	
	@Override
	public final AbstractEntry getNext() {
		return next;
	}

	public final void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public final void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public final void setType(EntryTypes type) {
		this.type = type;
	}

	public final void setHash(String hash) {
		this.hash = hash;
	}
}
