package com.bead.core.impl;

public class LoginRequest {

	private String ledgerName;
	private String userName;
	private String passwordHash;
	
	public final String getLedgerName() {
		return ledgerName;
	}
	public final String getUserName() {
		return userName;
	}
	public final String getPasswordHash() {
		return passwordHash;
	}
	public final void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}
	public final void setUserName(String userName) {
		this.userName = userName;
	}
	public final void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public String toString() {
		return "LedgerName : "+ledgerName+", UserName : "+userName;
	}
	
}
