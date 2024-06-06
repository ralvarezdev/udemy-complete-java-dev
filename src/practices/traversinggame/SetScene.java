package practices.traversinggame;

import javafx.scene.Scene;
import practices.MissingResourceFileException;

public class SetScene {
	public static void setDefaultStyles(Scene scene) throws MissingResourceFileException {
		scene.getStylesheets().add(ResourceManager.INSTANCE.getFontsStyle());
		scene.getStylesheets().add(ResourceManager.INSTANCE.getGeneralStyle());
	}
}
