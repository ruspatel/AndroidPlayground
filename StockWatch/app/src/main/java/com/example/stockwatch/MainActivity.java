package com.example.stockwatch;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText searchTextView;
    TextView stockDisplay;

    //Need to create a instance of String class to use in autocompletetextview
    private static String[] stockNames = new String[]{
            "America,", "Canada", "Brazil", "India"
    };

    private static String apiUrl = "https://cloud.iexapis.com/stable/stock/aapl/quote?token=pk_91c14a83883f423cb192797c34b930f9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stockNames);

        autoCompleteTextView.setAdapter(adapter);

        searchTextView = findViewById(R.id.searchTextView);
        stockDisplay = findViewById(R.id.stockDisplay);


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
                Log.i("result", result);

                return result;


            }catch (Exception e){
                e.printStackTrace();
                return null;

            }

        }

        //runs code after background is done, can interact with UI
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);

                Log.i("JSON string:", s);

                double currentPriceDouble = jsonObject.getDouble("latestPrice");
                double lastPriceDouble = jsonObject.getDouble("previousClose");

                //Set value to string in order to display it within text view
                String currentPrice = Double.toString(currentPriceDouble);

                Log.i("Current Stock Price", currentPrice);

                if(lastPriceDouble > currentPriceDouble){
                    stockDisplay.setText(currentPrice);
                    stockDisplay.setTextColor(Color.parseColor("#FF5733"));
                    Log.i("last > current", Double.toString(lastPriceDouble));
                }else{
                    stockDisplay.setText(currentPrice);
                    stockDisplay.setTextColor(Color.parseColor("#00ff00"));

                }


            } catch (Exception e) {

                Toast.makeText(getApplicationContext(),"Could not get Current Stock Price :(",Toast.LENGTH_SHORT).show();

                e.printStackTrace();
            }

        }
    }

}
