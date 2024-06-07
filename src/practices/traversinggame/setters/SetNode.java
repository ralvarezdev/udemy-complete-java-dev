package practices.traversinggame.setters;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import practices.traversinggame.commons.ColorPalette;
import practices.traversinggame.commons.ColorStyles;
import practices.traversinggame.commons.SizeStyles;

public class SetNode {
	private static String getFontStyle(SizeStyles fontSizeStyle, ColorStyles colorStyle) {
		if (fontSizeStyle == null || colorStyle == null)
			return null;

		Integer fontSize = SizeStyles.getFontSize(fontSizeStyle);
		ColorPalette color = ColorStyles.getFontColorPalette(colorStyle);

		return """
				-fx-font-size: %dpx;
				-fx-text-fill: %s;
				""".formatted(fontSize, color.getRGBA());
	}

	private static String getBorderRadiusStyle(SizeStyles borderSizeStyle) {
		if (borderSizeStyle == null)
			return null;

		Integer borderRadius = SizeStyles.getBorderRadiusSize(borderSizeStyle);

		return """
				-fx-background-radius: %dpx;
				""".formatted(borderRadius);
	}

	private static String getBgColorStyle(ColorStyles colorStyle) {
		if (colorStyle == null)
			return null;

		ColorPalette color = ColorStyles.getNodeBgColorPalette(colorStyle);

		return """
				-fx-background-color: %s;
				""".formatted(color.getRGBA());
	}

	public static void setLabelStyle(Label label, SizeStyles fontSizeStyle, ColorStyles colorStyle) {
		String fontStyle = getFontStyle(fontSizeStyle, colorStyle);

		if (fontStyle != null)
			label.setStyle(fontStyle);
	}

	public static String getBtnStyle(SizeStyles fontSizeStyle, ColorStyles fontColorStyle, SizeStyles btnSizeStyle,
			ColorStyles btnColorStyle) {
		if (fontSizeStyle == null || fontColorStyle == null || btnSizeStyle == null || btnColorStyle == null)
			return null;

		String bgStyle = getBgColorStyle(btnColorStyle);
		String fontStyle = getFontStyle(fontSizeStyle, fontColorStyle);
		String borderRadiusStyle = getBorderRadiusStyle(btnSizeStyle);
		String fullStyle = "";

		if (bgStyle != null)
			fullStyle += bgStyle;

		if (fontStyle != null)
			fullStyle += fontStyle;

		if (borderRadiusStyle != null)
			fullStyle += borderRadiusStyle;

		return fullStyle;
	}

	public static void setBtnStyle(Button btn, SizeStyles fontSizeStyle, ColorStyles fontColorStyle,
			SizeStyles btnSizeStyle, ColorStyles btnColorStyle) {
		String fullStyle = getBtnStyle(fontSizeStyle, fontColorStyle, btnSizeStyle, btnColorStyle);

		if (fullStyle != null)
			btn.setStyle(fullStyle);
	}
}
