package practices.gui.traversinggame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practices.files.DefaultResourceGetter;
import practices.gui.setters.StageSetter;
import practices.gui.traversinggame.commons.Sizes;
import practices.gui.traversinggame.commons.Texts;
import practices.gui.traversinggame.commons.assets.Assets;
import practices.gui.traversinggame.commons.styles.Styles;
import practices.gui.traversinggame.scenes.MainScene;

import java.io.InputStream;

public class TraversingGame extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load main scene
        try {
            // Resource getters
            DefaultResourceGetter assetsResourceGetter = new DefaultResourceGetter(Assets.class);
            DefaultResourceGetter stylesResourceGetter = new DefaultResourceGetter(Styles.class);

            // Set window icon
            InputStream icon = assetsResourceGetter.getResourceAsStream(Assets.Image.WIN);
            StageSetter.setWindowIcon(stage, icon);

            // Load main scene
            Scene scene = MainScene.getScene(assetsResourceGetter, stylesResourceGetter, stage);

            stage.setTitle(Texts.TITLE);
            stage.setScene(scene);

            double minWidth = Sizes.Stage.MAIN_MIN_WIDTH;
            double minHeight = Sizes.Stage.MAIN_MIN_HEIGHT;

            stage.show();

            StageSetter.setDiffMinSize(stage, scene, minWidth, minHeight);

        } catch (Exception e) {
            System.err.println(e);
            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
