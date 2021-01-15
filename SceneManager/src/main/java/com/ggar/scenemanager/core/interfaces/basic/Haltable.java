package com.ggar.scenemanager.core.interfaces.basic;

import com.ggar.scenemanager.core.interfaces.configuration.Configuration;

public interface Haltable {

	Configuration halt();
	void resume();

}
