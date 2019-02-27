package com.example.jsu.project1_bl;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.*;

public class Calculator extends AppCompatActivity {

    public static final int BUFFER_SIZE = 16;

    String output;
    Operator currentOperator;
    double firstOperand;
    double secondOperand;
    double result;

    boolean currentlyPerformingOperations;
    boolean previousButtonWasOperator;
    boolean decimalButtonPressed;

    /*
    result is not a part of clearCalculator because it is used when the user wants to edit
    the last result with a percent or square root button press
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clearCalculator();
        result = 0;
    }

    public enum Operator {
        NONE,
        ADD,
        SUBTRACT,
        DIVIDE,
        MULTIPLY
    }

    private void updateScreen() {
        TextView t = (TextView) findViewById(R.id.outputLabel);
        t.setText(output);
    }

    private void beginNewOperand() { //secondOperand is the first to assume a new number
        firstOperand = secondOperand;
        secondOperand = 0;
        decimalButtonPressed = false;
    }
    /*
    previousButtonWasOperator checks to see if the user already selected an operator and lets
    the program simply change operator instead of shifting the operands around
    */
    public void buttonClicked(View v) {
        String id = (v.getResources().getResourceName(v.getId())).split("/")[1];

        switch(id) {
            case "addButton":
                if(previousButtonWasOperator) {
                    currentOperator = Operator.ADD;
                }
                else {
                    if (firstOperand == 0) {
                        currentOperator = Operator.ADD;
                        beginNewOperand();
                    }
                    else {
                        if (!currentlyPerformingOperations) performOperation();

                        currentOperator = Operator.ADD;
                        secondOperand = 0;
                        decimalButtonPressed = false;
                    }
                    currentlyPerformingOperations = false;
                }
                previousButtonWasOperator = true;
                output = "0";
                break;

            case "subtractButton":
                if(previousButtonWasOperator) {
                    currentOperator = Operator.SUBTRACT;
                }
                else {
                    if (firstOperand == 0) {
                        currentOperator = Operator.SUBTRACT;
                        beginNewOperand();
                    }
                    else {
                        if (!currentlyPerformingOperations) performOperation();

                        currentOperator = Operator.SUBTRACT;
                        secondOperand = 0;
                        decimalButtonPressed = false;
                    }
                    currentlyPerformingOperations = false;
                }
                previousButtonWasOperator = true;
                output = "0";
                break;

            case "multiplyButton":
                if(previousButtonWasOperator) {
                    currentOperator = Operator.MULTIPLY;
                }
                else {
                    if (firstOperand == 0) {
                        currentOperator = Operator.MULTIPLY;
                        beginNewOperand();
                    }
                    else {
                        if (!currentlyPerformingOperations) performOperation();

                        currentOperator = Operator.MULTIPLY;
                        secondOperand = 0;
                        decimalButtonPressed = false;
                    }
                    currentlyPerformingOperations = false;
                }
                previousButtonWasOperator = true;
                output = "0";
                break;

            case "divideButton":
                if(previousButtonWasOperator) {
                    currentOperator = Operator.DIVIDE;
                }
                else {
                    if (firstOperand == 0) {
                        currentOperator = Operator.DIVIDE;
                        beginNewOperand();
                    }
                    else {
                        if (!currentlyPerformingOperations) performOperation();

                        currentOperator = Operator.DIVIDE;
                        secondOperand = 0;
                        decimalButtonPressed = false;
                    }
                    currentlyPerformingOperations = false;
                }
                previousButtonWasOperator = true;
                output = "0";
                break;

            case "percentButton":
                if(firstOperand == 0 && result != 0) {
                    secondOperand = ((result * secondOperand) / 100);
                    result = 0;
                }
                else {
                    secondOperand = ((firstOperand * secondOperand) / 100);
                }

                output = String.valueOf(secondOperand);
                updateScreen();
                currentlyPerformingOperations = true;
                break;

            case "signButton" :
                if(currentlyPerformingOperations) {
                    clearCalculator();
                    secondOperand = result * -1;
                    result = 0;
                    currentlyPerformingOperations = false;
                }
                else secondOperand = secondOperand * -1;

                output = String.valueOf(secondOperand);
                updateScreen();
                break;

            case "sqrtButton":
                if(currentlyPerformingOperations) {
                    clearCalculator();
                    secondOperand = Math.sqrt(result);
                }
                else secondOperand = Math.sqrt(secondOperand);

                result = secondOperand;
                output = String.valueOf(secondOperand);
                updateScreen();
                output = "0";
                currentlyPerformingOperations = true;
                break;

            case "equalsButton":
                previousButtonWasOperator = false;
                currentlyPerformingOperations = true;
                performOperation();
                break;

            case "clearButton":
                clearCalculator();
                result = 0;
                break;

            default:
                previousButtonWasOperator = false;
                inputNumber(id);
        }

    }
    /*
    currentlyPerformingOperations checks for the equivalent of an equals button press to decide
    if it should clear the calculator before inputting numbers
    */
    private void inputNumber(String buttonID) {
        if(currentlyPerformingOperations) clearCalculator();

        if(output.length() < BUFFER_SIZE) {
            switch (buttonID) {
                case "input0":
                    if (secondOperand == 0 && !decimalButtonPressed) {
                        output = "0";
                    }
                    else output = output + "0";

                    secondOperand = Double.valueOf(output);
                    break;
                case "input1":
                    if (secondOperand == 0 && !decimalButtonPressed) {
                        output = "1";
                    }
                    else output = output + "1";

                    secondOperand = Double.valueOf(output);
                    break;
                case "input2":
                    if (secondOperand == 0 && !decimalButtonPressed) {
                        output = "2";
                    }
                    else output = output + "2";

                    secondOperand = Double.valueOf(output);
                    break;
                case "input3":
                    if (secondOperand == 0 && !decimalButtonPressed) {
                        output = "3";
                    }
                    else output = output + "3";

                    secondOperand = Double.valueOf(output);
                    break;
                case "input4":
                    if (secondOperand == 0 && !decimalButtonPressed) {
                        output = "4";
                    }
                    else output = output + "4";

                    secondOperand = Double.valueOf(output);
                    break;
                case "input5":
                    if (secondOperand == 0 && !decimalButtonPressed) {
                        output = "5";
                    }
                    else output = output + "5";

                    secondOperand = Double.valueOf(output);
                    break;
                case "input6":
                    if (secondOperand == 0 && !decimalButtonPressed) {
                        output = "6";
                    }
                    else output = output + "6";

                    secondOperand = Double.valueOf(output);
                    break;
                case "input7":
                    if (secondOperand == 0 && !decimalButtonPressed) {
                        output = "7";
                    }
                    else output = output + "7";

                    secondOperand = Double.valueOf(output);
                    break;
                case "input8":
                    if (secondOperand == 0 && !decimalButtonPressed) {
                        output = "8";
                    }
                    else output = output + "8";

                    secondOperand = Double.valueOf(output);
                    break;
                case "input9":
                    if (secondOperand == 0 && !decimalButtonPressed) {
                        output = "9";
                    }
                    else output = output + "9";

                    secondOperand = Double.valueOf(output);
                    break;
                case "decimalButton":
                    if(currentlyPerformingOperations) {
                        clearCalculator();
                        result = 0;
                    }
                    if(!decimalButtonPressed) {
                        output = output + ".";
                    }
                    decimalButtonPressed = true;
                    break;
            }

            updateScreen();
        }
    }

    private void performOperation() {
        //performs the corresponding operation and stores the result in firstOperand and result
        switch(currentOperator) {
            case ADD:
                firstOperand = firstOperand + secondOperand;
                result = firstOperand;
                output = String.valueOf(result);
                break;
            case SUBTRACT:
                firstOperand = firstOperand - secondOperand;
                result = firstOperand;
                output = String.valueOf(result);
                break;
            case MULTIPLY:
                firstOperand = firstOperand * secondOperand;
                result = firstOperand;
                output = String.valueOf(result);
                break;
            case DIVIDE:
                if(secondOperand == 0) {
                    clearCalculator();
                    result = 0;
                }
                else {
                    firstOperand = firstOperand / secondOperand;
                    result = firstOperand;
                    output = String.valueOf(result);
                }
                break;
            default: break;
        }
        updateScreen();
    }

    private void clearCalculator() {
        output = "0";
        firstOperand = 0;
        secondOperand = 0;

        currentlyPerformingOperations = false;
        previousButtonWasOperator = false;
        decimalButtonPressed = false;
        currentOperator = Operator.NONE;
        updateScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
