package practices.traversinggame.commons;

public enum SizeStyles {
	SMALL, MEDIUM, BIG;

	public static Integer getFontSize(SizeStyles fontSize) {
		return switch (fontSize) {
		case SMALL -> Sizes.SMALL_FONT_SIZE;
		case MEDIUM -> Sizes.MEDIUM_FONT_SIZE;
		case BIG -> Sizes.BIG_FONT_SIZE;
		default -> null;
		};
	}

	public static Integer getBorderRadiusSize(SizeStyles borderSize) {
		return switch (borderSize) {
		case BIG -> Sizes.BIG_BORDER_RADIUS;
		default -> null;
		};
	}
}
