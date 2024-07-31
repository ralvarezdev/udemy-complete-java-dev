package practices.traversinggame.setters;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import practices.traversinggame.commons.ColorPalette;

public class NodeSetter {
    private static String getFontStyle(int fontSize, ColorPalette textColor) {
        if (fontSize <= 0 || textColor == null)
            return "";

        return """
                -fx-font-size: %dpx;
                -fx-text-fill: %s;
                """.formatted(fontSize, textColor.getRGBA());
    }

    private static String getBorderRadiusStyle(int borderRadiusSize) {
        if (borderRadiusSize <= 0)
            return "";

        return """
                -fx-background-radius: %dpx;
                -fx-border-radius: %s;
                """.formatted(borderRadiusSize, borderRadiusSize);
    }

    private static String getBgColorStyle(ColorPalette color) {
        if (color == null)
            return "";

        return """
                -fx-background-color: %s;
                """.formatted(color.getRGBA());
    }

    public static void setLabelStyle(Label label, int fontSize, ColorPalette textColor) {
        if (fontSize <= 0 || textColor == null)
            return;

        String fontStyle = getFontStyle(fontSize, textColor);

        if (!fontStyle.isEmpty())
            label.setStyle(fontStyle);
    }

    public static String getBtnStyle(int fontSize, ColorPalette textColor, int borderRadiusSize,
                                     ColorPalette btnColor) {
        if (fontSize <= 0 || textColor == null || borderRadiusSize <= 0 || btnColor == null)
            return "";

        String bgStyle = getBgColorStyle(btnColor);
        String fontStyle = getFontStyle(fontSize, textColor);
        String borderRadiusStyle = getBorderRadiusStyle(borderRadiusSize);
        return "%s%s%s".formatted(bgStyle, fontStyle, borderRadiusStyle);
    }

    public static void setBtnStyle(Button btn, int fontSize, ColorPalette textColor, int borderRadiusSize,
                                   ColorPalette btnColor) {
        String btnStyle = getBtnStyle(fontSize, textColor, borderRadiusSize, btnColor);

        if (!btnStyle.isEmpty())
            btn.setStyle(btnStyle);
    }

    public static void setRectStyle(Rectangle rect, int rectBorderRadiusSize, Color rectColor) {
        rect.setArcHeight(rectBorderRadiusSize);
        rect.setArcWidth(rectBorderRadiusSize);

        if (rectColor != null)
            rect.setFill(rectColor);
    }

    public static void setRectStyle(Rectangle rect, int rectBorderRadiusSize, ColorPalette rectColor) {
        NodeSetter.setRectStyle(rect, rectBorderRadiusSize, rectColor.getColor());
    }
}
