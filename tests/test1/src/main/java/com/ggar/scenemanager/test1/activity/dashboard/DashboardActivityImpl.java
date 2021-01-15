package com.ggar.scenemanager.test1.activity.dashboard;

import com.ggar.scenemanager.core.implementations.AbstractActivity;
import com.ggar.scenemanager.core.interfaces.configuration.Configuration;
import javafx.scene.layout.HBox;

public class DashboardActivityImpl extends AbstractActivity<HBox> implements DashboardActivity {

	public DashboardActivityImpl(Configuration configuration) {
		super(configuration);
	}

}
