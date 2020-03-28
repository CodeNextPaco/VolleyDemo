package com.example.volleydemo;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MovieAdapter extends ArrayAdapter<String> {

    private final String API_KEY = "388d5f18";

    private ImageView poster;
    private TextView title  ;
    private TextView director ;
    private TextView plot  ;

    RequestQueue requestQueue;



    public MovieAdapter(@NonNull Context context,  @NonNull ArrayList<String> movieList) {
        super(context, 0, movieList);
    }


    //getView can be made by Cmd + O and choose getView() to Override the method
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //inflate the custom XML so we can access its elements
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_card_view, parent, false);

        //now we can access the elements of the incoming views with findByID()
        poster = convertView.findViewById(R.id.posterIV);
        title = convertView.findViewById(R.id.titleTV);
        director = convertView.findViewById(R.id.directorTV);
        plot = convertView.findViewById(R.id.plotTV);

        //Our ArrayAdapter iterates over an arraylist of strings, so we need to declare a string here to hold each item

        String movieCode = getItem(position);

        Log.d("getView", "item: " + movieCode);

        String url = "https://www.omdbapi.com/?apikey="+ API_KEY+"&i="+movieCode;

        Log.d("adapter" , "url: " +url);

       fetchData(url);

        return convertView;


    }

    public void fetchData(String url){

        Log.d("fetchData", "url " + url);

        requestQueue = Volley.newRequestQueue(getContext()); //need to add the context class here, since it's not a regular activity, but an adapter

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("response " , "- "+ response);

                        try{

                           String movieTitle = response.getString("Title");

                           String  movieDirector = response.getString("Director");
                            String moviePlot = response.getString("Plot");
                            String moviePosterURL = response.getString("Poster");




                            if(moviePosterURL.length() < 5 ){

                                poster.setImageResource(R.drawable.noposter); //add a "no poster available" drawable if the url is less than 5 chars...
                                Log.d("fetchData" , "no poster");

                            } else {


                                Picasso.get().load(moviePosterURL).resize(560, 900).into(poster);

                            }



                            director.setText("Director: " + movieDirector);

                            title.setText(movieTitle);



                            plot.setText(moviePlot);


                        }catch(JSONException e){

                            e.printStackTrace();

                            //get a random movie if something fails...


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


}
