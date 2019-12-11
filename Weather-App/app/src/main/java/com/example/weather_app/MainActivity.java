package com.example.weather_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    //Async task - runs on background thread
    public class DownloadTask extends AsyncTask<String, Void, String> {


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

            Log.i("JSON", s);

//            try {
//                JSONObject jsonObject = new JSONObject(s);
//                String weatherInfo = jsonObject.getString("weather");
//                Log.i("Weather content", weatherInfo);
//
//                JSONArray arr = new JSONArray(weatherInfo);
//
//                for(int i=0; i < arr.length(); i++){
//                    JSONObject jsonPart = arr.getJSONObject(i);
//
//                    Log.i("main", jsonPart.getString("main"));
//                }
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        task.execute("http://api.openweathermap.org/data/2.5/weather?q=Toronto,Canada&APPID=bf58619abfa156050b2f8b2cf2f40f6e");
    }
}
