package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Random;


public class MainActivity extends AppCompatActivity {


    RequestQueue requestQueue;  //STEP 1
    private TextView dataTextView;
    String dataToDisplay = "";
    private final String API_KEY = "388d5f18";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataTextView = findViewById(R.id.data_txt_view);

        Log.d("onCreate" , "Application loading");

        final int min = 1;
        final int max = 20;
        final int random = new Random().nextInt((max - min) + 1) + min;

        requestQueue = Volley.newRequestQueue(this); //STEP 2

        //String url = "https://swapi.co/api/planets/"+random+"/?format=json";

        //*************************************

        String url = "https://www.omdbapi.com/?apikey="+ API_KEY+"&t=Toy+Story+4&y=2019";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{

                            String title = response.getString("Title");
                            String genre = response.getString("Genre");
                            String director = response.getString("Director");


                            /*******************************************************************************************************
                              The http://www.omdbapi.com/ database has the ratings stored in a JSON Object inside a JSON Array.
                              Rotten Tomatoes is the second object in the JSON Array - index 1.
                              Each rating has a value for "Value", so we grab  that at the end of the statement.
                             *********************************************************************************************************/
                            String rottenRating = response.getJSONArray("Ratings").getJSONObject(1).getString("Value");

                            dataToDisplay = title +
                                    "\n Directed by " + director +
                                    "\n genre: " + genre +
                                    "\n RT: " + rottenRating;

                            dataTextView.setText(dataToDisplay.toString());

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("response" , "error: " + error);
                    }
                });

         requestQueue.add(jsonObjectRequest);


    }

    //TODO Complete this fetchData() method that takes in a url and a key from the API and returns a value


    public void fetchData(String url, String key){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("response" , ""+ response);

                        try{

                            dataToDisplay = response.getString("Something");


                        }catch(JSONException e){

                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Log.e("response" , "error: " + error);
                    }
                }

        );

    }


}
