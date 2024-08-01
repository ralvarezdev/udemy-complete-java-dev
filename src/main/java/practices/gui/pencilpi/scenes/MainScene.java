package practices.gui.pencilpi.scenes;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import practices.files.ResourceGetter;
import practices.gui.pencilpi.commons.Colors;
import practices.gui.pencilpi.commons.Sizes;
import practices.gui.setters.CommonNodes;
import practices.gui.setters.LayoutSetter;
import practices.gui.setters.NodeSetter;

public class MainScene {
    public static Scene getScene(ResourceGetter assetsResourceGetter) {
        // Border pane
        BorderPane borderPane = new BorderPane();

        // Menu bar
        MenuBar menuBar = new MenuBar();
        NodeSetter.setMenuBarStyle(menuBar, Colors.Dark.MENU_BG);
        borderPane.setTop(menuBar);

        // Menu file
        Menu fileMenu = new Menu("");
        Label fileLabel=new Label("File");
        fileMenu.setGraphic(fileLabel);

        // Menu modes
        Menu modesMenu = new Menu("");
        Label modesLabel=new Label("Modes");
        modesMenu.setGraphic(modesLabel);

        // Set menu styles
        for(Menu menu:new Menu[]{fileMenu, modesMenu})
            NodeSetter.setMenuStyle(menu, Colors.Dark.MENU_BG);

        // Set menu label styles
        for(Label label:new Label[]{fileLabel, modesLabel})
            NodeSetter.setLabelStyle(label, Sizes.Font.MENU, Colors.Dark.FONT);

        // Set menu
        menuBar.getMenus().addAll(fileMenu, modesMenu);

        // Menu items
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem saveAsMenuItem = new MenuItem("Save As");

        // Menu modes items
        MenuItem calculatorMenuItem = new MenuItem("Calculator");

        // Set menu file items
        fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem);

        // Set menu modes items
        modesMenu.getItems().addAll(calculatorMenuItem);

        // Set menu item styles
        for(MenuItem menuItem:new MenuItem[]{newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, calculatorMenuItem})
            NodeSetter.setMenuItemStyle(menuItem, Sizes.Font.MENU_ITEM, Colors.Dark.FONT, Colors.Dark.MENU_BG);

        // Menu items actions
        calculatorMenuItem.setOnAction(_ -> {
            // Create a new stage
            Stage stage = new Stage();
            Scene scene = CalculatorScene.getScene();

            // Set the stage
            stage.setTitle("Calculator");
            stage.setMinHeight(Sizes.Stage.CALCULATOR_HEIGHT);
            stage.setMinWidth(Sizes.Stage.CALCULATOR_WIDTH);

            stage.setScene(scene);
            stage.show();
        });

        // Text area border pane
        BorderPane textAreaBorderPane=new BorderPane();
        LayoutSetter.setBgColor(textAreaBorderPane, Colors.Dark.STAGE_BG);
        textAreaBorderPane.setPadding(CommonNodes.getInset(Sizes.TextArea.PADDING));
        borderPane.setCenter(textAreaBorderPane);

        // Text area
        TextArea textArea = new TextArea();
        NodeSetter.setTextAreaStyle(textArea, Sizes.Font.TEXT, Colors.Dark.FONT, Colors.Dark.TEXT_AREA_BG);
        textAreaBorderPane.setCenter(textArea);

        // Labels
        HBox labelsHBox = new HBox();
        LayoutSetter.setBgColor(labelsHBox, Colors.Dark.LABEL_BG);

        // Add labels to the border pane
        borderPane.setBottom(labelsHBox);

        Label currentLabel = new Label("1:0");
        Label charactersLabel = new Label("Characters: 0");
        Label lineLabel = new Label("Words: 0");

        // Set label styles
        for(Label label:new Label[]{currentLabel, charactersLabel, lineLabel}) {
            label.setPadding(CommonNodes.getInset(Sizes.Footer.PADDING_VERTICAL, Sizes.Footer.PADDING_HORIZONTAL));
            NodeSetter.setLabelStyle(label, Sizes.Font.TEXT, Colors.Dark.FONT);
            labelsHBox.getChildren().add(label);
        }

        // Scene
        return new Scene(borderPane);
    }
}
