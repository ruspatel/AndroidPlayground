package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;
import org.json.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //R means resource object
        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNumEditText = (EditText) findViewById(R.id.num1);
                EditText secondNumEditText = (EditText) findViewById(R.id.num2);
                TextView resultTextView = (TextView) findViewById(R.id.result);


                try {
                    float num1 = Integer.parseInt(firstNumEditText.getText().toString());
                    float num2 = Integer.parseInt(secondNumEditText.getText().toString());
                    float result;
                    result = num1 + num2;

                    //Converting an int to string by concatenating an empty string
                    resultTextView.setText(result + "");

                }catch (Exception e){
                    String errorMsg = "Please enter a valid number";
                    resultTextView.setText(errorMsg);
                }
            }
        });

        Button subtractButton = (Button) findViewById(R.id.subtractButton);
        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNumEditText = (EditText) findViewById(R.id.num1);
                EditText secondNumEditText = (EditText) findViewById(R.id.num2);
                TextView resultTextView = (TextView) findViewById(R.id.result);

                try {

                    float num1 = Integer.parseInt(firstNumEditText.getText().toString());
                    float num2 = Integer.parseInt(secondNumEditText.getText().toString());
                    float result = num1 - num2;

                    resultTextView.setText(result + "");
                }catch (Exception e){
                    String errorMsg = "Please enter a valid number";
                    resultTextView.setText(errorMsg);
                }
            }
        });

        Button multiplyButton = (Button) findViewById(R.id.multiplyButton);
        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNumEditText = (EditText) findViewById(R.id.num1);
                EditText secondNumEditText = (EditText) findViewById(R.id.num2);
                TextView resultTextView = (TextView) findViewById(R.id.result);

                try {

                    float num1 = Integer.parseInt(firstNumEditText.getText().toString());
                    float num2 = Integer.parseInt(secondNumEditText.getText().toString());
                    float result = num1 * num2;

                    resultTextView.setText(result + "");

                }catch (Exception e){
                    String errorMsg = "Please enter a valid number";
                    resultTextView.setText(errorMsg);
                }
            }
        });

        Button divideButton = (Button) findViewById(R.id.divideButton);
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstNumEditText = (EditText) findViewById(R.id.num1);
                EditText secondNumEditText = (EditText) findViewById(R.id.num2);
                TextView resultTextView = (TextView) findViewById(R.id.result);

                try {

                    float num1 = Integer.parseInt(firstNumEditText.getText().toString());
                    float num2 = Integer.parseInt(secondNumEditText.getText().toString());
                    float result = num1 / num2;

                    resultTextView.setText(result + "");

                } catch(Exception e){
                    String errorMsg = "Please enter a valid number";
                    resultTextView.setText(errorMsg);
                }
            }
        });

        String api_url = "https://api.mapbox.com/geocoding/v5/mapbox.places/toronto,Canada.json?access_token=pk.eyJ1IjoicnVzcGF0ZWwiLCJhIjoiY2szM2V1MWVlMHUyNDNvazNkNXhsb2JrYyJ9.UDAYGOlItoCYAyr0n3LRFA";
        JSONObject =

    }
}
