package com.ggar.scenemanager.core.interfaces.basic;

import com.ggar.scenemanager.core.interfaces.configuration.Configuration;

public interface Configurable {

	/**
	 * Configuration phase at the creation of an activity. This enables creating an initial creation of the instance
	 * and a posterior configuration when needed (lazy loading)
	 * @param configuration the injected parameters
	 */
	void configure(Configuration configuration);

}
