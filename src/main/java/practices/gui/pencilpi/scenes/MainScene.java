package practices.gui.pencilpi.scenes;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import practices.files.DefaultFileReader;
import practices.files.DefaultFileWriter;
import practices.files.FileReader;
import practices.files.ResourceGetter;
import practices.gui.pencilpi.commons.Colors;
import practices.gui.pencilpi.commons.Sizes;
import practices.gui.pencilpi.commons.assets.Assets;
import practices.gui.setters.CommonNodes;
import practices.gui.setters.LayoutSetter;
import practices.gui.setters.NodeSetter;
import practices.gui.setters.StageSetter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.HashMap;
import java.util.function.Consumer;

public class MainScene {
    private static File currentFile=null;
    private static int currentLine=1;
    private static int currentColumn=1;
    private static int currentWords=0;
    private static int currentCharacters=0;
    private static KeyCode lastKeyCode;
    private static int lines=1;
    private static String oldNextCaretPositionCharacter="";
    private static String nextCaretPositionCharacter="";
    private static String oldPreviousCaretPositionCharacter="";
    private static String previousCaretPositionCharacter="";
    private static HashMap<Integer, Integer> linesLengthMap=new HashMap<>();

    public static void setCurrentFile(File file) {
        currentFile = file;
    }

    public static void setLastKeyCode(KeyCode keyCode) {
        lastKeyCode = keyCode;
    }

    public static String[] getCaretPositionCharacters(TextArea textArea){
        int caretPosition=textArea.getCaretPosition();
        String text=textArea.getText();
        String next="", previous="";

        if(text.isEmpty())
            return new String[]{previous,next};

        if(caretPosition==0){
            next=text.substring(0,1);
            previous=previousCaretPositionCharacter="";
        }

        else if(caretPosition==text.length()){
            next="";
            previous=text.substring(caretPosition-1,caretPosition);
        }

        else{
            next=text.substring(caretPosition,caretPosition+1);
            previous=text.substring(caretPosition-1,caretPosition);
        }

        return new String[]{previous,next};
    }

    public static void updateCaretPositionCharacters(TextArea textArea){
        oldNextCaretPositionCharacter=nextCaretPositionCharacter;
        oldPreviousCaretPositionCharacter=previousCaretPositionCharacter;

        String[] caretPositionCharacters=getCaretPositionCharacters(textArea);
        previousCaretPositionCharacter=caretPositionCharacters[0];
        nextCaretPositionCharacter=caretPositionCharacters[1];
    }
    
    public static void updateCurrentPositionLabel(Label currentPositionLabel, int line, int column){
        currentPositionLabel.setText("%d:%d".formatted(line, column));
    }

    public static void updateCurrentPositionLabel(Label currentPositionLabel){
        updateCurrentPositionLabel(currentPositionLabel, currentLine, currentColumn);
    }
    
    public static void updateCharactersLabel(Label charactersLabel, int numberCharacters) {
        charactersLabel.setText("Characters: %d".formatted(numberCharacters));
    }

    public static void updateCharactersLabel(Label charactersLabel) {
        updateCharactersLabel(charactersLabel,currentCharacters);
    }

    public static void updateWordsLabel(Label wordsLabel, int numberWords) {
        wordsLabel.setText("Words: %d".formatted(numberWords));
    }

    public static void updateWordsLabel(Label wordsLabel) {
        updateWordsLabel(wordsLabel,currentWords);
    }
    
    public static void resetDataLabels(TextArea textArea, Label currentPositionLabel, Label charactersLabel, Label wordsLabel) {
        String text= textArea.getText();
        String[] lines=text.split("\n");

        // Set cursor at start
        textArea.positionCaret(0);

        // Set line length counter
        for(int i=1;i<=lines.length;i++)
            linesLengthMap.put(i,lines[i-1].length());

        // Update current data
        currentLine=1;
        currentColumn=1;
        updateCurrentData(text);

        // Update position current label
        updateCurrentPositionLabel(currentPositionLabel);

        // Update characters label
        updateCharactersLabel(charactersLabel);

        // Update line label
        updateWordsLabel(wordsLabel);
    }

    public static void updateCurrentData(String text){
        currentWords=text.split("\\s+").length;
        currentCharacters=text.length();
    }

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
            NodeSetter.setLabelStyle(label, Sizes.Font.MENU, Colors.Dark.FONT, Colors.Dark.MENU_BG);

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
        // I couldn't get the styling to work properly
        /*
        for(MenuItem menuItem:new MenuItem[]{newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, calculatorMenuItem})
            NodeSetter.setMenuItemStyle(menuItem, Sizes.Font.MENU_ITEM, Colors.Dark.FONT, Colors.Dark.MENU_BG)
         */

        // Menu items actions
        calculatorMenuItem.setOnAction(_ -> {
            try {
                // Create a new stage
                Stage stage = new Stage();
                Scene scene = CalculatorScene.getScene();

                // Set window icon
                InputStream icon = assetsResourceGetter.getResourceAsStream(Assets.Image.WIN);
                StageSetter.setWindowIcon(stage, icon);

                // Set the stage
                stage.setTitle("Calculator");
                stage.setScene(scene);
                stage.setResizable(false);

                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

        // Footer border pane
        BorderPane footerBorderPane = new BorderPane();
        footerBorderPane.setPadding(CommonNodes.getInset(Sizes.Footer.PADDING_VERTICAL,Sizes.Footer.PADDING_HORIZONTAL));
        LayoutSetter.setBgColor(footerBorderPane, Colors.Dark.LABEL_BG);

        // Set footer border pane
        borderPane.setBottom(footerBorderPane);

        // Data labels HBox
        HBox dataLabelsHBox = new HBox();
        dataLabelsHBox.setSpacing(Sizes.Footer.PADDING_HORIZONTAL);
        
        // Set data labels HBox
        footerBorderPane.setLeft(dataLabelsHBox);

        // Data labels
        Label currentPositionLabel = new Label();
        Label charactersLabel = new Label();
        Label wordsLabel = new Label();
        
        // Update data labels content
        resetDataLabels(textArea, currentPositionLabel, charactersLabel, wordsLabel);

        // Set data labels
        dataLabelsHBox.getChildren().addAll(currentPositionLabel, charactersLabel, wordsLabel);
        
        // Made by label
        Label madeByLabel = new Label("Made by: ralvarezdev");

        // Set made by label
        footerBorderPane.setRight(madeByLabel);

        // Set label styles
        for(Label label:new Label[]{currentPositionLabel, charactersLabel, wordsLabel, madeByLabel})
            NodeSetter.setLabelStyle(label, Sizes.Font.TEXT, Colors.Dark.FONT);

        // Create open file chooser
        FileChooser openFileChooser = new FileChooser();
        openFileChooser.setTitle("Open File");

        // Create save file chooser
        FileChooser saveFileChooser = new FileChooser();
        saveFileChooser.setTitle("Save");
        saveFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));

        // Create file reader and file writer
        DefaultFileReader fileReader = new DefaultFileReader();
        DefaultFileWriter fileWriter = new DefaultFileWriter();

        // Set new menu item action
        newMenuItem.setOnAction(_ ->{
            textArea.clear();
            resetDataLabels(textArea, currentPositionLabel, charactersLabel, wordsLabel);
        });

        // Set open menu item action
        openMenuItem.setOnAction(_ -> {
            // Show open dialog
            File file=openFileChooser.showOpenDialog(new Stage());

            // Update current file
            setCurrentFile(file);

            try {
                StringBuilder content = fileReader.getFileContent(file);
                textArea.setText(content.toString());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
            resetDataLabels(textArea, currentPositionLabel, charactersLabel, wordsLabel);
        });

        // Write file lambda expression
        Runnable writeToFile = () -> {
            try {
                fileWriter.writeFileContent(currentFile, textArea.getText(), false);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        // Set save as menu item action
        saveAsMenuItem.setOnAction(_ -> {
            // Show save dialog
            File file=saveFileChooser.showSaveDialog(new Stage());

            // Update current file
            setCurrentFile(file);

            writeToFile.run();
        });

        // Set save menu item action
        saveMenuItem.setOnAction(_ -> {
            if(currentFile==null) {
                saveAsMenuItem.fire();
                return;
            }

            writeToFile.run();
        });

        // Move lines up
        Runnable moveLinesUp=()->{
            for(int i=currentLine+1;i<lines;i++)
                linesLengthMap.put(i, linesLengthMap.get(i+1));

            linesLengthMap.remove(lines);
        };

        // Move lines down
        Runnable moveLinesDown=()->{
            for(int i=currentLine;i<lines;i++)
                linesLengthMap.put(i+1, linesLengthMap.get(i));
        };

        // Update data labels based on text area content
        textArea.setOnKeyPressed(ev -> {
            KeyCode code =ev.getCode();

            // Update caret position characters
            updateCaretPositionCharacters(textArea);

            switch(code) {
                case LEFT:
                    if (oldPreviousCaretPositionCharacter.isEmpty())
                        break;

                    if (!oldPreviousCaretPositionCharacter.equals("\n"))
                        currentColumn--;

                    else {
                        currentLine--;
                        currentColumn = linesLengthMap.get(currentLine);
                    }
                    break;

                case RIGHT:
                    if (oldNextCaretPositionCharacter.isEmpty())
                        break;

                    if (!oldNextCaretPositionCharacter.equals("\n"))
                        currentColumn++;

                    else {
                        currentLine++;
                        currentColumn = 1;
                    }
                    break;

                case ENTER:
                    int diff=linesLengthMap.get(currentLine)-currentColumn+1;
                    moveLinesDown.run();
                    linesLengthMap.put(currentLine, currentColumn);
                    linesLengthMap.put(currentLine+1,diff);
                    currentColumn=1;

                    currentCharacters++;
                    currentLine++;
                    lines++;
                    break;

                case DOWN:
                    if (!oldNextCaretPositionCharacter.isEmpty())
                        if (currentColumn > linesLengthMap.get(++currentLine))
                            currentColumn = linesLengthMap.get(currentLine)+((currentLine==lines)?1:0);
                    break;

                case UP:
                    if (!oldPreviousCaretPositionCharacter.isEmpty())
                        if (currentColumn > linesLengthMap.get(--currentLine))
                            currentColumn = linesLengthMap.get(currentLine);
                    break;

                case BACK_SPACE:
                    if (oldPreviousCaretPositionCharacter.isEmpty())
                        break;

                    if (!oldPreviousCaretPositionCharacter.equals("\n")) {
                        if (oldPreviousCaretPositionCharacter.equals(" "))
                            currentWords--;

                        linesLengthMap.put(currentLine, --currentColumn-1);
                    }

                    else{
                        currentColumn = linesLengthMap.get(--currentLine);
                        linesLengthMap.put(currentLine, currentColumn-1+linesLengthMap.get(currentLine+1));
                        moveLinesUp.run();
                        lines--;
                    }

                    currentCharacters--;
                    break;

                case DELETE:
                    if (oldNextCaretPositionCharacter.isEmpty())
                        break;

                    if (!oldNextCaretPositionCharacter.equals("\n")) {
                        if (oldNextCaretPositionCharacter.equals(" "))
                            currentWords--;

                        linesLengthMap.put(currentLine, linesLengthMap.get(currentLine)-1);
                    }

                    else{
                        linesLengthMap.put(currentLine,currentColumn+ linesLengthMap.get(currentLine+1) - 1);
                        moveLinesUp.run();
                    }

                    currentCharacters--;
                    break;
                
                default:
                    if(code.isFunctionKey()||code.isMediaKey()||code.isModifierKey())
                        return;

                    linesLengthMap.put(currentLine, currentColumn++);

                    if(lastKeyCode==KeyCode.SPACE)
                        currentWords++;

                    currentCharacters++;
            }

            // Debug
            System.out.println(linesLengthMap);

            // Update last key code
            setLastKeyCode(ev.getCode());

            // Update data labels
            updateCurrentPositionLabel(currentPositionLabel);
            updateCharactersLabel(charactersLabel);
            updateWordsLabel(wordsLabel);
        });

        // Scene
        return new Scene(borderPane);
    }
}
