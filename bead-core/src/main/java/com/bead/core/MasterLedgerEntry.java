package com.bead.core;

public class MasterLedgerEntry extends AbstractEntry {

	private static final long serialVersionUID = -234991134397070389L;

	private String ledgerName;
	private String username;
	private String saltedPasswordHash;
	private String salt;
	
	public final String getLedgerName() {
		return ledgerName;
	}
	public final String getUsername() {
		return username;
	}
	public final String getSaltedPasswordHash() {
		return saltedPasswordHash;
	}
	public final String getSalt() {
		return salt;
	}
	public final void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}
	public final void setUsername(String username) {
		this.username = username;
	}
	public final void setSaltedPasswordHash(String saltedPasswordHash) {
		this.saltedPasswordHash = saltedPasswordHash;
	}
	public final void setSalt(String salt) {
		this.salt = salt;
	}
	
}
