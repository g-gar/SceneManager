package com.ggar.scenemanager.core.implementations;

import com.ggar.scenemanager.core.interfaces.Activity;
import com.ggar.scenemanager.core.interfaces.Container;
import com.ggar.scenemanager.core.interfaces.GuiManager;
import com.ggar.scenemanager.core.interfaces.SceneManager;
import com.ggar.scenemanager.core.interfaces.action.Action;
import com.ggar.scenemanager.core.interfaces.configuration.Configuration;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractSceneManager implements SceneManager {

	protected final Container container;

	public AbstractSceneManager() {
		this(new DefaultContainer() {});
	}

	public AbstractSceneManager(Container container) {
		this.container = container;
	}

	@Override
	public <A extends Activity<T>, T extends Pane> SceneManager registerActivity(Class<A> identifier, Class<? extends A> implementation) {
		return this.registerActivity(identifier, implementation, new AbstractConfiguration(){});
	}

	@Override
	public <A extends Activity<T>, T extends Pane> SceneManager registerActivity(Class<A> identifier, Class<? extends A> implementation, Configuration configuration) {
		try {
			A activity = implementation.getDeclaredConstructor(Configuration.class).newInstance(configuration);
			this.container.register(identifier, activity);
			this.container.registerConfiguration(identifier, configuration);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public <T extends Pane> Activity<T> getActivity(Class<? extends Activity<T>> identifier) {
		return this.container.get(identifier);
	}

	@Override
	public <A extends Activity<T>, T extends Pane> A restartActivity(Class<A> identifier) {
		A result = null;
		try {
			Class<A> implementation = (Class<A>) this.container.get(identifier).getClass();
			Configuration configuration = this.container.getInitialConfiguration(identifier);
			this.container.clearConfigurationHistory(identifier);
			this.container.registerConfiguration(identifier, configuration);
			result = implementation.getDeclaredConstructor(Configuration.class).newInstance(configuration);
			this.container.register(identifier, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public <T extends Pane> Activity<T> reconfigureActivity(Class<? extends Activity<T>> identifier, Configuration configuration) {
		Activity<T> result = null;
		Configuration newConfiguration;
		try {
			newConfiguration = this.container.getCurrentConfiguration(identifier);
			configuration.pairs().forEach(pair -> newConfiguration.registerParameter(pair));
			this.container.registerConfiguration(identifier, newConfiguration);
			result = this.restartActivity(identifier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public <A extends Activity<T>, T extends Pane> A switchTo(Class<A> identifier) {
		A activity = null;
		GuiManager guiManager;
		List<Action> actions;
		Configuration configuration;
		ActionFactory actionFactory;
		try {
			activity = this.container.get(identifier);
			guiManager = this.container.get(GuiManager.class);
			configuration = this.container.getCurrentConfiguration(identifier);
			actionFactory = this.container.get(ActionFactory.class);
			activity.configure(configuration);
			activity.create();
			actions = new ArrayList<Action>() {{
				if (configuration.containsKey(Action[].class)) {
					for (Class<? extends Action> c : Arrays.<Class<Action>>asList(configuration.get(Action[].class))) {
						add(actionFactory.create(c));
					}
				}
			}};
			guiManager.loadActivity(identifier, activity, actions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activity;
	}

	@Override
	public <T extends Pane> Class<Activity<T>> getActiveActivity() {
		return this.container.get(GuiManager.class).getCurrent();
	}
}
