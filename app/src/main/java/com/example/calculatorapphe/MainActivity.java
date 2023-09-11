package com.example.calculatorapphe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    boolean doneEnter = false;
    public void buttonSelected(View v){
        TextView input1TV = findViewById(R.id.textViewInputExpression);
        if(doneEnter){
            numberInputFull = "";
            doneEnter = false;
        }
        if(v.getId() == R.id.button0){
            if(numberInputFull.isEmpty()){
                numberInputFull += "";
            }else{
                numberInputFull += 0;
            }
        }else if(v.getId() == R.id.button1){
            numberInputFull += 1;
        }else if(v.getId() == R.id.button2){
            numberInputFull += 2;
        }else if(v.getId() == R.id.button3){
            numberInputFull += 3;
        }else if(v.getId() == R.id.button4){
            numberInputFull += 4;
        }else if(v.getId() == R.id.button5){
            numberInputFull += 5;
        }else if(v.getId() == R.id.button6){
            numberInputFull += 6;
        }else if(v.getId() == R.id.button7){
            numberInputFull += 7;
        }else if(v.getId() == R.id.button8){
            numberInputFull += 8;
        }else if(v.getId() == R.id.button9){
            numberInputFull += 9;
        }else if(v.getId() == R.id.buttonClear){
            numberInputFull = "";
        }else if(v.getId() == R.id.buttonDecimal){
            if(!isDecimalDuplicate()){
                numberInputFull += ".";
            }
        }else if(v.getId() == R.id.buttonPi){
            if(piOrE()){
                numberInputFull += Math.PI;
            }
        }else if(v.getId() == R.id.buttonE){
            if(piOrE()){
                numberInputFull += Math.E;
            }
        }else if(v.getId() == R.id.buttonDivide){
            if(!isOperationDuplicate()){
                numberInputFull += " ÷ ";
                numInput1 = Double.valueOf(numberInputFull.substring(0, numberInputFull.length() - 2));
            }else{
                numberInputFull += "";
            }
        }else if(v.getId() == R.id.buttonMultiply){
            if(!isOperationDuplicate()){
                numberInputFull += " × ";
                numInput1 = Double.valueOf(numberInputFull.substring(0, numberInputFull.length() - 2));
            }else{
                numberInputFull += "";
            }
        }else if(v.getId() == R.id.buttonNegative){
            numberInputFull += -1;
        }else if(v.getId() == R.id.buttonPlus){
            if(!isOperationDuplicate()){
                numberInputFull += " + ";
                numInput1 = Double.valueOf(numberInputFull.substring(0, numberInputFull.length() - 2));
            }else{
                numberInputFull += "";
            }
        }else if(v.getId() == R.id.buttonSubtract){
            if(!isOperationDuplicate()){
                numberInputFull += " - ";
                numInput1 = Double.valueOf(numberInputFull.substring(0, numberInputFull.length() - 2));
            }else{
                numberInputFull += "";
            }
        }else if(v.getId() == R.id.buttonEnter){
            if(numberInputFull.indexOf("÷") != -1){
                numInput2 = Double.valueOf(numberInputFull.substring(numberInputFull.indexOf("÷") + 2));
                result = numInput1/numInput2;
                numberInputFull += " = " + result;
                input1TV.setText(numberInputFull);
            }else if(numberInputFull.indexOf("×") != -1) {
                numInput2 = Double.valueOf(numberInputFull.substring(numberInputFull.indexOf("×") + 2));
                result = numInput1 * numInput2;
                numberInputFull += " = " + result;
                input1TV.setText(numberInputFull);
            }else if(numberInputFull.indexOf("+") != -1){
                numInput2 = Double.valueOf(numberInputFull.substring(numberInputFull.indexOf("+") + 2));
                result = numInput1 + numInput2;
                numberInputFull += " = " + result;
                input1TV.setText(numberInputFull);
            }else if(numberInputFull.indexOf("-") != -1){
                numInput2 = Double.valueOf(numberInputFull.substring(numberInputFull.indexOf("-") + 2));
                result = numInput1 - numInput2;
                numberInputFull += " = " + result;
                input1TV.setText(numberInputFull);
            }
            doneEnter = true;
        }
        input1TV.setText(numberInputFull);
    }

    // How to end input to only allow two input numbers
    // How to reset numberInputFull after result is outputted, so user can do a different operation?

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

    public boolean isDecimalDuplicate(){
        if(numberInputFull.indexOf(".") != -1){
            return true;
        }else{
            return false;
        }
    }

    public boolean piOrE(){
        if(numberInputFull.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}