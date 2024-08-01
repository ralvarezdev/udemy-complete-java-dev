package practices.gui.pencilpi.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class CalculatorScene {
    public static Scene getScene() {
        // Border pane
        BorderPane borderPane = new BorderPane();

        // Text result container
        Label result = new Label("0");
        result.setAlignment(Pos.CENTER_RIGHT);
        BorderPane.setAlignment(result, Pos.CENTER_RIGHT);
        BorderPane.setMargin(result, new Insets(10, 10, 10, 10));
        borderPane.setTop(result);

        // Buttons
        Button deleteButton = new Button();
        deleteButton.setText("←");

        Button ceButton = new Button();
        ceButton.setText("CE");

        Button remainderButton = new Button();
        remainderButton.setText("%");

        Button divideButton = new Button();
        divideButton.setText("÷");

        Button multiplyButton = new Button();
        multiplyButton.setText("*");

        Button subtractButton = new Button();
        subtractButton.setText("-");

        Button addButton = new Button();
        addButton.setText("+");


        // Buttons container
        GridPane buttonsContainer = new GridPane();
        GridPane.setColumnIndex(deleteButton, 0);
        GridPane.setColumnIndex(ceButton, 1);
        //GridPane.setColumnIndex();
        GridPane.setColumnIndex(ceButton, 1);


        // Delete-related buttons container
        HBox deleteButtonsContainer = new HBox(deleteButton, ceButton);
        borderPane.setCenter(deleteButtonsContainer);
        HBox.setHgrow(deleteButtonsContainer, Priority.ALWAYS);

        // Make operation-related buttons fill the parent container width
        HBox.setHgrow(deleteButton, Priority.ALWAYS);
        HBox.setHgrow(ceButton, Priority.ALWAYS);
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        ceButton.setMaxWidth(Double.MAX_VALUE);

        // Operation-related buttons container
        BorderPane operationButtonsContainer = new BorderPane();
        borderPane.setBottom(operationButtonsContainer);

        // Buttons
        String[] buttons = new String[]{
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        // Scene
        Scene scene = new Scene(borderPane);

        return scene;
    }
}
