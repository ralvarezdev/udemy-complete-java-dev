package practices.traversinggame.setters;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import practices.files.ResourceGetter;
import practices.traversinggame.commons.assets.Assets;

import java.io.IOException;
import java.io.InputStream;

public class StageSetter {
    public static void setWindowIcon(ResourceGetter assetsResourceGetter, Stage stage) throws IOException {
        InputStream icon = assetsResourceGetter.getResourceAsStream(Assets.Image.WIN);
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
