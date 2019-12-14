package com.example.weather_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    TextView temperatureView;
    EditText placeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureView = findViewById(R.id.temperatureView);
        placeText = findViewById(R.id.placeText);


    }

    public void getWeather (View view) {

        try {

            DownloadTask task = new DownloadTask();
            task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + placeText.getText().toString() + "&units=metric&APPID=bf58619abfa156050b2f8b2cf2f40f6e");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    //Async task - runs on background thread
    public class DownloadTask extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();


                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;


            } catch (Exception e){
                e.printStackTrace();
//                System.out.println("returning null from exception");
                return null;
            }

        }

//        public DownloadTask() {
//            super();
//        }

        //runs code after background is done, can interact with UI
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);

                String weatherInfo = jsonObject.getString("weather");

                Log.i("Weather content", weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);

                String message = "";
                String temp = jsonObject.getJSONObject("main").getString("temp");

                for (int i=0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);

                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("description");

                    if (!main.equals("") && !description.equals("")) {
                        message += main + ": " + description + "\r\n";
                    }
                }u

                if (!message.equals("")) {
                    temperatureView.setText(temp);
                } else {
                    Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {

                Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();

                e.printStackTrace();
            }

        }
    }

}
