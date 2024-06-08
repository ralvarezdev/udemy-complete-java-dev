package practices.traversinggame.setters;

import java.io.InputStream;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import practices.MissingResourceFileException;
import practices.traversinggame.commons.Assets;
import practices.traversinggame.managers.ResourceManager;

public class SetStage {
	public static void setWindowIcon(Stage stage) throws MissingResourceFileException {
		InputStream icon = ResourceManager.INSTANCE.getAsset(Assets.WIN_IMAGE);

		stage.getIcons().add(new Image(icon));
	}

	public static double getDiffWidth(Stage stage, Scene scene) {
		return stage.getWidth() - scene.getWidth();
	}

	public static double getDiffHeight(Stage stage, Scene scene) {
		return stage.getHeight() - scene.getHeight();
	}

	public static void setDiffMinSize(Stage stage, Scene scene, double minWidth, double MinHeight) {
		double diffWidth = getDiffWidth(stage, scene);
		double diffHeight = getDiffHeight(stage, scene);

		stage.setMinWidth(minWidth + diffWidth);
		stage.setMinHeight(MinHeight + diffHeight);
	}
}
