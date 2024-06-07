package practices.traversinggame.commons;

public enum ColorStyles {
	DARK_STAGE, DARK_FONT, DARK_FONT_HOVER, DARK_NODE_FG, DARK_NODE_BG, DARK_NODE_HOVER_FG, DARK_NODE_HOVER_BG;

	public static ColorPalette getFontColorPalette(ColorStyles colorStyle) {
		return switch (colorStyle) {
		case DARK_FONT -> Colors.DARK_FONT_COLOR;
		case DARK_FONT_HOVER -> Colors.DARK_FONT_HOVER_COLOR;
		default -> null;
		};
	}

	public static ColorPalette getNodeBgColorPalette(ColorStyles colorStyle) {
		return switch (colorStyle) {
		case ColorStyles.DARK_NODE_BG -> Colors.DARK_NODE_BG_COLOR;
		case ColorStyles.DARK_NODE_HOVER_BG -> Colors.DARK_NODE_HOVER_BG_COLOR;
		default -> null;
		};
	}

	public static ColorPalette getNodeFgColorPalette(ColorStyles colorStyle) {
		return switch (colorStyle) {
		case ColorStyles.DARK_NODE_FG -> Colors.DARK_NODE_FG_COLOR;
		case ColorStyles.DARK_NODE_HOVER_FG -> Colors.DARK_NODE_HOVER_FG_COLOR;
		default -> null;
		};
	}

	public static ColorPalette getStageColorPalette(ColorStyles colorStyle) {
		return switch (colorStyle) {
		case ColorStyles.DARK_STAGE -> Colors.DARK_STAGE_BG_COLOR;
		default -> null;
		};
	}
}
