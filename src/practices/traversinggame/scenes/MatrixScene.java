package practices.traversinggame.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import practices.MissingResourceFileException;
import practices.traversinggame.commons.ColorStyles;
import practices.traversinggame.commons.CommonNodes;
import practices.traversinggame.commons.SizeStyles;
import practices.traversinggame.commons.Sizes;
import practices.traversinggame.setters.SetLayout;
import practices.traversinggame.setters.SetNode;
import practices.traversinggame.setters.SetScene;

public class MatrixScene {

	/*
	 * private static int getQuarter(int x, int y) {
	 * 
	 * }
	 */

	private static Scene getScene(MatrixSceneTypes type) throws MissingResourceFileException {
		// Tale Pane which contains cells
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(CommonNodes.getInset(Sizes.MATRIX_PADDING));
		gridPane.setVgap(Sizes.CELL_PADDING);
		gridPane.setHgap(Sizes.CELL_PADDING);

		// Add cells to the tale pane
		Integer rows = MatrixSceneTypes.getRows(type);
		Integer cols = MatrixSceneTypes.getCols(type);

		if (rows == null || cols == null) {
			System.err.println("Missing rows/cols for the given matrix.");
			System.exit(-1);
		}

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				Rectangle rectCell = new Rectangle();
				rectCell.setWidth(Sizes.CELL_SIZE);
				rectCell.setHeight(Sizes.CELL_SIZE);

				SetNode.setRectStyle(rectCell, SizeStyles.MEDIUM, ColorStyles.DARK_NODE_BG);
				gridPane.add(rectCell, j, i);
			}

		// Main container
		double minWidth = MatrixSceneTypes.getMinWidth(type);
		double minHeight = MatrixSceneTypes.getMinHeight(type);

		StackPane stackPane = new StackPane(gridPane);
		stackPane.setMinSize(minWidth, minHeight);

		SetLayout.setBgColor(stackPane);

		// Scene
		Scene scene = new Scene(stackPane);

		SetScene.setDefaultStyles(scene);

		return scene;
	}

	public static Scene getNoviceScene() throws MissingResourceFileException {
		return getScene(MatrixSceneTypes.NOVICE);
	}

	public static Scene getNormalScene() throws MissingResourceFileException {
		return getScene(MatrixSceneTypes.NORMAL);
	}

	public static Scene getAdvancedScene() throws MissingResourceFileException {
		return getScene(MatrixSceneTypes.ADVANCED);
	}

	public static Scene getExpertScene() throws MissingResourceFileException {
		return getScene(MatrixSceneTypes.EXPERT);
	}
}
