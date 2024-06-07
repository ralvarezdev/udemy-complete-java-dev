package practices.traversinggame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practices.traversinggame.commons.Sizes;
import practices.traversinggame.commons.Texts;
import practices.traversinggame.scenes.MainScene;
import practices.traversinggame.setters.SetStage;

public class TraversingGame extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// Load main scene
		try {
			SetStage.setWindowIcon(stage);

			Scene scene = MainScene.getScene(stage);

			stage.setTitle(Texts.TITLE);
			stage.setScene(scene);

			double minWidth = Sizes.MAIN_MIN_WIDTH;
			double minHeight = Sizes.MAIN_MIN_HEIGHT;

			stage.show();

			SetStage.setDiffMinSize(stage, scene, minWidth, minHeight);

		} catch (Exception e) {
			System.err.println(e);
			System.exit(-1);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
