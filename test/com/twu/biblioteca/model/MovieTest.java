package com.twu.biblioteca.model;

import com.twu.biblioteca.app.model.Movie;
import com.twu.biblioteca.app.util.Asset;
import com.twu.biblioteca.app.util.StringsGenerator;
import com.twu.biblioteca.app.service.MovieService;
import org.junit.Before;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class MovieTest {
    private Movie movieAvailable;
    private String fileName, movieUnavailable;

    @Before
    public void setUp() {
        movieAvailable = new Movie("Titanic", "James Cameron", "1997", 7, false);
        fileName = Asset.MOVIE_FILE.toString();
        movieUnavailable = StringsGenerator.generateRandomChars(5);
    }

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
    public void shouldReturnTrueWhenSearchingAMovieThatIsInMovies() throws IOException, org.xml.sax.SAXException, ParserConfigurationException {
        List<Movie> movies = new MovieService().getAssets(fileName);
        Movie movieToSearch = movieAvailable;

        boolean expected = true;
        boolean actual = new MovieService().isAssetInAssets(movies, movieToSearch);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnFalseWhenSearchingAMovieThatNoIsInMovies() throws IOException, org.xml.sax.SAXException, ParserConfigurationException {
        List<Movie> movies = new MovieService().getAssets(fileName);
        Movie movieToSearch = movieAvailable;
        movieToSearch.setName(movieUnavailable);

        boolean expected = false;
        boolean actual = new MovieService().isAssetInAssets(movies, movieToSearch);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAMovieUsingTheNameOfTheMovie() throws IOException, org.xml.sax.SAXException, ParserConfigurationException {
        List<Movie> movies = new MovieService().getAssets(Asset.MOVIE_FILE.toString());

        Movie movieExpected = movieAvailable;
        Movie movieActual = (Movie) new MovieService().isAssetInAssets(movies, movieExpected.getName());

        assertEquals(movieExpected, movieActual);
    }

    @Test
    public void shouldReturnNullWhenEnteringAMovieNameThatNoIsInTheList() throws IOException, org.xml.sax.SAXException, ParserConfigurationException {
        List<Movie> movies = new MovieService().getAssets(Asset.MOVIE_FILE.toString());

        Movie movieActual = (Movie) new MovieService().isAssetInAssets(movies, movieUnavailable);

        assertNull(movieActual);
    }
}
