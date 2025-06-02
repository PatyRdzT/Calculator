package com.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/** This class is used to control the buttons and operations of the calculator. */
public class Controller {
    // This is a variable that indicates if we are starting an input (new operation).
    private boolean startNewInput = true;
    // This string stores the current operator.
    private String currentOperator = "";
    // This double stores the first operand, which defaults to zero.
    private double firstOperand = 0;

    // These are all the buttons on the calculator.
    @FXML private Button clear;
    @FXML private Button division;
    @FXML private Button seven;
    @FXML private Button eight;
    @FXML private Button nine;
    @FXML private Button multiplication;
    @FXML private Button four;
    @FXML private Button five;
    @FXML private Button six;
    @FXML private Button subtraction;
    @FXML private Button one;
    @FXML private Button two;
    @FXML private Button three;
    @FXML private Button addition;
    @FXML private Button zero;
    @FXML private Button dot;
    @FXML private Button answer;
    @FXML private TextField operation;

    /** This method initializes all actions for each button. */
    public void initialize() {
        zero.setOnAction(e -> appendNumber("0"));
        one.setOnAction(e -> appendNumber("1"));
        two.setOnAction(e -> appendNumber("2"));
        three.setOnAction(e -> appendNumber("3"));
        four.setOnAction(e -> appendNumber("4"));
        five.setOnAction(e -> appendNumber("5"));
        six.setOnAction(e -> appendNumber("6"));
        seven.setOnAction(e -> appendNumber("7"));
        eight.setOnAction(e -> appendNumber("8"));
        nine.setOnAction(e -> appendNumber("9"));

        addition.setOnAction(e -> setOperator("+"));
        subtraction.setOnAction(e -> setOperator("-"));
        multiplication.setOnAction(e -> setOperator("*"));
        division.setOnAction(e -> setOperator("/"));

        dot.setOnAction(e -> appendDecimal());
        clear.setOnAction(e -> clearOperation());
        answer.setOnAction(e -> calculateResult());
    }

    /** This method adds a number to the operation. If we start a new operation, we set a
     *  default text and mark that a new operation is no longer being started. And if
     *  the operation has already been started, we add the number to the text that the
     *  operation already contains. */
    private void appendNumber(String number) {
        if (startNewInput) {
            operation.setText("");
            startNewInput = false;
        }

        operation.setText(operation.getText() + number);
    }

    /** This method sets the current operator. First, if the operation already contains something,
     *  we calculate the result, but if not, we set the first operand as the operation text,
     *  and we mark de current operator (the parameter) to indicate that we are not starting a
     *  new operation. */
    private void setOperator(String op) {
        if (!startNewInput) {
            calculateResult();
        }
        firstOperand = Double.parseDouble(operation.getText());
        currentOperator = op;
        startNewInput = true;
    }

    /** This method adds a decimal number to the operation. If we start a new operation,
     *  we set the default text to 0, and indicate that a new operation is no longer
     *  being started. And if the operation has already been started, and there is no dot in the
     *  operation, we add the dot to the text that the operation already contains. */
    private void appendDecimal() {
        if (startNewInput) {
            operation.setText("0.");
            startNewInput = false;
            return;
        }

        if (!operation.getText().contains(".")) {
            operation.setText(operation.getText() + ".");
        }
    }

    /** This method clears the operation, setting the text to 0, the first operand to 0,
     *  the current operator to the default string, and we mark that now we are starting a new
     *  operation. */
    private void clearOperation() {
        operation.setText("");
        firstOperand = 0;
        currentOperator = "";
        startNewInput = true;
    }

    /** This method calculates the result of the operation. If we don't have an operator,
     * we return it, and if we do, we perform the operation specified by the operator and
     * append the result to the operation text. We indicate that we are now starting a new
     * operation and set the current operator as the default string. */
    private void calculateResult() {
        if (currentOperator.isEmpty()) return;

        double secondOperand = Double.parseDouble(operation.getText());
        double result = switch (currentOperator) {
            case "+" -> firstOperand + secondOperand;
            case "-" -> firstOperand - secondOperand;
            case "*" -> firstOperand * secondOperand;
            case "/" -> firstOperand / secondOperand;
            default -> 0;
        };

        operation.setText(String.valueOf(result));
        startNewInput = true;
        currentOperator = "";
    }
}