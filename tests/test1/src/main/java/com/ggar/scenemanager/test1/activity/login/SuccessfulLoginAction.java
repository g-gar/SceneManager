package com.ggar.scenemanager.test1.activity.login;

import com.ggar.scenemanager.core.interfaces.SceneManager;
import com.ggar.scenemanager.core.interfaces.action.ActivityAndApplicationAction;
import com.ggar.scenemanager.test1.activity.dashboard.DashboardActivity;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString @EqualsAndHashCode
public class SuccessfulLoginAction implements ActivityAndApplicationAction<LoginActivity, SceneManager> {

	private final LoginActivity activity;
	private final SceneManager sceneManager;

	public SuccessfulLoginAction(LoginActivity activity, SceneManager sceneManager) {
		this.activity = activity;
		this.sceneManager = sceneManager;
	}

	@Override
	public Class<LoginActivity> getSource() {
		return LoginActivity.class;
	}

	@Override
	public Class<SceneManager> getTarget() {
		return SceneManager.class;
	}

	@Override
	public void configure(Stage stage) {
		Button button = (Button) activity.getParent().getScene().lookup("#login");
		button.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			if (activity.checkLogin()) {
				//todo: add a method to halt activities from the application instance
				this.activity.halt();
				this.sceneManager.switchTo(DashboardActivity.class);
			}
		});
	}
}
