package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    RequestQueue requestQueue;  //STEP 1
    private final String API_KEY = "388d5f18";

    private String moviePosterURL;
    private String movieTitle;
    private String movieGenre;
    private String movieDirector;
    private String movieRating;

    private String moviePlot;
    private TextView movieTitleTV;
    private TextView movieGenresTV;
    private TextView movieDirectorTV;
    private TextView movieRatingTV;
    private TextView moviePlotTV;
    private EditText movieTitleET;

    private Button getRandomBtn;
    private Button addFavesBtn;
    private Button myFavesBtn;

    private int randomMovieInt;


    private ImageView moviePosterIV;
    private Movie aMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieTitleTV = findViewById(R.id.movieTitleTV);
        movieGenresTV = findViewById(R.id.genresTV);
        movieRatingTV = findViewById(R.id.rRatingTV);
        movieDirectorTV = findViewById(R.id.directorTV);
        movieTitleET = findViewById(R.id.movieET);
        getRandomBtn =findViewById(R.id.getRandomBtn);
        moviePosterIV = findViewById(R.id.moviePosterIV);
        moviePlotTV = findViewById(R.id.storyTV);
        addFavesBtn = findViewById(R.id.addFavesBtn);
        myFavesBtn = findViewById(R.id.favoritesBtn);


        getRandomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandomMovie();
            }
        });

        addFavesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        myFavesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        requestQueue = Volley.newRequestQueue(this);

        //load a first movie
        String url = "https://www.omdbapi.com/?apikey="+ API_KEY+"&t=Toy+Story+4&y=2019";

        fetchData(url);
    }


    public void fetchData(String url){

        Log.d("fetchData", "url " + url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response " , "- "+ response);

                        try{

                            movieTitle = response.getString("Title");
                            movieGenre = response.getString("Genre");
                            movieDirector = response.getString("Director");
                            moviePlot = response.getString("Plot");
                            moviePosterURL = response.getString("Poster");

                            if(moviePosterURL.length() < 5 ){

                                moviePosterIV.setImageResource(R.drawable.noposter); //add a "no poster available" drawable if the url is less than 5 chars...
                                Log.d("fetchData" , "no poster");

                            } else {


                                Picasso.get().load(moviePosterURL).resize(560, 900).into(moviePosterIV);

                            }

                            if(response.getJSONArray("Ratings").length() !=0 ){

                                movieRating = response.getJSONArray("Ratings").getJSONObject(1).getString("Value");

                            } else {

                                movieRating = "No rating found"; // if no ratings, add this disclaimer
                            }

                            movieDirectorTV.setText("Director: " + movieDirector);
                            movieGenresTV.setText("Genre: " + movieGenre);
                            movieTitleTV.setText(movieTitle);

                            if(movieRating!=null){

                                movieRatingTV.setText("RT\uD83C\uDF45  % : " +movieRating);
                            }

                            moviePlotTV.setText(moviePlot);

                        }catch(JSONException e){

                            e.printStackTrace();

                            //get a random movie if something fails...

                            Toast.makeText(getApplicationContext()," Here is a random movie: " , Toast.LENGTH_SHORT).show();
                            getRandomMovie();
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

        requestQueue.add(jsonObjectRequest); //make the request

    }

    public void findMovie(View view){

        //take the text from the MovieTV and use it to fetchData
        movieTitle = movieTitleET.getText().toString();
        movieTitle = movieTitle.replace(" ", "+"); //replace the space with a +
        moviePosterURL = "https://www.omdbapi.com/?apikey="+ API_KEY+"&t="+ movieTitle;
        Log.d("findMovie", "url: " + moviePosterURL  );


       fetchData(moviePosterURL);

       //Gets rid of the keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(movieTitleET.getWindowToken(), 0);


    }


    public void getRandomMovie(){

            Random rand = new Random();

            //tt6860000  this is the format for IMBD codes

            // Generate random integers in range 0 to 6860000
            randomMovieInt = rand.nextInt(6860000);

            String formattedInt = "tt" + String.format("%07d", randomMovieInt) ; //format the string to have 7 spaces
            moviePosterURL = "https://www.omdbapi.com/?apikey="+ API_KEY+"&i="+ formattedInt; //make a new random url
            fetchData(moviePosterURL); //send it off and see if it works. If error, fetchData() will call this again

    }

}
