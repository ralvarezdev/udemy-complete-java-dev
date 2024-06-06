package practices.traversinggame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practices.MissingResourceFileException;

public class MainScene {
	// Images
	private final static String MAIN_IMAGE = "dinosaur003.png";

	// Texts
	private final static String TITLE = "TRAVERSING GAME";
	private final static String NOVICE_MODE = "NOVICE";
	private final static String NORMAL_MODE = "NORMAL";
	private final static String EXPERT_MODE = "EXPERT";

	// Scene and Nodes Size
	private final static int MAX_WIDTH = 1280;
	private final static int MAX_HEIGHT = 720;

	private final static int MAIN_IMAGE_HEIGHT = 200;

	private final static int BTN_WIDTH = 400;
	private final static int BTN_HEIGHT = 30;
	private final static int BTN_BORDER_RADIUS = 5;

	// Font Sizes
	private final static int TITLE_FONT_SIZE = 50;
	private final static int BTN_FONT_SIZE = 30;
	private final static int FOOTER_FONT_SIZE = 30;

	// Margins
	private final static int TITLE_MARGIN_BOTTOM = 20;
	private final static int BTN_MARGIN_BOTTOM = 10;
	private final static int FOOTER_MARGIN_RIGHT = 40;
	private final static int FOOTER_MARGIN_BOTTOM = 20;

	public static String getTitle() {
		return TITLE;
	}

	public static Scene generateMainScene() throws MissingResourceFileException {
		// Title label
		Label titleLabel = new Label(TITLE);
		SetNode.setLabelStyle(titleLabel, ColorManager.GREEN_LIGHT, TITLE_FONT_SIZE);

		// Main image
		Image mainImage = new Image(ResourceManager.INSTANCE.getAsset(MAIN_IMAGE));
		ImageView mainImageView = new ImageView(mainImage);
		mainImageView.setFitHeight(MAIN_IMAGE_HEIGHT);
		mainImageView.setPreserveRatio(true);

		// Buttons
		Button noviceMode = new Button(NOVICE_MODE);
		Button normalMode = new Button(NORMAL_MODE);
		Button expertMode = new Button(EXPERT_MODE);
		Button[] btns = new Button[] { noviceMode, normalMode, expertMode };

		// Vertical box aligned at the center
		VBox centerVBox = new VBox(mainImageView, titleLabel, noviceMode, normalMode, expertMode);
		centerVBox.setAlignment(Pos.CENTER);

		// Add title margin
		VBox.setMargin(titleLabel, CommonNodes.getBottomInset(TITLE_MARGIN_BOTTOM));

		// Add buttons style, size and margin
		for (Button btn : btns) {
			String btnStyle = SetNode.getBtnStyle(ColorManager.GREY_DARK, ColorManager.GREEN_LIGHT, BTN_FONT_SIZE,
					BTN_BORDER_RADIUS);
			String btnFocusStyle = SetNode.getBtnStyle(ColorManager.GREEN_LIGHT, ColorManager.GREY_DARK, BTN_FONT_SIZE,
					BTN_BORDER_RADIUS);

			btn.setStyle(btnStyle);
			btn.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
			VBox.setMargin(btn, CommonNodes.getBottomInset(BTN_MARGIN_BOTTOM));

			// Set button styles on events
			btn.setOnMouseExited(e -> btn.setStyle(btnStyle));
			btn.setOnMouseEntered(e -> btn.setStyle(btnFocusStyle));
		}

		// Footer label
		Label footerLabel = new Label("MADE BY RALVAREZDEV");
		SetNode.setLabelStyle(footerLabel, ColorManager.GREY_LIGHT, FOOTER_FONT_SIZE);

		// Horizontal box aligned at the center
		HBox bottomHBox = new HBox(footerLabel);
		bottomHBox.setAlignment(Pos.CENTER_RIGHT);

		// Add footer label margin
		HBox.setMargin(footerLabel, CommonNodes.getInset(0, FOOTER_MARGIN_RIGHT, FOOTER_MARGIN_BOTTOM, 0));

		// Main container
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
		borderPane.setCenter(centerVBox);
		borderPane.setBottom(bottomHBox);

		SetLayout.setBgColor(borderPane);

		// Scene
		Scene scene = new Scene(borderPane);

		SetScene.setDefaultStyles(scene);

		return scene;
	}
}
