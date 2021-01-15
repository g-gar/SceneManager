package com.ggar.scenemanager.core.interfaces.basic;


import com.ggar.scenemanager.core.interfaces.action.Action;

import java.util.Collection;

public interface Hookable {

	Collection<Class<? extends Action>> getHooks();
	void registerHooks(Class<? extends Action> hooks);

}
