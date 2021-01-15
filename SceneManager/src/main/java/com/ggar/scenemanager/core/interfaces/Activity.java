package com.ggar.scenemanager.core.interfaces;

import com.ggar.scenemanager.core.implementations.ActivityState;
import com.ggar.scenemanager.core.interfaces.basic.Component;
import com.ggar.scenemanager.core.interfaces.basic.Haltable;
import com.ggar.scenemanager.core.interfaces.basic.Hookable;
import javafx.scene.layout.Pane;

//TODO: secure access against hooks
public interface Activity<T extends Pane> extends Component, Haltable, Hookable {

	T getParent();
	ActivityState getState();

}
