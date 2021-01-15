package com.ggar.scenemanager.core.interfaces;

import com.ggar.scenemanager.core.interfaces.configuration.Configuration;
import javafx.scene.layout.Pane;

//TODO: also enable working with fxml
//TODO: secure access to important methods against hooks
public interface SceneManager {

	<A extends Activity<T>, T extends Pane> SceneManager registerActivity(Class<A> identifier, Class<? extends A> implementation);
	<A extends Activity<T>, T extends Pane> SceneManager registerActivity(Class<A> identifier, Class<? extends A> implementation, Configuration configuration);
	<T extends Pane> Activity<T> getActivity(Class<? extends Activity<T>> identifier);
	<A extends Activity<T>, T extends Pane> A restartActivity(Class<A> identifier);

	<T extends Pane> Activity<T> reconfigureActivity(Class<? extends Activity<T>> identifier, Configuration configuration);
	public <A extends Activity<T>, T extends Pane> A switchTo(Class<A> identifier);
	<T extends Pane> Class<? extends Activity<T>> getActiveActivity();

}
