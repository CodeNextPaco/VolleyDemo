# VolleyDemo
A quick demo of the Volley Library for Android. Uses the [Star Wars API](https://swapi.co/)

This project is set up to return a random "person" from the Star Wards universe:
```
        final int min = 1;
        final int max = 20;
        final int random = new Random().nextInt((max - min) + 1) + min;

        String url = "https://swapi.co/api/people/"+random+"/?format=json";

```
It also has a Todo: 
1. finish the fetchData() method
2. call the method to get different data from this Star Wars Api or others.
