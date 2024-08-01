package practices.gui.pencilpi.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import practices.gui.commons.ColorPalette;
import practices.gui.pencilpi.commons.Colors;
import practices.gui.pencilpi.commons.Sizes;
import practices.gui.setters.CommonNodes;
import practices.gui.setters.LayoutSetter;
import practices.gui.setters.NodeSetter;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Function;

public class CalculatorScene {
    public static Scene getScene() {
        // Number and operators stacks
        final String error="ERROR";
        LinkedList<String> resultStack = new LinkedList<>();
        LinkedList<Double> numbersStack = new LinkedList<>();
        LinkedList<String> operatorsStack = new LinkedList<>();
        LinkedList<String> operatorsPostfixStack = new LinkedList<>();

        // Grid pane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(CommonNodes.getInset(Sizes.Calculator.PADDING));
        gridPane.setVgap(Sizes.Calculator.MAIN_GAP);
        gridPane.setHgap(Sizes.Calculator.MAIN_GAP);

        // Set grid pane
        LayoutSetter.setBgColor(gridPane, Colors.Dark.STAGE_BG);

        // Buttons container
        GridPane buttonsGridPane = new GridPane();
        buttonsGridPane.setHgap(Sizes.Calculator.BUTTONS_GAP);
        buttonsGridPane.setVgap(Sizes.Calculator.BUTTONS_GAP);
        gridPane.add(buttonsGridPane, 0, 2);

        // Delete and CE buttons
        Button deleteButton = new Button();
        deleteButton.setText("←");

        Button ceButton = new Button();
        ceButton.setText("CE");

        // Set delete and CE buttons
        buttonsGridPane.add(deleteButton, 0, 0, 2, 1);
        buttonsGridPane.add(ceButton, 2, 0, 2, 1);

        // Set delete and CE buttons style and size
        for(Button button:new Button[]{deleteButton, ceButton}) {
            NodeSetter.setBtnStyle(button, Sizes.Font.CALCULATOR_BUTTONS, Colors.Dark.FONT, Sizes.Button.BORDER_RADIUS, Colors.Dark.BUTTON_DELETE_BG);
            button.setMinWidth(Sizes.Button.DELETE_WIDTH);
            button.setMinHeight(Sizes.Button.DELETE_HEIGHT);
        }

        // Buttons text
        String[] buttonsText = new String[]{
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        // Buttons lists
        LinkedList<Button> numberButtons = new LinkedList<>();
        LinkedList<Button> operatorButtons = new LinkedList<>();

        for(int i=0;i<buttonsText.length;i++) {
            // Button
            Button button=new Button(buttonsText[i]);
            boolean isNumber=buttonsText[i].matches("[0-9]|\\.");

            // Set buttons style
            ColorPalette bgColor=isNumber ? Colors.Dark.BUTTON_NUMBER_BG : Colors.Dark.BUTTON_OPERATOR_BG;
            NodeSetter.setBtnStyle(button, Sizes.Font.CALCULATOR_BUTTONS, Colors.Dark.FONT, Sizes.Button.BORDER_RADIUS, bgColor);

            // Push to stack
            if(isNumber)
                numberButtons.push(button);
            else
                operatorButtons.push(button);

            // Set buttons size
            button.setMinWidth(Sizes.Button.OPERATOR_WIDTH);
            button.setMinHeight(Sizes.Button.OPERATOR_HEIGHT);

            // Set buttons
            buttonsGridPane.add(button, i%4, i/4+1);
        }

        // Operations label
        Label operations = new Label("");

        // Set operations label style and size
        NodeSetter.setLabelStyle(operations, Sizes.Font.CALCULATOR_OPERATIONS, Colors.Dark.FONT, Colors.Dark.RESULT_BG);
        operations.setMinWidth(Sizes.Calculator.RESULT_WIDTH);
        operations.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(operations, 0, 0);

        // Result label
        Label result = new Label("0");

        // Set result label style and size
        NodeSetter.setLabelStyle(result, Sizes.Font.CALCULATOR_RESULT, Colors.Dark.FONT, Colors.Dark.RESULT_BG);
        result.setMinWidth(Sizes.Calculator.RESULT_WIDTH);
        result.setAlignment(Pos.CENTER_RIGHT);
        gridPane.add(result, 0, 1);

        // Update operations label lambda expression
        Runnable updateOperations=()-> operations.setText(String.join("", resultStack));

        // Update result label lambda expression
        Runnable updateResult=()-> {
            if(resultStack.isEmpty()) {
                result.setText("0");
                return;
            }

            result.setText(String.join("", resultStack));
        };

        // Set delete button on click event
        deleteButton.setOnAction(_ -> {
            if(result.getText().equals(error)||resultStack.isEmpty())
                return;

            resultStack.removeLast();
            updateResult.run();
        });

        // Set CE button on click event
        ceButton.setOnAction(_ -> {
            resultStack.clear();
            result.setText("0");
            operations.setText("");
        });

        // Operator precedence
        Function<String, Integer> precedence = operator -> switch (operator) {
            case "+", "-" -> 1;
            case "*", "/", "%" -> 2;
            default -> 0;
        };

        // Set number buttons on click event
        for(Button button:numberButtons) {
            button.setOnAction(_ -> {
                if(result.getText().equals(error))
                    return;

                resultStack.addLast(button.getText());
                updateResult.run();
            });
        }

        // Set operator buttons on click event
        for(Button button:operatorButtons) {
            button.setOnAction(_ -> {
                if(result.getText().equals(error))
                    return;

                if(!button.getText().equals("=")) {
                    resultStack.addLast(button.getText());
                    updateResult.run();
                    return;
                }

                // Update operations label
                updateOperations.run();

                // Check if the result stack is empty
                if(resultStack.isEmpty())
                    return;

                // Convert to postfix notation
                StringBuilder tempNumber= new StringBuilder();
                boolean isTempNumberDecimal=false;

                // Parse first number
                try {
                    double firstNumber = Double.parseDouble(resultStack.removeFirst());
                    numbersStack.push(firstNumber);
                }
                catch(NumberFormatException e) {
                    result.setText(error);
                    return;
                }

                for(String token:resultStack) {
                    // Check if the token is a number
                    if(token.matches("[0-9]")) {
                        tempNumber.append(token);
                        continue;
                    }

                    // Check if the token is a decimal
                    if(token.matches("\\.")) {
                        if(isTempNumberDecimal) {
                            result.setText(error);
                            return;
                        }

                        isTempNumberDecimal=true;
                        tempNumber.append(token);
                        continue;
                    }

                    // Store temp number, if exists
                    if(!tempNumber.toString().isEmpty()) {
                        numbersStack.push(Double.parseDouble(tempNumber.toString()));
                        tempNumber.setLength(0);
                        isTempNumberDecimal=false;
                    }

                    // Store operator
                    while(!operatorsStack.isEmpty() && precedence.apply(operatorsStack.peek())>=precedence.apply(token))
                            operatorsPostfixStack.addLast(operatorsStack.pop());

                    operatorsPostfixStack.push(token);
                }

                if(!tempNumber.isEmpty())
                    numbersStack.push(Double.parseDouble(tempNumber.toString()));

                // Calculate the result
                double operationResult;

                while(!operatorsPostfixStack.isEmpty()) {
                    String operator=operatorsPostfixStack.pop();

                    if(numbersStack.size()<2) {
                        result.setText(error);
                        return;
                    }

                    double number1=numbersStack.pop();
                    double number2=numbersStack.pop();

                    operationResult=switch(operator) {
                        case "+"->number2+number1;
                        case "-"->number2-number1;
                        case "*"->number2*number1;
                        case "/"-> number2 / number1;
                        default->0;
                    };

                    numbersStack.push(operationResult);
                }

                // Set result
                resultStack.clear();
                resultStack.push(String.valueOf(numbersStack.pop()));
                updateResult.run();

                operatorsStack.clear();
                numbersStack.clear();
                operatorsPostfixStack.clear();
            });
        }

        // Scene
        return new Scene(gridPane);
    }
}
