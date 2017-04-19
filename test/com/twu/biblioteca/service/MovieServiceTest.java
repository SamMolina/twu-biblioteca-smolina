package com.twu.biblioteca.service;

import com.twu.biblioteca.app.model.Movie;
import com.twu.biblioteca.app.service.MovieService;
import com.twu.biblioteca.app.service.XMLFileParser;
import com.twu.biblioteca.app.util.Asset;
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
        fileName = Asset.MOVIE_FILE.toString();

        movieAvailableOne = new Movie("Titanic", "James Cameron", "1997", 7, false);
        movieAvailableTwo = new Movie("The Lord of the Rings", "Peter Jackson", "1997", 3, false);
        movieNoAvailable = new Movie("Titanic", "James Cameron", "1997", 7, true);
    }

    @Test
    public void shouldReturnAValidListOfMovies() throws ParserConfigurationException, SAXException, IOException {
        List<Object> movies = new XMLFileParser().parserFile(fileName, Asset.MOVIE.toString());

        assertNotEquals(0, movies.size());
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
        List<Object> moviesExpected = new MovieService().getAssets(Asset.MOVIE_FILE.toString());

        List<Movie> moviesActual = new MovieService().checkoutAsset(moviesExpected, movieAvailableOne);
        moviesExpected.remove(movieAvailableOne);

        assertEquals(moviesExpected, moviesActual);
    }

    @Test
    public void shouldRefreshMoviesWhenReturnAMovie() throws Exception {
        List<Object> moviesExpected = new MovieService().getAssets(Asset.MOVIE_FILE.toString());
        List<Movie> moviesActual;

        moviesActual = new MovieService().checkoutAsset(moviesExpected, movieAvailableOne);
        moviesActual = new MovieService().checkoutAsset(moviesActual, movieAvailableTwo);

        moviesActual = new MovieService().returnAsset(moviesActual, movieAvailableOne);
        moviesExpected.remove(movieAvailableTwo);

        assertEquals(moviesExpected, moviesActual);
    }
}
