package practices.traversinggame;

import javafx.application.Application;
import javafx.stage.Stage;
import practices.traversinggame.commons.Texts;
import practices.traversinggame.scenes.MainScene;
import practices.traversinggame.setters.SetStage;

public class TraversingGame extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// Load main scene
		try {
			SetStage.setWindowIcon(stage);

			stage.setTitle(Texts.TITLE);
			stage.setScene(MainScene.getScene());
			stage.show();

		} catch (Exception e) {
			System.err.println(e);
			System.exit(-1);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
