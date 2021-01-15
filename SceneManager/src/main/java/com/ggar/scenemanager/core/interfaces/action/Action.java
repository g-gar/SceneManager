package com.ggar.scenemanager.core.interfaces.action;

import javafx.stage.Stage;

public interface Action<A> {

	Class<A> getSource();
	void configure(Stage stage);

}
