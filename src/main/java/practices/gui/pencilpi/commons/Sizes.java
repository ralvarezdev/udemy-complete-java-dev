package practices.gui.pencilpi.commons;

public final class Sizes {
    // Stage sizes
    public final static class Stage {
        public final static int MAIN_WIDTH = 800;
        public final static int MAIN_HEIGHT = 600;

        public final static int CALCULATOR_WIDTH = 400;
        public final static int CALCULATOR_HEIGHT = 600;

        private Stage() {
        }
    }

    // Button sizes
    public static final class Button {
        public final static int BIG_WIDTH = 400;
        public final static int BIG_HEIGHT = 30;

        private Button() {
        }
    }

    // Font sizes
    public static final class Font {
        public final static int TITLE = 16;
        public final static int MENU = 14;
        public final static int MENU_ITEM = 12;
        public final static int TEXT=12;

        private Font() {
        }
    }

    // Text area sizes
    public static final class TextArea{

        public final static int PADDING=10;
        private TextArea(){
        }

    }
    // Footer sizes
    public static final class Footer{
        public final static int PADDING_VERTICAL=5;
        public final static int PADDING_HORIZONTAL=15;
        private Footer(){
        }
    }

    private Sizes() {
    }
}
