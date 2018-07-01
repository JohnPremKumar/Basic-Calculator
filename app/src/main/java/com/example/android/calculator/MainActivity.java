/*MIT License

        Copyright (c) 2018 JOHN PREM KUMAR S

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.*/
package com.example.android.calculator;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/*
 * Initialisation of global variables
 * inputResult variable contains current input as string
 * outputResult variable contains current output as double
 * maxValue used to store max double value
 */
public class MainActivity extends AppCompatActivity {
    String inputResult = "";
    double outputResult = 0D;
    double maxValue = 9223372036854775807D;

    /*

    *Setting screen orientation to portrait mode for better user interface

    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /*

    *addition() method is invoked when '+' is pressed

    */
    public void addition(View view) {
        /*creating required TextView's for updating and retrieving values */
        TextView inputTextView = (TextView) findViewById(R.id.input_text_view);
        TextView symbol = (TextView) findViewById(R.id.add);
        displaySymbol(symbol.getText().toString());
        /* checks whether the input is valid and output doesn't exceed */
        if (inputTextView.getText().toString().length() > 0 && outputResult < maxValue) {
            outputResult = outputResult + Double.valueOf(inputResult);
            displayOutput(outputResult);
            inputResult = "";
            displayInput(inputResult);
        }
    }

    /*

    *subtraction() method is invoked when '-' is pressed

    */
    public void subtraction(View view) {
        /*creating required TextView's for updating and retrieving values */
        TextView inputTextView = (TextView) findViewById(R.id.input_text_view);
        TextView outputTextView = (TextView) findViewById(R.id.output_text_view);
        TextView symbol = (TextView) findViewById(R.id.sub);
        displaySymbol(symbol.getText().toString());
        /* checks whether the input is valid and output doesn't exceed
         * used additional condition for initial input case to avoid exception*/
        if (outputTextView.getText().toString().equals("") && inputTextView.getText().toString().length() > 0) {
            outputResult = Double.valueOf(inputResult);
            displayOutput(outputResult);
            inputResult = "";
            displayInput(inputResult);
        } else if (inputTextView.getText().toString().length() > 0 && outputResult < maxValue) {
            displaySymbol(symbol.getText().toString());
            outputResult = outputResult - Double.valueOf(inputResult);
            displayOutput(outputResult);
            inputResult = "";
            displayInput(inputResult);
        }
    }

    /*

    *multiplication() method is invoked when '*' is pressed

    */
    public void multiplication(View view) {
        /*creating required TextView's for updating and retrieving values */
        TextView inputTextView = (TextView) findViewById(R.id.input_text_view);
        TextView outputTextView = (TextView) findViewById(R.id.output_text_view);
        TextView symbol = (TextView) findViewById(R.id.multiply);
        displaySymbol(symbol.getText().toString());
        /* checks whether the input is valid and output doesn't exceed
         * used additional condition for initial input case to avoid exception*/
        if (outputTextView.getText().toString().equals("") && inputTextView.getText().toString().length() > 0) {
            outputResult = Double.valueOf(inputResult);
            displayOutput(outputResult);
            inputResult = "";
            displayInput(inputResult);
        } else if (inputTextView.getText().toString().length() > 0 && outputResult < maxValue) {
            outputResult = outputResult * Double.valueOf(inputResult);
            displayOutput(outputResult);
            inputResult = "";
            displayInput(inputResult);
        }
    }
    /*

    *division() method is invoked when '/' is pressed

    */
    public void division(View view) {
        /*creating required TextView's for updating and retrieving values */
        TextView inputTextView = (TextView) findViewById(R.id.input_text_view);
        TextView outputTextView = (TextView) findViewById(R.id.output_text_view);
        TextView symbol = (TextView) findViewById(R.id.divide);
        displaySymbol(symbol.getText().toString());
        /* checks whether the input is valid and output doesn't exceed
         * used additional condition for initial input case to avoid exception
         * used additonal condition to display '∞' to avoid exception*/
        if (inputTextView.getText().toString().equals("0") && outputTextView.getText().toString().length() > 0) {
            displayException("∞");
            inputResult = "";
            displayInput(inputResult);
        } else if (outputTextView.getText().toString().equals("") && inputTextView.getText().toString().length() > 0) {
            outputResult = Double.valueOf(inputResult);
            displayOutput(outputResult);
            inputResult = "";
            displayInput(inputResult);
        } else if (inputTextView.getText().toString().length() > 0 && outputResult < maxValue) {
            outputResult = outputResult / Double.valueOf(inputResult);
            displayOutput(outputResult);
            inputResult = "";
            displayInput(inputResult);
        }
    }
    /*

    *zero() method is invoked when '0' is pressed

    */
    public void zero(View view) {
        if (inputResult.length() < 9) {
            inputResult = inputResult + "0";
            displayInput(inputResult);
        }
    }
    /*

    *decimal() method is invoked when '.' is pressed

    */
    public void decimal(View view) {
        if (inputResult.length() < 9) {
            inputResult = inputResult + ".";
            displayInput(inputResult);
        }
    }
    /*

    *buttonPressed() method is invoked when numbers are pressed

    */
    public void buttonPressed(View view) {
        /*creating required TextView's for updating and retrieving values */
        TextView input = (TextView) findViewById(view.getId());
        String current = input.getText().toString();
        if (inputResult.length() < 17) {
            inputResult = inputResult + (String) current;
            displayInput(inputResult);
        }
    }

    /*

    *equalTo() method is invoked when '=' is pressed

    */
    public void equalTo(View view) {
        /*creating required TextView's for updating and retrieving values */
        TextView symbolTextView = (TextView) findViewById(R.id.symbol_text_view);
        String symbolValue = symbolTextView.getText().toString();
        /*calls the respective function based on the current opertor in the symbolTextView*/
        if (symbolValue.equals("+")) {
            addition(view);
        } else if (symbolValue.equals("-")) {
            subtraction(view);
        } else if (symbolValue.equals("x")) {
            multiplication(view);
        } else if (symbolValue.equals("/")) {
            division(view);
        }
        symbolTextView.setText("");
    }
    /*

    * del() method is invoked when 'DEL' is pressed
    * used to delete a last updated value from the input field

    */
    public void del(View view) {
        if (inputResult.length() > 0) {
            inputResult = inputResult.substring(0, inputResult.length() - 1);
            displayInput(inputResult);
        }
    }
    /*

    *clearAll() method is invoked when 'CLR' is pressed

    */
    public void clearAll(View view) {
        /*creating required TextView's for updating and retrieving values */
        TextView symbolTextView = (TextView) findViewById(R.id.symbol_text_view);
        symbolTextView.setText("");
        inputResult = "";
        displayInput(inputResult);
        outputResult = 0;
        displayException("");

    }
    /*

    *displayInput() method is used to update input_text_view

    */
    private void displayInput(String number) {
        /*creating required TextView's for updating values */
        TextView inputTextView = (TextView) findViewById(R.id.input_text_view);
        inputTextView.setText("" + number);
    }
    /*

    *displaySymbol() method is used to update symbol_text_view

    */
    private void displaySymbol(String symbol) {
        /*creating required TextView's for updating values */
        TextView symbolTextView = (TextView) findViewById(R.id.symbol_text_view);
        symbolTextView.setText("" + symbol);
    }
    /*

    *displayOutput() method is used to update output_text_view

    */
    private void displayOutput(double number) {
        /*creating required TextView's for updating values */
        TextView outputTextView = (TextView) findViewById(R.id.output_text_view);
        String wholeNumberCheck = String.valueOf(number);
        if (wholeNumberCheck.charAt(wholeNumberCheck.length() - 2) == '.' && wholeNumberCheck.charAt(wholeNumberCheck.length() - 1) == '0') {
            outputTextView.setText("" + (long) number);
        } else {
            outputTextView.setText("" + number);
        }

    }
    /*

    *displayException() method is used to update output_text_view for displaying exceptional results

    */
    private void displayException(String number) {
        /*creating required TextView's for updating and retrieving values */
        TextView outputTextView = (TextView) findViewById(R.id.output_text_view);
        outputTextView.setText("" + number);
    }
}
