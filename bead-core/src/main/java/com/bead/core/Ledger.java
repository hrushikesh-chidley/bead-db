package com.bead.core;

public interface Ledger<E extends Entry> {
	
	public void addEntry(E e);
	
	public void removeEntry(E e);
	
	public void updateEntry(E e);
	
	public E findEntry();

}
