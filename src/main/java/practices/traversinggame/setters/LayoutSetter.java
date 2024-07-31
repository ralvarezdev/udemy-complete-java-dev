package practices.traversinggame.setters;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import practices.traversinggame.commons.Colors;

public class LayoutSetter {
    public static void setBgColor(Pane pane) throws NullPointerException {
        Color stageBgColor = Colors.Dark.STAGE_BG.getColor();
        pane.setBackground(new Background(new BackgroundFill(stageBgColor, null, null)));
    }
}
