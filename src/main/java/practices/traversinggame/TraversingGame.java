package practices.traversinggame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practices.files.DefaultResourceGetter;
import practices.traversinggame.commons.Sizes;
import practices.traversinggame.commons.Texts;
import practices.traversinggame.commons.assets.Assets;
import practices.traversinggame.commons.styles.Styles;
import practices.traversinggame.scenes.MainScene;
import practices.traversinggame.setters.StageSetter;

public class TraversingGame extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load main scene
        try {
            // Resource getters
            DefaultResourceGetter assetsResourceGetter = new DefaultResourceGetter(Assets.class);
            DefaultResourceGetter stylesResourceGetter = new DefaultResourceGetter(Styles.class);

            // Set window icon
            StageSetter.setWindowIcon(assetsResourceGetter, stage);

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
