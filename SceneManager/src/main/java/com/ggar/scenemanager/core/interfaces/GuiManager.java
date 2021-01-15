package com.ggar.scenemanager.core.interfaces;

import com.ggar.scenemanager.core.interfaces.action.Action;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public interface GuiManager {

	<G extends GuiManager> G setStage(Stage stage);
	<T extends Pane> void loadActivity(Class<? extends Activity<T>> c, Activity<T> activity, List<? extends Action> actions);
	<A extends Activity<T>, T extends Pane> Class<A> getCurrent();

}
