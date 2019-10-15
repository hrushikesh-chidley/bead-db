package com.bead.core;

import java.time.Instant;

public class GenesisEntry extends AbstractEntry {

	private static final long serialVersionUID = -1297202438129740595L;

	public GenesisEntry(final String ledgerName) {
		setHash(ledgerName);
		setSerialNumber(0);
		setCreatedAt(Instant.now());
		setType(EntryTypes.INSERT);
	}
}
