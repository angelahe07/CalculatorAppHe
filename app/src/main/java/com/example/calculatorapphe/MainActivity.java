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
    double numInput;
    // operation gathers the data on which operation is being done
    String operation;
    // symbol gathers the data on which symbol (parentheses) is being used,
    String symbol;
    public void buttonSelected(View v){
        if(v.getId() == R.id.button0){
            numInput = 0;
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
        }
    }
}