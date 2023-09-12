package com.example.calculatorapphe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // numInput1 is the first number in the expression
    double numInput1;
    // numInput2 is the second number in the expression
    double numInput2;
    // numberInputFull is the full string with the two input numbers, the operation, and the answer
    String numberInputFull = "";
    // result is the answer to whatever operation was inputted
    double result;
    // doneEnter is a boolean that states if the enter button was clicked
    boolean doneEnter = false;
    // Makes results only show 3 decimal places
    // Link: https://stackoverflow.com/questions/16583604/formatting-numbers-using-decimalformat
    DecimalFormat df = new DecimalFormat("0.###");
    public void buttonSelected(View v){
        TextView input1TV = findViewById(R.id.textViewInputExpression);
        // if the enter button has been clicked, clear the screen and begin new calculation
        if(doneEnter){
            numberInputFull = "";
            doneEnter = false;
        }
        // checking which button has been clicked
        if(v.getId() == R.id.button0){
            // if the string is empty, then 0 cannot be the first digit
            if(numberInputFull.isEmpty()){
                numberInputFull += "";
            }else{
                // if there is an operation at the end, then 0 cannot be the first digit of the
                // second input (prevent input like 098 * 2)
                if(numberInputFull.indexOf("+") == numberInputFull.length()-2 || numberInputFull.indexOf("-") == numberInputFull.length()-2
                        || numberInputFull.indexOf("×") == numberInputFull.length()-2 || numberInputFull.indexOf("÷") == numberInputFull.length()-2){
                    numberInputFull += "";
                }else{
                    Log.i("Angela", "button 0 clicked and 0 successfully amended");
                    // safe to add zero in middle digits or end digit
                    numberInputFull += 0;
                }
            }
        // buttons 1 - 9
        }else if(v.getId() == R.id.button1){
            Log.i("Angela", "button 1 clicked and 1 successfully amended");
            numberInputFull += 1;
        }else if(v.getId() == R.id.button2){
            Log.i("Angela", "button 2 clicked and 2 successfully amended");
            numberInputFull += 2;
        }else if(v.getId() == R.id.button3){
            Log.i("Angela", "button 3 clicked and 3 successfully amended");
            numberInputFull += 3;
        }else if(v.getId() == R.id.button4){
            Log.i("Angela", "button 4 clicked and 4 successfully amended");
            numberInputFull += 4;
        }else if(v.getId() == R.id.button5){
            Log.i("Angela", "button 5 clicked and 5 successfully amended");
            numberInputFull += 5;
        }else if(v.getId() == R.id.button6){
            Log.i("Angela", "button 6 clicked and 6 successfully amended");
            numberInputFull += 6;
        }else if(v.getId() == R.id.button7){
            Log.i("Angela", "button 7 clicked and 7 successfully amended");
            numberInputFull += 7;
        }else if(v.getId() == R.id.button8){
            Log.i("Angela", "button 8 clicked and 8 successfully amended");
            numberInputFull += 8;
        }else if(v.getId() == R.id.button9){
            Log.i("Angela", "button 9 clicked and 9 successfully amended");
            numberInputFull += 9;
        }else if(v.getId() == R.id.buttonClear){
            Log.i("Angela", "button AC clicked");
            // when AC button clicked, everything is erased
            numberInputFull = "";
        }else if(v.getId() == R.id.buttonDecimal){
            // only first number can be decimal, second number cannot have decimal (limitation)
            if(numberInputFull.indexOf("+") == -1 || numberInputFull.indexOf("-") == -1
                    || numberInputFull.indexOf("×") == -1 || numberInputFull.indexOf("÷") == -1){
                // if no operation yet, check if decimal had already been placed in first number
                // to prevent 9..0 for example
                if(!isDecimalDuplicate()){
                    Log.i("Angela", "button (.) clicked and decimal successfully amended");
                    numberInputFull += ".";
                }
            }else{
                // if there is an operation, then check to make sure no decimal anywhere in input
                if(!isDecimalDuplicate()){
                    Log.i("Angela", "button (.) clicked and decimal successfully amended");
                    numberInputFull += ".";
                }else{
                    numberInputFull += "";
                }
            }
        }else if(v.getId() == R.id.buttonPi){
            if(piOrE()){
                Log.i("Angela", "button Pi clicked and Pi successfully amended");
                // no number before Pi, add Pi onto screen
                numberInputFull += Math.PI;
            }else if(numberInputFull.indexOf("+") != -1 || numberInputFull.indexOf("-") != -1
                        || numberInputFull.indexOf("×") != -1 || numberInputFull.indexOf("÷") != -1){
                // add Pi onto screen after operation
                Log.i("Angela", "button Pi clicked and Pi successfully amended");
               numberInputFull += Math.PI;
            }else{
                //multiply Pi by number if number is clicked before Pi
                numInput1 = Double.valueOf(numberInputFull.substring(0, numberInputFull.length()));
                numInput2 = Math.PI;
                result = numInput1 * numInput2;
                numberInputFull = df.format(numInput1) + " × " + df.format(numInput2) + " = " + df.format(result);
                input1TV.setText(numberInputFull);
                doneEnter = true;
            }
        }else if(v.getId() == R.id.buttonE) {
            // Same E as Pi
            if (piOrE()) {
                Log.i("Angela", "button E clicked and E successfully amended");
                numberInputFull += Math.E;
            }else if(numberInputFull.indexOf("+") != -1 || numberInputFull.indexOf("-") != -1
                    || numberInputFull.indexOf("×") != -1 || numberInputFull.indexOf("÷") != -1){
                Log.i("Angela", "button E clicked and E successfully amended");
                numberInputFull += Math.E;
            }else{
                numInput1 = Double.valueOf(numberInputFull.substring(0, numberInputFull.length()));
                numInput2 = Math.E;
                result = numInput1 * numInput2;
                numberInputFull = df.format(numInput1) + " × " + df.format(numInput2) + " = " + df.format(result);
                input1TV.setText(numberInputFull);
                doneEnter = true;
            }
        }else if(v.getId() == R.id.buttonDivide){
            Log.i("Angela", "button division clicked");
            // check to make sure no operation already (prevent breaking app when more than one
            // operation is inputted (same for all operations)
            if(!isOperationDuplicate()){
                if(numberInputFull.isEmpty()){
                    numberInputFull += "";
                }else{
                    numberInputFull += " ÷ ";
                    numInput1 = Double.valueOf(numberInputFull.substring(0, numberInputFull.length() - 2));
                }
            }else{
                numberInputFull += "";
            }
        }else if(v.getId() == R.id.buttonMultiply){
            Log.i("Angela", "button multiplication clicked");
            if(!isOperationDuplicate()){
                if(numberInputFull.isEmpty()){
                    numberInputFull += "";
                }else{
                    numberInputFull += " × ";
                    numInput1 = Double.valueOf(numberInputFull.substring(0, numberInputFull.length() - 2));
                }
            }else{
                numberInputFull += "";
            }
        }else if(v.getId() == R.id.buttonDelete){
            Log.i("Angela", "button delete clicked");
            // Check to make sure than enter had not been clicked and there is something inputted
            // if yes, then change last character in string to delete it (when the same number is
            // inputted, then all occurrences of that number will be replaced/deleted)
            if(doneEnter == false && numberInputFull.length() >= 1){
                numberInputFull = numberInputFull.replace(numberInputFull.substring(numberInputFull.length()-1), "");
            }else{
                numberInputFull += "";
            }
        }else if(v.getId() == R.id.buttonPlus){
            Log.i("Angela", "button addition clicked");
            // same as multiply and divide
            if(!isOperationDuplicate()){
                if(numberInputFull.isEmpty()){
                    numberInputFull += "";
                }else{
                    numberInputFull += " + ";
                    numInput1 = Double.valueOf(numberInputFull.substring(0, numberInputFull.length() - 2));
                }
            }else{
                numberInputFull += "";
            }
        }else if(v.getId() == R.id.buttonSubtract){
            Log.i("Angela", "button subtract clicked");
            // same as multiply and divide
            if(!isOperationDuplicate()){
                if(numberInputFull.isEmpty()){
                    numberInputFull += "";
                }else{
                    numberInputFull += " - ";
                    numInput1 = Double.valueOf(numberInputFull.substring(0, numberInputFull.length() - 2));
                }
            }else{
                numberInputFull += "";
            }
        }else if(v.getId() == R.id.buttonEnter){
            Log.i("Angela", "button enter clicked");
            // if enter button is clicked, check to make sure operation is present
            if(numberInputFull.indexOf("÷") != -1){
                // check to make sure last button clicked is not operation to prevent crash
                // (done for all operations)
                if(numberInputFull.indexOf("÷") < numberInputFull.length()-2){
                    numInput2 = Double.valueOf(numberInputFull.substring(numberInputFull.indexOf("÷") + 2));
                    result = numInput1/numInput2;
                    numberInputFull += " = " + df.format(result);
                    input1TV.setText(numberInputFull);
                }else{
                    numberInputFull = "";
                }
            }else if(numberInputFull.indexOf("×") != -1) {
                if(numberInputFull.indexOf("×") < numberInputFull.length()-2){
                    numInput2 = Double.valueOf(numberInputFull.substring(numberInputFull.indexOf("×") + 2));
                    result = numInput1 * numInput2;
                    numberInputFull += " = " + df.format(result);
                    input1TV.setText(numberInputFull);
                }else{
                    numberInputFull = "";
                }
            }else if(numberInputFull.indexOf("+") != -1){
                if(numberInputFull.indexOf("+") < numberInputFull.length()-2){
                    numInput2 = Double.valueOf(numberInputFull.substring(numberInputFull.indexOf("+") + 2));
                    result = numInput1 + numInput2;
                    numberInputFull += " = " + df.format(result);
                    input1TV.setText(numberInputFull);
                }else{
                    numberInputFull = "";
                }
            }else if(numberInputFull.indexOf("-") != -1){
                if(numberInputFull.indexOf("-") < numberInputFull.length()-2){
                    numInput2 = Double.valueOf(numberInputFull.substring(numberInputFull.indexOf("-") + 2));
                    result = numInput1 - numInput2;
                    numberInputFull += " = " + df.format(result);
                    input1TV.setText(numberInputFull);
                }else{
                    numberInputFull = "";
                }
            }
            doneEnter = true;
        }
        input1TV.setText(numberInputFull);
    }

    // This function checks to see that the user did not do another operation, therefore limiting
    // the input to two numbers and 1 operation
    public boolean isOperationDuplicate(){
        if(numberInputFull.indexOf("+") != -1 || numberInputFull.indexOf("-") != -1
                || numberInputFull.indexOf("×") != -1 || numberInputFull.indexOf("÷") != -1){
            return true;
        }else{
            return false;
        }
    }

    // This function checks to see that the user did not put in two decimals in one input number
    public boolean isDecimalDuplicate(){
        if(numberInputFull.indexOf(".") != -1){
            return true;
        }else{
            return false;
        }
    }

    // This function checks to see if the user put in Pi or E as their first value
    public boolean piOrE(){
        if(numberInputFull.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}