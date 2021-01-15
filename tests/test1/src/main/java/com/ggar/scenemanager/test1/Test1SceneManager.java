package com.ggar.scenemanager.test1;

import com.ggar.scenemanager.core.implementations.AbstractConfiguration;
import com.ggar.scenemanager.core.implementations.AbstractGuiManager;
import com.ggar.scenemanager.core.implementations.AbstractSceneManager;
import com.ggar.scenemanager.core.implementations.ActionFactory;
import com.ggar.scenemanager.core.interfaces.GuiManager;
import com.ggar.scenemanager.core.interfaces.action.Action;
import com.ggar.scenemanager.test1.activity.dashboard.DashboardActivity;
import com.ggar.scenemanager.test1.activity.dashboard.DashboardActivityImpl;
import com.ggar.scenemanager.test1.activity.login.ExitAction;
import com.ggar.scenemanager.test1.activity.login.LoginActivity;
import com.ggar.scenemanager.test1.activity.login.LoginActivityImpl;
import com.ggar.scenemanager.test1.activity.login.SuccessfulLoginAction;
import javafx.stage.Stage;

import java.io.InputStream;

public class Test1SceneManager extends AbstractSceneManager {

	public Test1SceneManager() {
		this.container.register(ActionFactory.class, new ActionFactory(this));
		this.container.register(GuiManager.class, new AbstractGuiManager(){});

		this.registerActivity(LoginActivity.class, LoginActivityImpl.class, new AbstractConfiguration() {{
			this.registerParameter(InputStream.class, Test1Application.class.getResourceAsStream("/login.fxml"));
			this.registerParameter(Action[].class, new Class[]{SuccessfulLoginAction.class, ExitAction.class});
		}});

		this.registerActivity(DashboardActivity.class, DashboardActivityImpl.class, new AbstractConfiguration() {{
			this.registerParameter(InputStream.class, Test1Application.class.getResourceAsStream("/dashboard.fxml"));
		}});
	}

	public <S extends Stage> void setStage(S stage) {
		this.container.get(GuiManager.class).setStage(stage);
	}
}
