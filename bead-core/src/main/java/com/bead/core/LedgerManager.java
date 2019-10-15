package com.bead.core;

public interface LedgerManager {
	
	public Ledger<?> getMasterLedger();
	
	public Ledger<?> createLedger();
 
}