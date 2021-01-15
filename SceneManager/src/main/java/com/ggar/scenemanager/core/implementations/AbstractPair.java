package com.ggar.scenemanager.core.implementations;


import com.ggar.scenemanager.core.interfaces.configuration.Pair;

public abstract class AbstractPair<K,V> implements Pair<K,V> {

	protected final K key;
	protected final V value;

	public AbstractPair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public Pair<K,V> clone() {
		return new AbstractPair<>(this.getKey(), this.getValue()){};
	}

	@Override
	public K getKey() {
		return this.key;
	}

	@Override
	public V getValue() {
		return this.value;
	}
}
