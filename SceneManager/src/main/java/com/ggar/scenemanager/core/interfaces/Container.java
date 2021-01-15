package com.ggar.scenemanager.core.interfaces;

import com.ggar.scenemanager.core.interfaces.configuration.Configuration;
import javafx.scene.layout.Pane;

import java.util.Collection;

public interface Container {

	<T extends Pane> Collection<Configuration> getConfigurationHistory(Class<? extends Activity<T>> identifier);
	<T extends Pane> void clearConfigurationHistory(Class<? extends Activity<T>> identifier);
	<T extends Pane> Configuration getCurrentConfiguration(Class<? extends Activity<T>> identifier);
	<T extends Pane> Configuration getInitialConfiguration(Class<? extends Activity<T>> identifier);
	<T extends Pane> Configuration registerConfiguration(Class<? extends Activity<T>> identifier, Configuration configuration);

	<T> T get(Class<T> identifier);

//	TODO: Add an option to register classes to classes as in Guice Binder
	<T> T register(Class<T> identifier, T implementation);

}
