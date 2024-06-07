package practices.traversinggame.scenes;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import practices.MissingResourceFileException;
import practices.traversinggame.commons.Assets;
import practices.traversinggame.commons.ColorStyles;
import practices.traversinggame.commons.CommonNodes;
import practices.traversinggame.commons.SizeStyles;
import practices.traversinggame.commons.Sizes;
import practices.traversinggame.commons.Texts;
import practices.traversinggame.managers.ResourceManager;
import practices.traversinggame.setters.SetLayout;
import practices.traversinggame.setters.SetNode;
import practices.traversinggame.setters.SetScene;
import practices.traversinggame.setters.SetStage;

public class MainScene {
	// Main image
	private final static int MAIN_IMAGE_HEIGHT = 200;

	private static <T extends Event> EventHandler<T> getOnMatrixSceneClicked(Stage mainStage, MatrixSceneTypes type) {
		return (param) -> {
			try {
				Stage matrixStage = new Stage();

				matrixStage.setTitle(MatrixSceneTypes.getTitle(type));
				SetStage.setWindowIcon(matrixStage);

				Scene matrixScene = MatrixSceneTypes.getScene(type);
				matrixStage.setScene(matrixScene);

				double minWidth = MatrixSceneTypes.getMinWidth(type);
				double minHeight = MatrixSceneTypes.getMinHeight(type);

				matrixStage.setOnCloseRequest(innerEvent -> mainStage.show());

				mainStage.hide();
				matrixStage.show();

				SetStage.setDiffMinSize(matrixStage, matrixScene, minWidth, minHeight);

			} catch (MissingResourceFileException e) {
				System.err.println(e);
				System.exit(-1);
			}
		};
	}

	public static Scene getScene(Stage stage) throws MissingResourceFileException {
		// Title label
		Label titleLabel = new Label(Texts.TITLE);
		SetNode.setLabelStyle(titleLabel, SizeStyles.BIG, ColorStyles.DARK_FONT);

		// Main image
		Image mainImage = new Image(ResourceManager.INSTANCE.getAsset(Assets.MAIN_IMAGE));
		ImageView mainImageView = new ImageView(mainImage);
		mainImageView.setFitHeight(MAIN_IMAGE_HEIGHT);
		mainImageView.setPreserveRatio(true);

		// Buttons
		Button noviceMode = new Button(Texts.NOVICE_MODE);
		Button normalMode = new Button(Texts.NORMAL_MODE);
		Button advancedMode = new Button(Texts.ADVANCED_MODE);
		Button expertMode = new Button(Texts.EXPERT_MODE);
		Button[] btns = new Button[] { noviceMode, normalMode, advancedMode, expertMode };

		// Vertical box aligned at the center
		VBox centerVBox = new VBox(mainImageView, titleLabel, noviceMode, normalMode, advancedMode, expertMode);
		centerVBox.setAlignment(Pos.CENTER);

		// Add title margin
		VBox.setMargin(titleLabel, CommonNodes.getBottomInset(Sizes.TITLE_MARGIN_BOTTOM));

		// Add buttons style, size and margin
		for (Button btn : btns) {
			String btnStyle = SetNode.getBtnStyle(SizeStyles.MEDIUM, ColorStyles.DARK_FONT, SizeStyles.BIG,
					ColorStyles.DARK_NODE_BG);
			String btnFocusStyle = SetNode.getBtnStyle(SizeStyles.MEDIUM, ColorStyles.DARK_FONT_HOVER, SizeStyles.BIG,
					ColorStyles.DARK_NODE_HOVER_BG);

			btn.setStyle(btnStyle);
			btn.setPrefSize(Sizes.BIG_BTN_WIDTH, Sizes.BIG_BTN_HEIGHT);
			VBox.setMargin(btn, CommonNodes.getBottomInset(Sizes.BTN_MARGIN_BOTTOM));

			// Set buttons on mouse entered and exited events
			btn.setOnMouseExited(event -> btn.setStyle(btnStyle));
			btn.setOnMouseEntered(event -> btn.setStyle(btnFocusStyle));
		}

		// Footer label
		Label footerLabel = new Label(Texts.FOOTER);
		SetNode.setLabelStyle(footerLabel, SizeStyles.MEDIUM, ColorStyles.DARK_FONT);

		// Horizontal box aligned at the center
		HBox bottomHBox = new HBox(footerLabel);
		bottomHBox.setAlignment(Pos.CENTER_RIGHT);

		// Add footer label margin
		HBox.setMargin(footerLabel, CommonNodes.getInset(0, Sizes.FOOTER_MARGIN_RIGHT, Sizes.FOOTER_MARGIN_BOTTOM, 0));

		// Main container
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(Sizes.MAX_WIDTH, Sizes.MAX_HEIGHT);
		borderPane.setCenter(centerVBox);
		borderPane.setBottom(bottomHBox);

		SetLayout.setBgColor(borderPane);

		// Scene
		Scene scene = new Scene(borderPane);

		SetScene.setDefaultStyles(scene);

		// Set buttons on mouse clicked events
		noviceMode.setOnMouseClicked(getOnMatrixSceneClicked(stage, MatrixSceneTypes.NOVICE));
		normalMode.setOnMouseClicked(getOnMatrixSceneClicked(stage, MatrixSceneTypes.NORMAL));
		advancedMode.setOnMouseClicked(getOnMatrixSceneClicked(stage, MatrixSceneTypes.ADVANCED));
		expertMode.setOnMouseClicked(getOnMatrixSceneClicked(stage, MatrixSceneTypes.EXPERT));

		return scene;
	}
}
