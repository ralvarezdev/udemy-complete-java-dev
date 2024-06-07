package practices.traversinggame.setters;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import practices.traversinggame.commons.ColorPalette;
import practices.traversinggame.commons.ColorStyles;

public class SetLayout {
	public static void setBgColor(Pane pane) {
		ColorPalette colorPalette = ColorStyles.getStageColorPalette(ColorStyles.DARK_STAGE);
		Color color = colorPalette.getColor();

		pane.setBackground(new Background(new BackgroundFill(color, null, null)));
	}
}
