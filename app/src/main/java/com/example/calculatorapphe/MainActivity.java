package com.example.calculatorapphe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // numImput gathers the data on which number button is clicked
    int numInput;
    // operation gathers the data on which operation is being done
    String operation;
    // symbol gathers the data on which symbol (parentheses, decimal) is being used,
    String symbol;
    String numberInputFull1;
    String numberInputFull2;

    public void buttonSelected(View v){
        if(v.getId() == R.id.button0){
            numInput = 0;
            if(numberInputFull1.isEmpty() || numberInputFull2.isEmpty()){
                numberInputFull1 += "";
            }
        }else if(v.getId() == R.id.button1){
            numInput = 1;
        }else if(v.getId() == R.id.button2){
            numInput = 2;
        }else if(v.getId() == R.id.button3){
            numInput = 3;
        }else if(v.getId() == R.id.button4){
            numInput = 4;
        }else if(v.getId() == R.id.button5){
            numInput = 5;
        }else if(v.getId() == R.id.button6){
            numInput = 6;
        }else if(v.getId() == R.id.button7){
            numInput = 7;
        }else if(v.getId() == R.id.button8){
            numInput = 8;
        }else if(v.getId() == R.id.button9){
            numInput = 9;
        }else if(v.getId() == R.id.buttonBack){
            numInput = 0;
        }else if(v.getId() == R.id.buttonDecimal){
            symbol = ".";
        }else if(v.getId() == R.id.buttonDivide){
            operation = "÷";
        }else if(v.getId() == R.id.buttonLeftParentheses){
            symbol = "(";
        }else if(v.getId() == R.id.buttonRightParentheses){
            symbol = ")";
        }else if(v.getId() == R.id.buttonMultiply){
            operation = "×";
        }else if(v.getId() == R.id.buttonNegative){
            numInput *= -1;
        }else if(v.getId() == R.id.buttonPlus){
            operation = "+";
        }else if(v.getId() == R.id.buttonSubtract){
            operation = "-";
        }
    }
}