package practices.gui.pencilpi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practices.files.DefaultResourceGetter;
import practices.gui.pencilpi.commons.Sizes;
import practices.gui.pencilpi.commons.Texts;
import practices.gui.pencilpi.commons.assets.Assets;
import practices.gui.pencilpi.scenes.MainScene;
import practices.gui.setters.StageSetter;

import java.io.InputStream;

public class PencilPi extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Load main scene
        try {
            // Resource getters
            DefaultResourceGetter assetsResourceGetter = new DefaultResourceGetter(Assets.class);

            // Set window icon
            InputStream icon = assetsResourceGetter.getResourceAsStream(Assets.Image.WIN);
            StageSetter.setWindowIcon(stage, icon);

            // Get the main scene
            Scene scene = MainScene.getScene(assetsResourceGetter);

            // Set the stage
            stage.setTitle(Texts.TITLE);
            stage.setScene(scene);

            // Set minimum size of the stage
            stage.setMinHeight(Sizes.Stage.MAIN_HEIGHT);
            stage.setMinWidth(Sizes.Stage.MAIN_WIDTH);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
