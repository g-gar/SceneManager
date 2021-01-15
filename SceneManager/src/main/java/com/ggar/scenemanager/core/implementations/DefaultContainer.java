package com.ggar.scenemanager.core.implementations;

import com.ggar.scenemanager.core.interfaces.Activity;
import com.ggar.scenemanager.core.interfaces.Container;
import com.ggar.scenemanager.core.interfaces.configuration.Configuration;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public abstract class DefaultContainer implements Container {

	protected final Map<Class<? extends Activity>, Collection<Configuration>> configurations;
	protected final Map<Class, Object> items;

	protected DefaultContainer() {
		this.configurations = new ConcurrentHashMap<>();
		this.items = new ConcurrentHashMap<>();
	}

	@Override
	public <T extends Pane> Collection<Configuration> getConfigurationHistory(Class<? extends Activity<T>> identifier) {
		Collection<Configuration> result = null;
		if (this.items.containsKey(identifier) && this.configurations.containsKey(identifier)) {
			result = this.configurations.get(identifier).stream()
				.map(configuration -> configuration.clone())
				.collect(Collectors.toList());
		} else {
			throw new NoSuchElementException(String.format("%s does not exist in container", identifier));
		}
		return result;
	}

	@Override
	public <T extends Pane> void clearConfigurationHistory(Class<? extends Activity<T>> identifier) {
		if (this.items.containsKey(identifier) && this.configurations.containsKey(identifier)) {
			this.configurations.get(identifier).clear();
		} else {
			throw new NoSuchElementException(String.format("%s does not exist in container", identifier));
		}
	}

	@Override
	public <T extends Pane> Configuration getCurrentConfiguration(Class<? extends Activity<T>> identifier) {
		Configuration result = null;
		if (this.items.containsKey(identifier) && this.configurations.containsKey(identifier)) {
			result = this.configurations.get(identifier).stream()
				.map(configuration -> configuration.clone())
				.collect(Collectors.toList())
			.stream()
			.skip(this.configurations.get(identifier).size() - 1)
			.map(e -> e.clone())
			.findFirst()
			.orElseThrow(NoSuchElementException::new);
		} else {
			throw new NoSuchElementException(String.format("%s does not exist in container", identifier));
		}
		return result;
	}

	@Override
	public <T extends Pane> Configuration getInitialConfiguration(Class<? extends Activity<T>> identifier) {
		Configuration result = null;
		if (this.items.containsKey(identifier) && this.configurations.containsKey(identifier)) {
			result = this.configurations.get(identifier).stream()
				.map(configuration -> configuration.clone())
				.collect(Collectors.toList())
				.stream()
				.findFirst()
				.orElseThrow(NoSuchElementException::new)
				.clone();
		} else {
			throw new NoSuchElementException(String.format("%s does not exist in container", identifier));
		}
		return result;
	}

	public <T extends Pane> Configuration registerConfiguration(Class<? extends Activity<T>> identifier, Configuration configuration) {
		if (this.configurations.containsKey(identifier)) {
			this.configurations.get(identifier).add(configuration);
		} else {
			this.configurations.put(identifier, new ArrayList<>(){{
				add(configuration);
			}});
		}
		return this.getCurrentConfiguration(identifier);
	}

	@Override
	public <T> T get(Class<T> identifier) {
		T result = null;
		if (this.items.containsKey(identifier)) {
			result = (T) this.items.get(identifier);
		} else {
			throw new NoSuchElementException(String.format("%s does not exist in container", identifier));
		}
		return result;
	}

	@Override
	public <T> T register(Class<T> identifier, T value) {
		this.items.put(identifier, value);
		if (identifier.isAssignableFrom(Activity.class)) {
			this.configurations.put((Class<? extends Activity>) identifier, new ArrayList<Configuration>());
		}
		return (T) this.items.get(identifier);
	}
}
