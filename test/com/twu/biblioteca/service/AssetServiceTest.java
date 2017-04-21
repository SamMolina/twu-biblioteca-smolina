package com.twu.biblioteca.service;

import com.twu.biblioteca.app.impl.AssetService;
import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.impl.XMLFileParser;
import com.twu.biblioteca.app.model.Movie;
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

public class AssetServiceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Book bookAvailableOne, bookAvailableTwo, bookNoAvailable;
    private String fileNameBook, fileNameMovie;
    private Movie movieAvailableOne, movieAvailableTwo, movieNoAvailable;
    private AssetService manager = new AssetService();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));

        bookAvailableOne = new Book("The Shadow of the Wind", "Carlos Ruíz Zafón", "2001", false);
        bookAvailableTwo = new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false);
        bookNoAvailable = new Book("Great Expectations","Charles Dickens", "1861", true);

        movieAvailableOne = new Movie("Titanic", "James Cameron", "1997", 7, false);
        movieAvailableTwo = new Movie("The Lord of the Rings", "Peter Jackson", "1997", 3, false);
        movieNoAvailable = new Movie("Titanic", "James Cameron", "1997", 7, true);

        fileNameBook = BibliotecaConstants.BOOK_FILE.toString();
        fileNameMovie = BibliotecaConstants.MOVIE_FILE.toString();
    }

    @Test
    public void shouldReturnAValidListOfBooks() throws ParserConfigurationException, SAXException, IOException {
        List<Object> books = new XMLFileParser().parserFile(fileNameBook, BibliotecaConstants.BOOK.toString());

        assertNotEquals(0, books.size());
    }

    @Test
    public void shouldRemoveTheCheckoutBooksFromAvailableBooks() throws Exception {
        List<Object> assets = new AssetService().getAssets(fileNameBook, BibliotecaConstants.BOOK.toString());
        List<Object> booksAvailableExpected = new AssetService().getAssets(fileNameBook, BibliotecaConstants.BOOK.toString());

        booksAvailableExpected.remove(bookAvailableOne);

        Book book = (Book) assets.get(0);
        book.setCheckout(true);
        assets.set(0, book);

        List<Object> booksAvailableActual = manager.getAvailableAssets(assets);

        assertEquals(booksAvailableExpected, booksAvailableActual);
    }

    @Test
    public void shouldRefreshBooksWhenCheckoutABook() throws Exception {
        List<Object> booksExpected = new AssetService().getAssets(fileNameBook, BibliotecaConstants.BOOK.toString());

        List<Book> booksActual = (List<Book>) manager.checkoutAsset(booksExpected, bookAvailableOne);
        booksExpected.remove(bookAvailableOne);

        assertEquals(booksExpected, booksActual);
    }

    @Test
    public void shouldRefreshBooksWhenReturnABook() throws Exception {
        List<Object> booksExpected = new AssetService().getAssets(fileNameBook, BibliotecaConstants.BOOK.toString());
        List<Object> booksActual;

        booksActual = manager.checkoutAsset(booksExpected, bookAvailableOne);
        booksActual = manager.checkoutAsset(booksActual, bookAvailableTwo);

        booksActual = manager.returnAsset(booksActual, bookAvailableOne);
        booksExpected.remove(bookAvailableTwo);

        assertEquals(booksExpected, booksActual);
    }

    @Test
    public void shouldReturnAValidListOfMovies() throws ParserConfigurationException, SAXException, IOException {
        List<Object> movies = new XMLFileParser().parserFile(fileNameMovie, BibliotecaConstants.MOVIE.toString());

        assertNotEquals(0, movies.size());
    }

    @Test
    public void shouldRemoveTheCheckoutMoviesFromAvailableMovies() throws Exception {
        List<Object> assets = new AssetService().getAssets(fileNameMovie, BibliotecaConstants.MOVIE.toString());
        List<Object> moviesAvailableExpected = new AssetService().getAssets(fileNameMovie, BibliotecaConstants.MOVIE.toString());

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
