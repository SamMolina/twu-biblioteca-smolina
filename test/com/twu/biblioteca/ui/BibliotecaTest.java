package com.twu.biblioteca.ui;

import com.twu.biblioteca.app.model.Movie;
import com.twu.biblioteca.app.service.BookService;
import com.twu.biblioteca.app.service.MovieService;
import com.twu.biblioteca.app.ui.Biblioteca;
import com.twu.biblioteca.app.util.AssetConstants;
import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.util.StringsGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class BibliotecaTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Book bookAvailable, bookNoAvailable;
    private String fileBook, fileMovie;
    private Biblioteca biblioteca = Mockito.mock(Biblioteca.class);

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        fileBook = AssetConstants.BOOK_FILE.toString();
        fileMovie = AssetConstants.MOVIE_FILE.toString();
        bookAvailable = new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false);
        bookNoAvailable = new Book("Great Expectations","Charles Dickens", "1861", true);
    }

    @Test
    public void shouldStartMenu() throws IOException, SAXException, ParserConfigurationException {
        biblioteca = Mockito.mock(Biblioteca.class);

        biblioteca.start();

        Mockito.when(biblioteca.start()).thenReturn(0);
    }

    @Test
    public void shouldShowTheWelcomeMessage() {
        String expected = AssetConstants.WELCOME_MESSAGE.toString();
        new Biblioteca().sayHello(expected);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowTheBookList() throws IOException, SAXException, ParserConfigurationException {
        List<Object> assets = new BookService().getAssets(fileBook);

        String expected = AssetConstants.SHOW_BOOKS.toString();
        expected += new Book().formatBookInformation(AssetConstants.TITLE.name(), AssetConstants.AUTHOR.name(), AssetConstants.YEAR.name());
        for (Object asset: assets) {
            Book book = (Book) asset;
            expected += new Book().formatBookInformation(book.getTitle(), book.getAuthor(), book.getYear());
        }

        Mockito.when(biblioteca.menu(AssetConstants.OPTION_ONE.toString())).thenReturn(expected);

        new Biblioteca().printAsset(AssetConstants.BOOK.toString());
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowTheMovieList() throws IOException, SAXException, ParserConfigurationException {
        List<Object> assets = new MovieService().getAssets(fileMovie);

        String expected = AssetConstants.SHOW_MOVIES.toString();
        expected += new Movie().formatMovieInformation(AssetConstants.NAME.name(), AssetConstants.DIRECTOR.name(), AssetConstants.YEAR.name(), AssetConstants.RATING.name());
        for (Object asset: assets) {
            Movie movie = (Movie) asset;
            expected += new Movie().formatMovieInformation(movie.getName(), movie.getDirector(), movie.getYear(), String.valueOf(movie.getRating()));
        }

        Mockito.when(biblioteca.menu(AssetConstants.OPTION_ONE.toString())).thenReturn(expected);

        new Biblioteca().printAsset(AssetConstants.MOVIE.toString());
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowTheMenuApp() throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        String expected = AssetConstants.CHOOSE_OPTIONS.toString();
        expected += AssetConstants.OPTION_ONE_FULL;
        expected += AssetConstants.OPTION_TWO_FULL;
        expected += AssetConstants.OPTION_THREE_FULL;
        expected += AssetConstants.OPTION_FOUR_FULL;

        biblioteca.menu(AssetConstants.OPTION_ZERO.toString());

        Mockito.when(biblioteca.menu(AssetConstants.OPTION_ZERO.toString())).thenReturn(expected);
    }

    @Test
    public void shouldReturnSelectAValidOptionWhenUserEnterAnInvalidOption() throws ParserConfigurationException, SAXException, IOException {
        Random random = new Random();
        String invalidOption = StringsGenerator.generateRandomChars(random.nextInt(100));

        String expect = AssetConstants.SELECT_A_VALID_OPTION.toString();

        Mockito.when(biblioteca.menu(String.valueOf(invalidOption))).thenReturn(expect);
    }

    @Test
    public void shouldReturnQuitWhenIExitOfTheApp() throws ParserConfigurationException, SAXException, IOException {
        String expect = AssetConstants.OPTION_TWO.name();

        Mockito.when(biblioteca.menu(AssetConstants.OPTION_TWO.toString())).thenReturn(expect);
    }

    @Test
    public void shouldReturnThankYouEnjoyTheBookWhenUserDoASuccessfulCheckout() throws ParserConfigurationException, SAXException, IOException {
        List<Object> assets = new BookService().getAssets(AssetConstants.BOOK_FILE.toString());

        String expect = AssetConstants.ENJOY_THE_BOOK.toString();
        new BookService().checkoutAsset(assets, bookAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnThankYouEnjoyTheMovieWhenUserDoASuccessfulCheckout() throws ParserConfigurationException, SAXException, IOException {
        List<Object> assets = new BookService().getAssets(AssetConstants.MOVIE_FILE.toString());

        String expect = AssetConstants.ENJOY_THE_MOVIE.toString();
        new MovieService().checkoutAsset(assets, bookAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnBookIsNotAvailableWhenUserDoAUnsuccessfulCheckout() throws IOException, SAXException, ParserConfigurationException {
        List<Object> assets = new BookService().getAssets(AssetConstants.BOOK_FILE.toString());

        String expect = AssetConstants.BOOK_NO_AVAILABLE.toString();
        new BookService().checkoutAsset(assets, bookNoAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnBookDoesNotExitWhenUserTryToCheckoutANoExistentBook() throws IOException, SAXException, ParserConfigurationException {
        String expect = AssetConstants.BOOK_NOT_EXISTS.toString();
        new Book().isAValidBook(null);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnThankYouForRetuningTheBookWhenTheTheReturningIsSuccessful() throws Exception {
        List<Object> assets = new BookService().getAssets(AssetConstants.BOOK_FILE.toString());

        String expected = AssetConstants.THANK_YOU_FOR_RETURNING.toString();
        new BookService().returnAsset(assets, bookNoAvailable);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnInvalidRetuningWhenTheTheReturningIsUnsuccessful() throws Exception {
        String expected = AssetConstants.INVALID_RETURN.toString();
        new BookService().returnAsset(null, bookAvailable);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnBookDoesNotExitWhenUserTryToReturnAnNoExistentBook() throws IOException, SAXException, ParserConfigurationException {
        String expect = AssetConstants.BOOK_NOT_EXISTS.toString();
        new Book().isAValidBook(null);

        assertEquals(expect, outContent.toString());
    }
}
