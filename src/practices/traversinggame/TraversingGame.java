package practices.traversinggame;

import javafx.application.Application;
import javafx.stage.Stage;

public class TraversingGame extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// Load main scene
		try {
			SetStage.setWindowIcon(stage);

			stage.setTitle(MainScene.getTitle());
			stage.setScene(MainScene.generateMainScene());
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
