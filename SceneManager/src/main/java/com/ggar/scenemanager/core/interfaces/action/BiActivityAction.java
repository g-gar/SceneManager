package com.ggar.scenemanager.core.interfaces.action;

import com.ggar.scenemanager.core.interfaces.Activity;

public interface BiActivityAction<A extends Activity, B extends Activity> extends Action<A> {

	Class<B> getTarget();

}