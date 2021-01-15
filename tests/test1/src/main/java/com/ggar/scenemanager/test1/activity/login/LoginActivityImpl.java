package com.ggar.scenemanager.test1.activity.login;

import com.ggar.scenemanager.core.implementations.AbstractActivity;
import com.ggar.scenemanager.core.interfaces.configuration.Configuration;
import com.ggar.scenemanager.test1.service.login.LoginService;
import com.ggar.scenemanager.test1.service.login.BasicLoginService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lombok.SneakyThrows;

public class LoginActivityImpl extends AbstractActivity<GridPane> implements LoginActivity {

	private final LoginService loginService;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;

	public LoginActivityImpl(Configuration configuration) {
		super(configuration);
		this.loginService = new BasicLoginService();
	}

	@Override
	public void create() {
		super.create();
	}

	@SneakyThrows
	@Override
	public boolean checkLogin() {
		return !this.username.getText().isEmpty()
			&& !this.password.getText().isEmpty()
			&& this.loginService.isValid(this.username.getText(), this.password.getText());
	}
}
