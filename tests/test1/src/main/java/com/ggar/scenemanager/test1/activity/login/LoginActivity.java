package com.ggar.scenemanager.test1.activity.login;

import com.ggar.scenemanager.core.interfaces.Activity;
import javafx.scene.layout.GridPane;

public interface LoginActivity extends Activity<GridPane> {

	boolean checkLogin();

}
