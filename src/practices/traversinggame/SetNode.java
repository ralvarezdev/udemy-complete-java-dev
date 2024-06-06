package practices.traversinggame;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SetNode {
	private static String getFontStyle(ColorManager color, int fontSize) {
		if (color == null || fontSize <= 0)
			return null;

		return """
				-fx-font-size: %dpx;
				-fx-text-fill: %s;
				""".formatted(fontSize, color.getRGBA());
	}

	private static String getBorderRadiusStyle(int borderRadius) {
		if (borderRadius <= 0)
			return null;

		return """
				-fx-background-radius: %dpx;
				""".formatted(borderRadius);
	}

	private static String getBgColorStyle(ColorManager color) {
		if (color == null)
			return null;

		return """
				-fx-background-color: %s;
				""".formatted(color.getRGBA());
	}

	public static void setLabelStyle(Label label, ColorManager color, int fontSize) {
		String fontStyle = getFontStyle(color, fontSize);

		if (fontStyle != null)
			label.setStyle(fontStyle);
	}

	public static String getBtnStyle(ColorManager bgColor, ColorManager fgColor, int fontSize, int borderRadius) {
		String bgStyle = getBgColorStyle(bgColor);
		String fontStyle = getFontStyle(fgColor, fontSize);
		String borderRadiusStyle = getBorderRadiusStyle(borderRadius);
		String fullStyle = "";

		if (bgStyle != null)
			fullStyle += bgStyle;

		if (fontStyle != null)
			fullStyle += fontStyle;

		if (borderRadiusStyle != null)
			fullStyle += borderRadiusStyle;

		return fullStyle;
	}

	public static void setBtnStyle(Button btn, ColorManager bgColor, ColorManager fgColor, int fontSize,
			int borderRadius) {
		String fullStyle = getBtnStyle(bgColor, fgColor, fontSize, borderRadius);
		btn.setStyle(fullStyle);
	}
}
