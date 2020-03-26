# VolleyDemo
A quick demo of the Volley Library for Android. Uses the [OMBD](http://www.omdbapi.com/) API. to fetch movie data. 
Branch1 has the following improvements:

1. Added the Picasso  dependency to the Gradle app file. Picasso allows for setting ImageView content from external URLs.

```
        implementation 'com.squareup.picasso:picasso:2.71828'
```


2. Added more UI to hold incoming data
3. Added UI to enter movie title and year, and a submit button.
4. Created a "Random Movie" button to get a random movie using IMDB codes.
5. Declared variables :
```
        private String moviePosterURL;
        private String movieTitle;
        private String movieYear;
```

         

 
