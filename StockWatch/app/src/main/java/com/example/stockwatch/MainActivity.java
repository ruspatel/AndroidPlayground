package com.example.stockwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView searchTextView;
    TextView stockDisplay;

    private static final String[] COUNTRIES = {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola"};

    private static String apiUrl = "https://cloud.iexapis.com/stable/stock/aapl/quote?token=pk_91c14a83883f423cb192797c34b930f9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        AutoCompleteTextView editText = findViewById(R.id.searchTextView);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, COUNTRIES);
//
//        editText.setAdapter(adapter);
    }

    public void getCurrentPrice (View view){

        try {

            DownloadTask task = new DownloadTask();
            task.execute("https://cloud.iexapis.com/stable/stock/" + searchTextView.getText().toString() + "/quote?token=pk_91c14a83883f423cb192797c34b930f9");
        }catch (Exception e){
            e.printStackTrace();

        }
    }




    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection  urlConnection = null;

            try{
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


            }catch (Exception e){
                e.printStackTrace();
//                System.out.println("returning null from exception");
                return null;

            }

        }

        //runs code after background is done, can interact with UI
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);

                String currentPrice = jsonObject.getString("latestPrice");

                Log.i("Current Stock Price", currentPrice);

                stockDisplay.setText(currentPrice);


//                JSONArray arr = new JSONArray(weatherInfo);
//
//                String message = "";
//                String temp = jsonObject.getJSONObject("main").getString("temp");
//
//                for (int i=0; i < arr.length(); i++) {
//                    JSONObject jsonPart = arr.getJSONObject(i);
//
//                    String main = jsonPart.getString("main");
//                    String description = jsonPart.getString("description");
//
//                    if (!main.equals("") && !description.equals("")) {
//                        message += main + ": " + description + "\r\n";
//                    }
//                }u
//
//                if (!message.equals("")) {
//                    temperatureView.setText(temp);
//                } else {
//                    Toast.makeText(getApplicationContext(),"Could not find weather :(",Toast.LENGTH_SHORT).show();
//                }

            } catch (Exception e) {

                Toast.makeText(getApplicationContext(),"Could not get Current Stock Price :(",Toast.LENGTH_SHORT).show();

                e.printStackTrace();
            }

        }
    }

}
