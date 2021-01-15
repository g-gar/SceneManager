package com.ggar.scenemanager.test1.activity.login;

import com.ggar.scenemanager.core.interfaces.action.SingleActivityAction;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString @EqualsAndHashCode
public class ExitAction implements SingleActivityAction<LoginActivity> {

	private final LoginActivity activity;

	public ExitAction(LoginActivity activity) {
		this.activity = activity;
	}

	@Override
	public Class<LoginActivity> getSource() {
		return LoginActivity.class;
	}

	@Override
	public void configure(Stage stage) {
		Button button = (Button) activity.getParent().getScene().lookup("#exit");
		button.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
			System.out.println("Exiting...");
			Platform.exit();
		});
	}
}
