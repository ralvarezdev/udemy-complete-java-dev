package practices.traversinggame.setters;

import javafx.scene.Scene;
import practices.files.ResourceGetter;
import practices.traversinggame.commons.styles.Styles;

import java.io.IOException;

public class SceneSetter {
    public static void setDefaultStyles(ResourceGetter stylesResourceGetter, Scene scene) throws IOException {
        String fontStyle = stylesResourceGetter.getResourceToExternalForm(Styles.FONT);
        String generalStyle = stylesResourceGetter.getResourceToExternalForm(Styles.GENERAL);

        for (String style : new String[]{fontStyle, generalStyle})
            scene.getStylesheets().add(style);
    }
}
