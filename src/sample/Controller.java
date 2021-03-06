package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;


public class Controller {
    @FXML
    public TextField txtData;
    @FXML
    private Label labelMemory;

    private Calculator calculator;
    private boolean isSecondNumber;

    public Controller() {
        txtData = new TextField();
        calculator = new Calculator();
        isSecondNumber = false;
    }

    private String delZeroAtEnd(String input) // Delete last zero
    {
        return input.endsWith(".0") ? input.substring(0, input.length() - 2) : input;
    }
//      new Alert(Alert.AlertType.CONFIRMATION, txtData.getText()).show();


    public void btnNumberClick(ActionEvent actionEvent) {
        String btnText = ((Button) actionEvent.getSource()).getText();
        txtData.setText(txtData.getText().equals("0") || (isSecondNumber == true) ? btnText : txtData.getText() + btnText);
        isSecondNumber = false;
        calculator.isFirstCalculation = true;

    }

    public void btnCommaClick(ActionEvent actionEvent) {
        txtData.setText(txtData.getText() + (!txtData.getText().contains(".") ? "." : ""));
        calculator.isFirstCalculation = true;
    }

    public void btnClearClick() {
        txtData.setText("0");
        calculator.isFirstCalculation = true;
    }

    public void btnSignClick(ActionEvent actionEvent) {

        txtData.setText(delZeroAtEnd(String.valueOf(-1 * Double.parseDouble(txtData.getText()))));
        calculator.isFirstCalculation = true;
    }

    public void btnPairOperationClick(ActionEvent actionEvent) {
        try {
            calculator.number1 = Double.parseDouble(txtData.getText());
            switch (((Button) actionEvent.getSource()).getText()) {
                case "+":
                    calculator.operation = 1;
                    break;
                case "-":
                    calculator.operation = 2;
                    break;
                case "*":
                    calculator.operation = 3;
                    break;
                case "/":
                    calculator.operation = 4;
                    break;
                case "x^y":
                    calculator.operation = 5;
                    break;
                case "√":
                    calculator.operation = 6;
                    calculator.calculate();
                    txtData.setText(delZeroAtEnd(calculator.result));
                    //txtData.setAlignment(Pos.CENTER_LEFT);
                    break;
                default:
                    calculator.operation = 0;
            }
            isSecondNumber = true;
            calculator.isFirstCalculation = true;
            //txtData.setText("0");
        } catch (Exception ex) {
            txtData.setText("Number conversion error");
        }

    }

    public void btnResultClick() {

        try {
            calculator.number2 = Double.parseDouble(txtData.getText());
        } catch (Exception ex) {
            txtData.setText("Number conversion error");
        }

        calculator.calculate();
        if (calculator.isFirstCalculation) {
            calculator.number1 = calculator.number2;
            calculator.isFirstCalculation = false;
        }
        txtData.setText(delZeroAtEnd(calculator.result));
    }

    public void btnMemoryClearClick(ActionEvent actionEvent) {
        calculator.setMemory(0);
        isSecondNumber = true;
        labelMemory.setVisible(false);
    }

    public void btnMemoryReadClick(ActionEvent actionEvent) {
        txtData.setText(delZeroAtEnd(String.valueOf(calculator.getMemory())));
        isSecondNumber = true;
    }

    public void btnMemeoryAddClick(ActionEvent actionEvent) {
        double memory = 0;
        try {
            memory = Double.parseDouble(txtData.getText());
        } catch (Exception ex) {
            txtData.setText("Number conversion error");
        }
        calculator.setMemory(calculator.getMemory() + memory);
        isSecondNumber = true;
        labelMemory.setVisible(calculator.getMemory() != 0 ? true : false);
    }

    public void btnMemorySubtractClick(ActionEvent actionEvent) {
        double memory = 0;
        try {
            memory = Double.parseDouble(txtData.getText());
        } catch (Exception ex) {
            txtData.setText("Number conversion error");
        }
        calculator.setMemory(calculator.getMemory() - memory);
        isSecondNumber = true;
        labelMemory.setVisible(calculator.getMemory() != 0 ? true : false);
    }

    public void buttonPressed(KeyEvent keyEvent) {
        String kbText;
        System.out.println(keyEvent.getCode().toString());
        if (keyEvent.getText().contains("0") || keyEvent.getText().contains("1") || keyEvent.getText().contains("2") || keyEvent.getText().contains("3") || keyEvent.getText().contains("4") || keyEvent.getText().contains("5") || keyEvent.getText().contains("6") || keyEvent.getText().contains("7") || keyEvent.getText().contains("8") || keyEvent.getText().contains("9")) {
            kbText = keyEvent.getText();
            txtData.setText(txtData.getText().equals("0") || (isSecondNumber == true) ? kbText : txtData.getText() + kbText);
            isSecondNumber = false;
            calculator.isFirstCalculation = true;
        } else if (keyEvent.getText().contains("+")) {
            calculator.number1 = Double.parseDouble(txtData.getText());
            calculator.operation = 1;
            isSecondNumber = true;
            calculator.isFirstCalculation = true;
        } else if (keyEvent.getText().contains("-")) {
            calculator.number1 = Double.parseDouble(txtData.getText());
            calculator.operation = 2;
            isSecondNumber = true;
            calculator.isFirstCalculation = true;
        } else if (keyEvent.getText().contains("*")) {
            calculator.number1 = Double.parseDouble(txtData.getText());
            calculator.operation = 3;
            isSecondNumber = true;
            calculator.isFirstCalculation = true;
        } else if (keyEvent.getText().contains("/")) {
            calculator.number1 = Double.parseDouble(txtData.getText());
            calculator.operation = 4;
            isSecondNumber = true;
            calculator.isFirstCalculation = true;

        } else if ((keyEvent.getCode().toString().equals("ENTER"))) {
            btnResultClick();
        }else if ((keyEvent.getCode().toString().equals("ESCAPE"))) {
            btnClearClick();
        }
    }
}
