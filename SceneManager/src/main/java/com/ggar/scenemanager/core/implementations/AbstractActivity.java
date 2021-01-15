package com.ggar.scenemanager.core.implementations;

import com.ggar.scenemanager.core.interfaces.Activity;
import com.ggar.scenemanager.core.interfaces.action.Action;
import com.ggar.scenemanager.core.interfaces.configuration.Configuration;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

//TODO enable working with fxml
public abstract class AbstractActivity<T extends Pane> implements Activity<T> {

	protected final Configuration configuration;
	protected final Collection<Class<? extends Action>> hooks;
	protected T parent;

	public AbstractActivity() {
		this(new AbstractConfiguration(){});
	}

	public AbstractActivity(Configuration configuration) {
		this.configuration = configuration;
		this.hooks = new ArrayList<>();
	}

	@Override
	public void configure(Configuration configuration) {
		this.configuration.clear();
		configuration.pairs().forEach(pair -> AbstractActivity.this.configuration.registerParameter(pair));
	}

	/**
	 * Creates the GUI for this activity
	 */
	@SneakyThrows({IOException.class})
	@Override
	public void create() {
		FXMLLoader loader;
		if (this.configuration.containsKey(InputStream.class)) {
			loader = new FXMLLoader();
			loader.setController(this);
			this.parent = loader.load(this.configuration.<Class<InputStream>, InputStream>get(InputStream.class));
		}
		if (this.configuration.containsKey(Action[].class)) {
			this.hooks.addAll(Arrays.asList(this.configuration.get(Action[].class)));
		}
	}

	@Override
	public T getParent() {
		return this.parent;
	}

	@Override
	public void destroy() {
		this.configuration.clear();
		this.parent = null;
	}

	@Override
	public Configuration halt() {
		return null;
	}

	@Override
	public void resume() {

	}

	@Override
	public Collection<Class<? extends Action>> getHooks() {
		return hooks;
	}

	@Override
	public void registerHooks(Class<? extends Action> hooks) {
		this.hooks.addAll(Arrays.asList(hooks));
	}

	@Override
	public ActivityState getState() {
		return null;
	}
}
