package practices.traversinggame.scenes;

import javafx.scene.Scene;
import practices.MissingResourceFileException;
import practices.traversinggame.commons.Sizes;
import practices.traversinggame.commons.Texts;

public enum MatrixSceneTypes {
	MAIN, NOVICE, NORMAL, ADVANCED, EXPERT;

	// Novice scene
	private static final int NOVICE_SCENE_ROWS = 5;
	private static final int NOVICE_SCENE_COLS = 5;

	// Normal scene
	private static final int NORMAL_SCENE_ROWS = 7;
	private static final int NORMAL_SCENE_COLS = 10;

	// Advanced scene
	private static final int ADVANCED_SCENE_ROWS = 10;
	private static final int ADVANCED_SCENE_COLS = 20;

	// Expert scene
	private static final int EXPERT_SCENE_ROWS = 15;
	private static final int EXPERT_SCENE_COLS = 30;

	public static Integer getCols(MatrixSceneTypes type) {
		return switch (type) {
		case NOVICE -> NOVICE_SCENE_COLS;
		case NORMAL -> NORMAL_SCENE_COLS;
		case ADVANCED -> ADVANCED_SCENE_COLS;
		case EXPERT -> EXPERT_SCENE_COLS;
		default -> null;
		};
	}

	public static Integer getRows(MatrixSceneTypes type) {
		return switch (type) {
		case NOVICE -> NOVICE_SCENE_ROWS;
		case NORMAL -> NORMAL_SCENE_ROWS;
		case ADVANCED -> ADVANCED_SCENE_ROWS;
		case EXPERT -> EXPERT_SCENE_ROWS;
		default -> null;
		};
	}

	public static Integer getMinWidth(MatrixSceneTypes type) {
		Integer cols = getCols(type);
		if (cols == null)
			return null;

		return cols * Sizes.CELL_SIZE + (cols - 1) * Sizes.CELL_PADDING + 2 * Sizes.MATRIX_PADDING;
	}

	public static Integer getMinHeight(MatrixSceneTypes type) {
		Integer rows = getRows(type);
		if (rows == null)
			return null;

		return rows * Sizes.CELL_SIZE + (rows - 1) * Sizes.CELL_PADDING + 2 * Sizes.MATRIX_PADDING;
	}

	public static String getTitle(MatrixSceneTypes type) {
		return switch (type) {
		case NOVICE -> Texts.NOVICE_MODE;
		case NORMAL -> Texts.NORMAL_MODE;
		case ADVANCED -> Texts.ADVANCED_MODE;
		case EXPERT -> Texts.EXPERT_MODE;
		default -> null;
		};
	}

	public static Scene getScene(MatrixSceneTypes type) throws MissingResourceFileException {
		return switch (type) {
		case NOVICE -> MatrixScene.getNoviceScene();
		case NORMAL -> MatrixScene.getNormalScene();
		case ADVANCED -> MatrixScene.getAdvancedScene();
		case EXPERT -> MatrixScene.getExpertScene();
		default -> null;
		};
	}
}
