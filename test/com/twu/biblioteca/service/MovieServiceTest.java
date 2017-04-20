package com.twu.biblioteca.service;

import com.twu.biblioteca.app.model.Movie;
import com.twu.biblioteca.app.impl.AssetService;
import com.twu.biblioteca.app.impl.XMLFileParser;
import com.twu.biblioteca.app.util.BibliotecaConstants;
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
    private AssetService manager = new AssetService();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        fileName = BibliotecaConstants.MOVIE_FILE.toString();

        movieAvailableOne = new Movie("Titanic", "James Cameron", "1997", 7, false);
        movieAvailableTwo = new Movie("The Lord of the Rings", "Peter Jackson", "1997", 3, false);
        movieNoAvailable = new Movie("Titanic", "James Cameron", "1997", 7, true);
    }

    @Test
    public void shouldReturnAValidListOfMovies() throws ParserConfigurationException, SAXException, IOException {
        List<Object> movies = new XMLFileParser().parserFile(fileName, BibliotecaConstants.MOVIE.toString());

        assertNotEquals(0, movies.size());
    }

    @Test
    public void shouldRemoveTheCheckoutMoviesFromAvailableMovies() throws Exception {
        List<Object> assets = new AssetService().getAssets(fileName, BibliotecaConstants.MOVIE.toString());
        List<Object> moviesAvailableExpected = new AssetService().getAssets(fileName, BibliotecaConstants.MOVIE.toString());

        moviesAvailableExpected.remove(movieAvailableOne);

        Movie movie = (Movie) assets.get(0);
        movie.setCheckout(true);
        assets.set(0, movie);

        List<Object> moviesAvailableActual = manager.getAvailableAssets(assets);

        assertEquals(moviesAvailableExpected, moviesAvailableActual);
    }

    @Test
    public void shouldRefreshMoviesWhenCheckoutAMovie() throws Exception {
        List<Object> moviesExpected = new AssetService().getAssets(BibliotecaConstants.MOVIE_FILE.toString(), BibliotecaConstants.MOVIE.toString());

        List<Movie> moviesActual = manager.checkoutAsset(moviesExpected, movieAvailableOne);
        moviesExpected.remove(movieAvailableOne);

        assertEquals(moviesExpected, moviesActual);
    }

    @Test
    public void shouldRefreshMoviesWhenReturnAMovie() throws Exception {
        List<Object> moviesExpected = new AssetService().getAssets(BibliotecaConstants.MOVIE_FILE.toString(), BibliotecaConstants.MOVIE.toString());
        List<Object> moviesActual;

        moviesActual = manager.checkoutAsset(moviesExpected, movieAvailableOne);
        moviesActual = manager.checkoutAsset(moviesActual, movieAvailableTwo);

        moviesActual = manager.returnAsset(moviesActual, movieAvailableOne);
        moviesExpected.remove(movieAvailableTwo);

        assertEquals(moviesExpected, moviesActual);
    }
}
