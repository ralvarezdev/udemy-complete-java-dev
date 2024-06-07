
package practices.traversinggame.commons;

import javafx.scene.paint.Color;

public enum ColorPalette {
	GREEN_LIGHT(195, 250, 232), GREEN_DARK(56, 217, 169), GREY_LIGHT(214, 243, 245), GREY_MEDIUM(73, 80, 87),
	GREY_DARK(33, 37, 41);

	private double[] color;

	private static final int MAX_ALPHA = 255;

	ColorPalette(double r, double g, double b, double a) {
		this.color = new double[] { r, g, b, a };
	}

	ColorPalette(int r, int g, int b, int a) {
		this((double) r, (double) g, (double) b, (double) a);
	}

	ColorPalette(double r, double g, double b) {
		this(r, g, b, MAX_ALPHA);
	}

	ColorPalette(int r, int g, int b) {
		this((double) r, (double) g, (double) b, (double) MAX_ALPHA);
	}

	public Color getColor() {
		return new Color(this.color[0] / 255, this.color[1] / 255, this.color[2] / 255, this.color[3] / 255);
	}

	public String getRGBA() {
		return "rgba(%d,%d,%d,%d)".formatted((int) this.color[0], (int) this.color[1], (int) this.color[2],
				(int) this.color[3]);
	}
}
