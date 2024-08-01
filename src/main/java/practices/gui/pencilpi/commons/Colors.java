package practices.gui.pencilpi.commons;

import javafx.scene.paint.Color;
import practices.gui.commons.ColorPalette;

public enum Colors implements ColorPalette {
    WHITE(255,255,255),GREY_1(248,249,250),GREY_2(233,236,239),GREY_3(206,212,218), GREY_4(73, 80, 87), GREY_5(52, 58, 64),
    GREY_6(33, 37, 41);
    
    public final double[] color;

    public static final int MAX_ALPHA = 255;

    Colors(double r, double g, double b, double a) {
        this.color = new double[]{r, g, b, a};
    }

    Colors(int r, int g, int b, int a) {
        this(r, g, b, (double) a);
    }

    Colors(double r, double g, double b) {
        this(r, g, b, MAX_ALPHA);
    }

    Colors(int r, int g, int b) {
        this(r, g, b, (double) MAX_ALPHA);
    }

    public Color getColor() {
        return new Color(this.color[0] / 255, this.color[1] / 255, this.color[2] / 255, this.color[3] / 255);
    }

    public String getRGBA() {
        return "rgba(%d,%d,%d,%d)".formatted((int) this.color[0], (int) this.color[1], (int) this.color[2],
                (int) this.color[3]);
    }
    
    // Dark theme
    public final static class Dark {
        public final static Colors STAGE_BG = Colors.GREY_6;

        public final static Colors MENU_BG = Colors.GREY_5;
        public final static Colors TEXT_AREA_BG = STAGE_BG;
        public final static Colors LABEL_BG = Colors.GREY_5;

        //public final static Colors NODE_FG = Colors.YELLOW_LIGHT;
        public final static Colors NODE_BG = Colors.GREY_5;

        public final static Colors NODE_HOVER_FG = Colors.GREY_4;
        //public final static Colors NODE_HOVER_BG = Colors.YELLOW_DARK;

        public final static Colors FONT = Colors.GREY_2;

        public final static Colors FONT_HOVER = Colors.GREY_4;

        private Dark() {
        }
    }
}