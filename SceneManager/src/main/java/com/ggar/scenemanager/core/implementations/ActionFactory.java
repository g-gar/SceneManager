package com.ggar.scenemanager.core.implementations;

import com.ggar.scenemanager.core.interfaces.Activity;
import com.ggar.scenemanager.core.interfaces.SceneManager;
import com.ggar.scenemanager.core.interfaces.action.Action;
import com.ggar.scenemanager.core.interfaces.action.ActivityAndApplicationAction;
import com.ggar.scenemanager.core.interfaces.action.BiActivityAction;
import com.ggar.scenemanager.core.interfaces.action.SingleActivityAction;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ActionFactory {

	@Getter
	private final SceneManager sceneManager;

	public ActionFactory(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	public Action create(Class<? extends Action> c) {
		Action result = null;
		List<Function<Class<? extends Action>, Action>> fns = new ArrayList<>() {{
			add(c -> createSingle(c));
			add(c -> createBi(c));
			add(c -> createBi2(c));
		}};
		int i = 0;
		while (result == null && i < fns.size()) {
			try {
				result = fns.get(i++).apply(c);
				if (result == null) throw new Exception();
			} catch (Exception e) {}
		}
		return result;
	}

//	@SneakyThrows
	public SingleActivityAction createSingle(Class<? extends Action> c) {
		SingleActivityAction result = null;
		Class source;
		Activity activity;
		try {
			if (SingleActivityAction.class.isAssignableFrom(c)) {
				source = (Class) c.getDeclaredMethod("getSource", null).invoke(createDummyInstance(c));
				activity = sceneManager.getActivity(source);
				result = (SingleActivityAction) c.getConstructor(source).newInstance(activity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

//	@SneakyThrows
	public BiActivityAction createBi(Class<? extends Action> c) {
		BiActivityAction result = null;
		Class source;
		Class target;
		Activity activityA;
		Activity activityB;
		try {
			if (BiActivityAction.class.isAssignableFrom(c)) {
				source = (Class) c.getDeclaredMethod("getSource", null).invoke(createDummyInstance(c));
				target = (Class) c.getDeclaredMethod("getTarget", null).invoke(createDummyInstance(c));
				activityA = sceneManager.getActivity(source);
				activityB = sceneManager.getActivity(target);
				result = (BiActivityAction) c.getDeclaredConstructor(source, target).newInstance(activityA, activityB);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

//	@SneakyThrows
	public ActivityAndApplicationAction createBi2(Class<? extends Action> c) {
		ActivityAndApplicationAction result = null;
		Class source;
		Activity activityA;
		try {
			if (ActivityAndApplicationAction.class.isAssignableFrom(c)) {
				source = (Class) c.getDeclaredMethod("getSource").invoke(createDummyInstance(c));
				activityA = this.sceneManager.getActivity(source);
				result = (ActivityAndApplicationAction) c.getDeclaredConstructor(source, SceneManager.class).newInstance(activityA, this.sceneManager);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private <T> T createDummyInstance(Class<T> c) {
		T result = null;

		try {
			Constructor<T>[] constructors = (Constructor<T>[]) c.getDeclaredConstructors();
			Constructor<T> constructor;
			if (constructors.length > 0) {
				constructor = constructors[0];
				Parameter[] params = constructor.getParameters();
				Object[] args = new Object[params.length];
 				for (int i = 0; i < params.length; i++) {
					args[i] = null;
				}
 				result = constructor.newInstance(args);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
