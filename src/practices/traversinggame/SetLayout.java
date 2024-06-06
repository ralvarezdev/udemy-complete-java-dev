package practices.traversinggame;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SetLayout {
	public static void setBgColor(Pane pane) {
		Color color = ColorManager.GREY_MEDIUM.getColor();
		pane.setBackground(new Background(new BackgroundFill(color, null, null)));
	}
}
