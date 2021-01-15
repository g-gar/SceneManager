package com.ggar.scenemanager.core.implementations;

import com.ggar.scenemanager.core.interfaces.Activity;
import com.ggar.scenemanager.core.interfaces.GuiManager;
import com.ggar.scenemanager.core.interfaces.action.Action;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.util.List;

public class AbstractGuiManager implements GuiManager {

	protected Class<? extends Activity> current;
	private Stage stage;

	public AbstractGuiManager() {
//		this.workers = new ArrayList<Worker>();
	}

	@Override
	public AbstractGuiManager setStage(Stage stage) {
		this.stage = stage;
		return this;
	}

	@SneakyThrows
	@Override
	public <T extends Pane> void loadActivity(Class<? extends Activity<T>> identifier, Activity<T> activity, List<? extends Action> actions) {
		this.current = identifier;
		this.stage.setScene(new Scene(activity.getParent()));
		for (Action action : actions) {
			Platform.runLater(() -> action.configure(this.stage));
		}
		this.stage.show();
	}

	@Override
	public Class<? extends Activity> getCurrent() {
		return this.current;
	}

//	public class Worker extends Task<Void> {

//		@Getter
//		private final Action action;
//
//		public Worker(Action action) {
//			this.action = action;
//		}
//
//		protected Void call() throws Exception {
//			KeyFrame kf = new KeyFrame(Duration.seconds(0.01),event -> {
//				Platform.runLater(() -> {
//					if (this.action.configure()) {
//						this.action.execute();
//					}
//				});
//			});
//			Timeline tl = new Timeline(kf);
//			tl.setCycleCount(Timeline.INDEFINITE);
//			tl.play();
//
//			return null;
//		}

//		@Override
//		public void run() {
//			Platform.runLater(() -> {
//				while (!this.action.validate()) {
//					try {
//						Thread.sleep(1);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				this.action.execute();
//			});
//		}
//	}
}
