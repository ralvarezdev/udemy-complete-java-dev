package practices.traversinggame.scenes;

import java.io.InputStream;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import practices.MissingResourceFileException;
import practices.traversinggame.commons.AssetStyles;
import practices.traversinggame.commons.Assets;
import practices.traversinggame.commons.ColorStyles;
import practices.traversinggame.commons.CommonNodes;
import practices.traversinggame.commons.Coords;
import practices.traversinggame.commons.KeyDirections;
import practices.traversinggame.commons.SizeStyles;
import practices.traversinggame.commons.Sizes;
import practices.traversinggame.managers.ResourceManager;
import practices.traversinggame.setters.SetLayout;
import practices.traversinggame.setters.SetNode;
import practices.traversinggame.setters.SetScene;

public class MatrixScene {
	private static KeyDirections currKeyDir;
	private static Integer currKeyDirCounter;

	private static Integer[] playerCoords = null;
	private static Double[] playerCoordsPx = null;

	private static Integer[] glassesCoords = null;
	private static Double[] glassesCoordsPx = null;

	private static int minWidth;
	private static int minHeight;

	private static boolean checkQuarter(int quarter) {
		return quarter >= 0 && quarter <= 3;
	}

	private static Integer getOppositeQuarter(int quarter) {
		if (checkQuarter(quarter))
			return null;

		return (quarter + 2) % 4;
	}

	private static Integer[] getQuarterCornerCell(int quarter, int cols, int rows) {
		if (!checkQuarter(quarter))
			return null;

		Integer[] coords = new Integer[2];

		switch (quarter) {
		case 0 -> {
			coords[Coords.X] = cols - 1;
			coords[Coords.Y] = 0;
		}
		case 1 -> {
			coords[Coords.X] = 0;
			coords[Coords.Y] = 0;
		}
		case 2 -> {
			coords[Coords.X] = 0;
			coords[Coords.Y] = rows - 1;
		}
		case 3 -> {
			coords[Coords.X] = cols - 1;
			coords[Coords.Y] = rows - 1;
		}
		}

		return coords;
	}

	private static Double[] getCoordsPx(Integer[] coords) {
		Double[] coordsPx = new Double[2];
		int[] coordsList = new int[] { Coords.X, Coords.Y };

		for (int coord : coordsList)
			coordsPx[coord] = (double) coords[coord] * Sizes.CELL_SIZE + (coords[coord] - 1) * Sizes.CELL_PADDING;

		return coordsPx;
	}

	private static void getPlayerCoordsPx() {
		playerCoordsPx = getCoordsPx(playerCoords);
	}

	private static void getGlassesCoordsPx() {
		glassesCoordsPx = getCoordsPx(glassesCoords);
	}

	private static Double[] getCoordsPx(Integer[] coords, int quarter, int cols, int rows) {
		getQuarterCornerCell(quarter, cols, rows);
		return getCoordsPx(coords);
	}

	private static void getPlayerCoordsPx(int quarter, int cols, int rows) {
		playerCoordsPx = getCoordsPx(playerCoords, quarter, cols, rows);
	}

	private static void getGlassesCoordsPx(int quarter, int cols, int rows) {
		glassesCoordsPx = getCoordsPx(glassesCoords, quarter, cols, rows);
	}

	private static double getCellTranslateX(Double[] coordsPx, double minWidth) {
		return coordsPx[Coords.X] - minWidth / 2 + Sizes.CELL_SIZE - Sizes.CELL_PADDING;
	}

	private static double getCellTranslateY(Double[] coordsPx, double minHeight) {
		return coordsPx[Coords.Y] - minHeight / 2 + Sizes.CELL_SIZE - Sizes.CELL_PADDING;
	}

	private static Scene getScene(MatrixSceneTypes type) throws MissingResourceFileException {
		// Reset static values
		currKeyDir = null;
		currKeyDirCounter = 0;

		playerCoords = new Integer[] { 0, 0 };
		glassesCoords = new Integer[] { 0, 0 };

		// Tale Pane which contains cells
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(CommonNodes.getInset(Sizes.MATRIX_PADDING));
		gridPane.setVgap(Sizes.CELL_PADDING);
		gridPane.setHgap(Sizes.CELL_PADDING);

		// Player image
		InputStream playerAsset = ResourceManager.INSTANCE.getAsset(Assets.PLAYER_IMAGE);
		Image playerImage = new Image(playerAsset);
		ImageView playerImageView = new ImageView(playerImage);

		playerImageView.setFitWidth(Sizes.CELL_SIZE);
		playerImageView.setFitHeight(Sizes.CELL_SIZE);

		// Add cells to grid pane
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
		minWidth = MatrixSceneTypes.getMinWidth(type);
		minHeight = MatrixSceneTypes.getMinHeight(type);

		getQuarterCornerCell(AssetStyles.PLAYER_INITIAL_QUARTER, rows, cols);
		getPlayerCoordsPx();

		playerImageView.setTranslateX(getCellTranslateX(playerCoordsPx, minWidth));
		playerImageView.setTranslateY(getCellTranslateY(playerCoordsPx, minHeight));

		// Grid pane container
		StackPane stackPane = new StackPane(gridPane, playerImageView);
		stackPane.setMinSize(minWidth, minHeight);

		SetLayout.setBgColor(stackPane);

		// Glasses cell
		glassesCoords = getQuarterCornerCell(3, rows, cols);
		getGlassesCoordsPx();

		// Scene
		Scene scene = new Scene(stackPane);

		SetScene.setDefaultStyles(scene);

		// Add player image events
		scene.setOnKeyPressed(event -> {
			KeyCode eventCode = event.getCode();
			KeyDirections eventKeyDir = KeyDirections.getKeyDirection(eventCode);

			if (eventKeyDir == null) {
				System.err.println("UNEXPECTED VALUE: " + currKeyDir);
				return;
			}

			System.out.println("RECEIVED MOVE: " + eventKeyDir);

			if (currKeyDir != eventKeyDir) {
				currKeyDir = eventKeyDir;
				currKeyDirCounter = 1;

			} else if (currKeyDirCounter >= KeyDirections.MAX_DIR) {
				System.err.println("INVALID MOVE: Too many " + eventKeyDir.getDirection() + " movements.");
				currKeyDirCounter++;
				return;

			} else
				currKeyDirCounter++;

			switch (currKeyDir) {
			case MOVE_UP -> {
				if (playerCoords[Coords.Y] > 0)
					playerCoords[Coords.Y]--;
			}
			case MOVE_RIGHT -> {
				if (playerCoords[Coords.X] < cols - 1)
					playerCoords[Coords.X]++;
			}
			case MOVE_DOWN -> {
				if (playerCoords[Coords.Y] < rows - 1)
					playerCoords[Coords.Y]++;
			}
			case MOVE_LEFT -> {
				if (playerCoords[Coords.X] > 0)
					playerCoords[Coords.X]--;
			}
			}

			// Update player coordinates
			getPlayerCoordsPx();
			playerImageView.setTranslateX(getCellTranslateX(playerCoordsPx, minWidth));
			playerImageView.setTranslateY(getCellTranslateY(playerCoordsPx, minHeight));

			if (playerCoords[Coords.X] == glassesCoords[Coords.X] && playerCoords[Coords.Y] == glassesCoords[Coords.Y])
				System.out.println("GLASSES REACHED... YOU WINNED");
		});

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
