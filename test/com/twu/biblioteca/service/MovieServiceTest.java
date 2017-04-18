package com.twu.biblioteca.service;

import com.twu.biblioteca.app.model.Movie;
import com.twu.biblioteca.app.service.MovieService;
import com.twu.biblioteca.app.service.XMLFileParser;
import com.twu.biblioteca.app.util.Menu;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MovieServiceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Movie movieAvailableOne, movieAvailableTwo, movieNoAvailable;
    private String fileName;
    private MovieService manager = new MovieService();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        fileName = Menu.MOVIE_FILE.toString();

        movieAvailableOne = new Movie("Titanic", "James Cameron", "1997", 7, false);
        movieAvailableTwo = new Movie("The Lord of the Rings", "Peter Jackson", "1997", 3, false);
        movieNoAvailable = new Movie("Titanic", "James Cameron", "1997", 7, true);
    }

    @Test
    public void shouldReturnAValidListOfMovies() throws ParserConfigurationException, SAXException, IOException {
        List<Object> movies = new XMLFileParser().parserFile(fileName, Menu.MOVIE.toString());

        assertNotEquals(0, movies.size());
    }

    @Test
    public void shouldModifyTheCheckoutValueFromAMovieWhenCheckingOutAMovie() throws Exception {
        boolean expect = true;
        Movie movie = (Movie) manager.checkoutAsset(movieAvailableOne);

        assertEquals(expect, movie.getCheckout());
    }

    @Test
    public void shouldModifyCheckoutValueFromAMovieWhenReturnAMovie() throws Exception {
        boolean expected = false;
        Movie movie = (Movie) new MovieService().returnAsset(movieNoAvailable);

        assertEquals(expected, movie.getCheckout());
    }

    @Test
    public void shouldRemoveTheCheckoutMoviesFromAvailableMovies() throws Exception {
        List<Object> assets = new MovieService().getAssets(fileName);
        List<Object> moviesAvailableExpected = new MovieService().getAssets(fileName);

        moviesAvailableExpected.remove(movieAvailableOne);

        Movie movie = (Movie) assets.get(0);
        movie.setCheckout(true);
        assets.set(0, movie);

        List<Movie> moviesAvailableActual = manager.getAvailableAssets(assets);

        assertEquals(moviesAvailableExpected, moviesAvailableActual);
    }

    @Test
    public void shouldRefreshMoviesWhenCheckoutAMovie() throws Exception {
        List<Object> moviesExpected = new MovieService().getAssets(Menu.MOVIE_FILE.toString());
        Movie movie = (Movie) new MovieService().checkoutAsset(movieAvailableOne);

        List<Movie> moviesActual = new MovieService().checkoutAsset(moviesExpected, movie);
        moviesExpected.remove(movieAvailableOne);

        assertEquals(moviesExpected, moviesActual);
    }

    @Test
    public void shouldRefreshMoviesWhenReturnAMovie() throws Exception {
        List<Object> moviesExpected = new MovieService().getAssets(Menu.MOVIE_FILE.toString());
        List<Movie> moviesActual;

        Movie movieOne = (Movie) new MovieService().checkoutAsset(movieAvailableOne);
        Movie movieTwo = (Movie) new MovieService().checkoutAsset(movieAvailableTwo);

        moviesActual = new MovieService().checkoutAsset(moviesExpected, movieOne);
        moviesActual = new MovieService().checkoutAsset(moviesActual, movieTwo);

        moviesActual = new MovieService().returnAsset(moviesActual, movieOne);
        moviesExpected.remove(movieAvailableTwo);

        assertEquals(moviesExpected, moviesActual);
    }
}
