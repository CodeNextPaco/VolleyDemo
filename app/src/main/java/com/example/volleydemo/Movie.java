package com.example.volleydemo;

public class Movie {

    private String title;
    private String director;
    private String genre;
    private String posterURL;
    private String rating;
    private String plot;

    public Movie(String title, String director, String genre, String posterURL, String rating, String plot) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.posterURL = posterURL;
        this.rating = rating;
        this.plot = plot;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
