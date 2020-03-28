package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class FavoriteMovies extends AppCompatActivity {

    private ListView movieListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);


        Bundle bundle = getIntent().getExtras();



        ArrayList<String> moviesList = bundle.getStringArrayList("movies");

        Log.d("favmovies", "movie list: " + moviesList);


        MovieAdapter adapter = new MovieAdapter(this, moviesList ); //link the adapter to our movieList data

        movieListView = findViewById(R.id.movieLV); // find our ListView from the xml

        movieListView.setAdapter(adapter); //tie it all together!










    }
}
