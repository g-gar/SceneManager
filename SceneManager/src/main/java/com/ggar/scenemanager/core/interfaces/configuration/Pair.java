package com.ggar.scenemanager.core.interfaces.configuration;

import com.ggar.scenemanager.core.interfaces.basic.Cloneable;

public interface Pair<K,V> extends Cloneable<Pair<K,V>> {

	K getKey();
	V getValue();

}
