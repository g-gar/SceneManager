package com.ggar.scenemanager.core.interfaces.action;

import com.ggar.scenemanager.core.interfaces.Activity;
import com.ggar.scenemanager.core.interfaces.SceneManager;

public interface ActivityAndApplicationAction<A extends Activity, B extends SceneManager> extends Action<A> {

	Class<B> getTarget();

}
