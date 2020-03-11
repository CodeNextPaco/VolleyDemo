# VolleyDemo
A quick demo of the Volley Library for Android. Uses the [OMBD](http://www.omdbapi.com/) API. to fetch movie data. 

This project searches for the movie "Toy Story 4". the url below shows how to concatenate a string to include an API_KEY defined as a private static String outside the onCreate() method. You will need your own API key here. 

```

        String url = "https://www.omdbapi.com/?apikey="+ API_KEY+"&t=Toy+Story+4&y=2019";

```
Todo: 
1. Finish the fetchData() method to handle different requests.
2. Add some UI to make it more dynamic, for example an EditText to submit any move title, or an ImageView to display the movie poster. 
3. Add a submit button to call the method.
 
