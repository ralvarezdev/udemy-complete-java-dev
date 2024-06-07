package practices.traversinggame.setters;

import javafx.scene.Scene;
import practices.MissingResourceFileException;
import practices.traversinggame.managers.ResourceManager;

public class SetScene {
	public static void setDefaultStyles(Scene scene) throws MissingResourceFileException {
		scene.getStylesheets().add(ResourceManager.INSTANCE.getFontsStyle());
		scene.getStylesheets().add(ResourceManager.INSTANCE.getGeneralStyle());
	}
}
