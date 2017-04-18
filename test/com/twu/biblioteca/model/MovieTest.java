package com.twu.biblioteca.model;

import com.twu.biblioteca.app.model.Movie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MovieTest {

    @Test
    public void shouldHaveANameWhenCreateAMovie() {
        String expected = "Titanic";

        Movie movie = new Movie(expected, null, null, 0, false);
        String actual = movie.getName();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAYearWhenCreateAMovie() {
        String expected = "1998";

        Movie movie = new Movie(null, expected, null, 0, false);
        String actual = movie.getYear();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnADirectorWhenCreateAMovie() {
        String expected = "Steven Spielberg";

        Movie movie = new Movie(null, null, expected, 0, false);
        String actual = movie.getDirector();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnARateWhenCreateAMovie() {
        int expected = 5;

        Movie movie = new Movie(null, null, null, expected, false);
        int actual = movie.getRating();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTheCheckoutStatusAsFalseWhenCreateAMovie() {
        boolean expected = false;

        Movie movie = new Movie(null, null, null, 0, expected);
        boolean actual = movie.getCheckout();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrueWhenCreateMoviesWithTheSameFields() {
        String name = "Titanic";
        String director = "James Cameron";
        String year = "1997";
        int rating = 3;

        Movie movieOne = new Movie(name, director, year, rating, false);
        Movie movieTwo = new Movie(name, director, year, rating, false);

        assertEquals(true, movieOne.equals(movieTwo));
    }

    @Test
    public void shouldReturnFalseWhenCompareMovieWithDifferentsFields() {
        String name = "Titanic";
        String director = "James Cameron";
        String year = "1997";
        int ratingExpected = 3;
        int ratingActual = 4;

        Movie movieExpected = new Movie(name, director, year, ratingExpected, false);
        Movie movieActual = new Movie(name, director, year, ratingActual, false);

        assertEquals(false, movieExpected.equals(movieActual));
    }

    @Test
    public void shouldReturnTrueWhenSearchingABookThatIsInBooks() {

    }
}
