package com.twu.biblioteca.app.model;

import com.twu.biblioteca.app.ui.Biblioteca;
import com.twu.biblioteca.app.util.BibliotecaConstants;

public class Movie extends Asset {
    private int rating;

    public Movie() {
        super(BibliotecaConstants.MOVIE.toString(), BibliotecaConstants.MOVIE_FILE.toString());
    }

    public Movie(String name, String director, String year, int rating, boolean checkout) {
        super(name, director, year, checkout, BibliotecaConstants.MOVIE.toString(), BibliotecaConstants.MOVIE_FILE.toString());
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public String showAsset() {
        return BibliotecaConstants.SHOW_MOVIES.toString();
    }

    public String unsuccessfulCheckout() {
        return BibliotecaConstants.ENJOY_THE_MOVIE.toString();
    }

    public String noAvailableAsset() {
        return BibliotecaConstants.MOVIE_NO_AVAILABLE.toString();
    }

    public String successCheckout() {
        return BibliotecaConstants.MOVIE_NOT_EXISTS.toString();
    }

    public String successReturn() {
        return BibliotecaConstants.THANK_YOU_FOR_RETURNING_THE_MOVIE.toString();
    }

    public String unsuccessfulReturn() {
        return BibliotecaConstants.INVALID_RETURN_MOVIE.toString();
    }

    @Override
    public boolean equals(Object object) {
        Movie movie = (Movie) object;
        return this.getName().equals(movie.getName()) && this.getYear().equals(movie.getYear())
                && this.getAuthor().equals(movie.getAuthor()) && rating == movie.getRating();
    }

    public String formatAssetInformation(String name, String director, String year, String rating) {
        return "|" + new Biblioteca().printWhiteSpaces(name, 30) +
                "|" + new Biblioteca().printWhiteSpaces(director, 30) +
                "|" + year + "|" + new Biblioteca().printWhiteSpaces(rating, 6) + "|\n";
    }
}
