package com.ggar.scenemanager.core.implementations;

import com.ggar.scenemanager.core.interfaces.configuration.Configuration;
import com.ggar.scenemanager.core.interfaces.configuration.Pair;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConfiguration implements Configuration {

	private final List<Pair> pairs;

	public AbstractConfiguration() {
		this.pairs = new LinkedList<Pair>();
	}

	@Override
	public <K, V> Pair<K, V> registerParameter(Pair<K, V> parameter) {
		this.pairs.add(parameter);
		return this.pairs.stream().filter(pair -> pair.getKey().equals(parameter.getKey())).findAny().get();
	}

	@Override
	public <K, V> Pair<K, V> registerParameter(K key, V value) {
		return this.registerParameter(new AbstractPair<>(key, value){});
	}

	@Override
	public <K, V> V get(K key) {
		V result = null;
		if (this.containsKey(key)) {
			result = (V) this.pairs.stream().filter(pair -> pair.getKey().equals(key)).findAny().get().getValue();
		}
		return result;
	}

	@Override
	public <K> Boolean containsKey(K key) {
		return this.pairs.stream().filter(pair -> pair.getKey().equals(key)).findAny().isPresent();
	}

	@Override
	public <K> Collection<K> keys() {
		return (Collection<K>) pairs.stream().map(Pair::getKey).collect(Collectors.toList());
	}

	@Override
	public <K, V> Collection<Pair<K, V>> pairs() {
		return new LinkedList<>() {{
			for (Pair pair : AbstractConfiguration.this.pairs) {
				add(pair);
			}
		}};
	}

	@Override
	public Configuration clone() {
		Configuration tocopy = this;
		return new AbstractConfiguration() {{
			for (Pair<?,?> pair : tocopy.pairs()) {
				registerParameter(pair.clone());
			}
		}};
	}

	public void clear() {
		this.pairs().clear();
	}
}
