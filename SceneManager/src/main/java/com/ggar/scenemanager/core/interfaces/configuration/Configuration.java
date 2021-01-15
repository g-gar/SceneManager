package com.ggar.scenemanager.core.interfaces.configuration;

import com.ggar.scenemanager.core.interfaces.basic.Cloneable;
import java.util.Collection;

//TODO: make this implement map
public interface Configuration extends Cloneable<Configuration> {

	<K,V> Pair<K,V> registerParameter(Pair<K, V> parameter);
	<K,V> Pair<K,V> registerParameter(K key, V value);
	<K,V> V get(K key);
	<K> Boolean containsKey(K key);

	<K> Collection<K> keys();
	<K,V> Collection<Pair<K,V>> pairs();

	void clear();

}
