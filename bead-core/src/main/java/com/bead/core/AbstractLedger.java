package com.bead.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class AbstractLedger<T extends Entry> implements Ledger<T> {

	private GenesisEntry genesis;

	private static final String STORAGE = "./store/";

	public AbstractLedger(final String ledgerName) {
		final File ledgerFile = new File(STORAGE, ledgerName);
		if (ledgerFile.createNewFile()) {
			genesis = new GenesisEntry(ledgerName);
		}
	}

	@Override
	public void addEntry(T newEntry) {
		AbstractEntry entry = genesis;
		while (entry.getNext() != null) {
			entry = entry.getNext();
		}

		try {
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
					ObjectOutputStream out = new ObjectOutputStream(bos)) {
				out.writeObject(entry);
				final byte[] objectData = bos.toByteArray();
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				digest.digest(objectData);
			}
		} catch (IOException | NoSuchAlgorithmException e) {

		}

	}

}
