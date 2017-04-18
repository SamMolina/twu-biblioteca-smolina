package com.twu.biblioteca.app.model;

public class Movie {
    private String name;
    private String year;
    private String director;
    private int rating;
    private boolean checkout;

    public Movie(String name, String year, String director, int rating, boolean checkout) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }

    public boolean getCheckout() {
        return checkout;
    }

    @Override
    public boolean equals(Object object) {
        Movie movie = (Movie) object;
        return name.equals(movie.getName()) && year.equals(movie.getYear()) && director.equals(movie.getDirector()) && movie.rating == rating;
    }


}
