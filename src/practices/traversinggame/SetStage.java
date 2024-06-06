package practices.traversinggame;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import practices.MissingResourceFileException;

public class SetStage {
	private final static String WIN_ICON = "icon002.png";

	public static void setWindowIcon(Stage stage) throws MissingResourceFileException {
		InputStream icon = ResourceManager.INSTANCE.getAsset(WIN_ICON);

		stage.getIcons().add(new Image(icon));
	}
}
