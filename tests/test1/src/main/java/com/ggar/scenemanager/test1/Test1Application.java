package com.ggar.scenemanager.test1;

import com.ggar.scenemanager.core.interfaces.SceneManager;
import com.ggar.scenemanager.test1.activity.login.LoginActivity;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Test1Application extends javafx.application.Application
{
	private Stage stage;
	private SceneManager sceneManager;

	public Test1Application() {
		this.sceneManager = new Test1SceneManager();
	}

	public void start(Stage primaryStage) {
		((Test1SceneManager) this.sceneManager).setStage(primaryStage);
		this.sceneManager.switchTo(LoginActivity.class);
	}

	public static void main(String[] args) {
		Platform.setImplicitExit(true);
		launch(args);
	}
}
