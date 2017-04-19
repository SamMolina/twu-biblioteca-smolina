package com.twu.biblioteca.app.model;

import com.twu.biblioteca.app.ui.Biblioteca;
import com.twu.biblioteca.app.util.AssetConstants;

public class Movie {
    private String name;
    private String year;
    private String director;
    private int rating;
    private boolean checkout;

    public Movie(String name, String director, String year, int rating, boolean checkout) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public Movie() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }

    @Override
    public boolean equals(Object object) {
        Movie movie = (Movie) object;
        return name.equals(movie.getName()) && year.equals(movie.getYear()) && director.equals(movie.getDirector()) && movie.rating == rating;
    }

    public String formatMovieInformation(String name, String director, String year, String rating) {
        return "|" + new Biblioteca().printWhiteSpaces(name, 30) +
                "|" + new Biblioteca().printWhiteSpaces(director, 30) +
                "|" + year + "|" + new Biblioteca().printWhiteSpaces(rating, 6) + "|\n";
    }

    public boolean isAValidMovie(Movie movie) {
        if (movie == null) {
            System.out.print(AssetConstants.MOVIE_NOT_EXISTS.toString());
            return false;
        }
        return true;
    }
}
