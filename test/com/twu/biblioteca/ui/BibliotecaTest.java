package com.twu.biblioteca.ui;

import com.twu.biblioteca.app.impl.AssetService;
import com.twu.biblioteca.app.impl.XMLFileParser;
import com.twu.biblioteca.app.model.Movie;
import com.twu.biblioteca.app.model.User;
import com.twu.biblioteca.app.ui.Biblioteca;
import com.twu.biblioteca.app.util.BibliotecaConstants;
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
    private Movie movieAvailable, movieNoAvailable;
    private String fileBook, fileMovie;
    private Biblioteca biblioteca = Mockito.mock(Biblioteca.class);
    private AssetService manager = new AssetService();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        fileBook = BibliotecaConstants.BOOK_FILE.toString();
        fileMovie = BibliotecaConstants.MOVIE_FILE.toString();

        bookAvailable = new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false);
        bookNoAvailable = new Book("Great Expectations","Charles Dickens", "1861", true);

        movieAvailable = new Movie("Titanic", "James Cameron", "1997", 7, false);
        movieNoAvailable = new Movie("The Lord of the Rings", "Peter Jackson", "1997", 10, true);
    }

    @Test
    public void shouldStartMenu() throws IOException, SAXException, ParserConfigurationException {
        biblioteca = Mockito.mock(Biblioteca.class);

        biblioteca.start();

        Mockito.when(biblioteca.start()).thenReturn(0);
    }

    @Test
    public void shouldShowTheWelcomeMessage() {
        String expected = BibliotecaConstants.WELCOME_MESSAGE.toString();
        new Biblioteca().sayHello(expected);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldLoginACustomer() {
        String expected = BibliotecaConstants.WELCOME_MESSAGE.toString();
        new Biblioteca().sayHello(expected);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowTheBookList() throws IOException, SAXException, ParserConfigurationException {
        List<Object> assets = new AssetService().getAssets(fileBook, BibliotecaConstants.BOOK.toString());

        String expected = BibliotecaConstants.SHOW_BOOKS.toString();
        expected += new Book().formatAssetInformation(BibliotecaConstants.TITLE.name(), BibliotecaConstants.AUTHOR.name(), BibliotecaConstants.YEAR.name());
        for (Object asset: assets) {
            Book book = (Book) asset;
            expected += new Book().formatAssetInformation(book.getName(), book.getAuthor(), book.getYear());
        }

        Mockito.when(biblioteca.menu(BibliotecaConstants.OPTION_ONE.toString(), "")).thenReturn(expected);

        new Biblioteca().printAsset(BibliotecaConstants.BOOK.toString());
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowTheMovieList() throws IOException, SAXException, ParserConfigurationException {
        List<Object> assets = manager.getAssets(fileMovie, BibliotecaConstants.MOVIE.toString());

        String expected = BibliotecaConstants.SHOW_MOVIES.toString();
        expected += new Movie().formatAssetInformation(BibliotecaConstants.NAME.name(), BibliotecaConstants.DIRECTOR.name(), BibliotecaConstants.YEAR.name(), BibliotecaConstants.RATING.name());
        for (Object asset: assets) {
            Movie movie = (Movie) asset;
            expected += new Movie().formatAssetInformation(movie.getName(), movie.getAuthor(), movie.getYear(), String.valueOf(movie.getRating()));
        }

        Mockito.when(biblioteca.menu(BibliotecaConstants.OPTION_ONE.toString(), null)).thenReturn(expected);

        new Biblioteca().printAsset(BibliotecaConstants.MOVIE.toString());
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowTheMenuApp() throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        String expected = BibliotecaConstants.CHOOSE_OPTIONS.toString();
        expected += BibliotecaConstants.OPTION_ONE_FULL;
        expected += BibliotecaConstants.OPTION_TWO_FULL;
        expected += BibliotecaConstants.OPTION_THREE_FULL;
        expected += BibliotecaConstants.OPTION_FOUR_FULL;
        expected += BibliotecaConstants.OPTION_FIVE_FULL;

        Mockito.when(biblioteca.menu(BibliotecaConstants.OPTION_ZERO.toString(), null)).thenReturn(expected);
        new Biblioteca().printMenu();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnSelectAValidOptionWhenUserEnterAnInvalidOption() throws ParserConfigurationException, SAXException, IOException {
        Random random = new Random();
        String invalidOption = StringsGenerator.generateRandomChars(random.nextInt(100));

        String expect = BibliotecaConstants.SELECT_A_VALID_OPTION.toString();

        Mockito.when(biblioteca.menu(String.valueOf(invalidOption), null)).thenReturn(expect);
    }

    @Test
    public void shouldReturnQuitWhenIExitOfTheApp() throws ParserConfigurationException, SAXException, IOException {
        String expect = BibliotecaConstants.OPTION_TWO.name();

        Mockito.when(biblioteca.menu(BibliotecaConstants.OPTION_TWO.toString(), null)).thenReturn(expect);
    }

    @Test
    public void shouldReturnThankYouEnjoyTheBookWhenUserDoASuccessfulCheckout() throws ParserConfigurationException, SAXException, IOException {
        List<Object> assets = new AssetService().getAssets(fileBook, BibliotecaConstants.BOOK.toString());

        String expect = BibliotecaConstants.ENJOY_THE_BOOK.toString();
        new AssetService().checkoutAsset(assets, bookAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnThankYouEnjoyTheMovieWhenUserDoASuccessfulCheckout() throws ParserConfigurationException, SAXException, IOException {
        List<Object> assets = new AssetService().getAssets(fileMovie, BibliotecaConstants.MOVIE.toString());

        String expect = BibliotecaConstants.ENJOY_THE_MOVIE.toString();
        manager.checkoutAsset(assets, movieAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnBookIsNotAvailableWhenUserDoAUnsuccessfulCheckout() throws IOException, SAXException, ParserConfigurationException {
        List<Object> assets = new AssetService().getAssets(fileBook, BibliotecaConstants.BOOK.toString());

        String expect = BibliotecaConstants.BOOK_NO_AVAILABLE.toString();
        new AssetService().checkoutAsset(assets, bookNoAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnMovieIsNotAvailableWhenUserDoAUnsuccessfulCheckout() throws IOException, SAXException, ParserConfigurationException {
        List<Object> assets = new AssetService().getAssets(fileMovie, BibliotecaConstants.MOVIE.toString());

        String expect = BibliotecaConstants.MOVIE_NO_AVAILABLE.toString();
        new AssetService().checkoutAsset(assets, movieNoAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnBookDoesNotExitWhenUserTryToCheckoutANoExistentBook() throws IOException, SAXException, ParserConfigurationException {
        String expect = BibliotecaConstants.BOOK_NOT_EXISTS.toString();
        new Book().isAValidAsset(null);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnMovieDoesNotExitWhenUserTryToCheckoutANoExistentBook() throws IOException, SAXException, ParserConfigurationException {
        String expect = BibliotecaConstants.MOVIE_NOT_EXISTS.toString();
        new Movie().isAValidAsset(null);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnThankYouForRetuningTheBookWhenTheTheReturningIsSuccessful() throws IOException, SAXException, ParserConfigurationException {
        List<Object> assets = new AssetService().getAssets(fileBook, BibliotecaConstants.BOOK.toString());

        String expected = BibliotecaConstants.THANK_YOU_FOR_RETURNING_THE_BOOK.toString();
        new AssetService().returnAsset(assets, bookNoAvailable);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnThankYouForRetuningTheMovieWhenTheTheReturningIsSuccessful() throws IOException, SAXException, ParserConfigurationException {
        List<Object> assets = new AssetService().getAssets(fileBook, BibliotecaConstants.MOVIE.toString());

        String expected = BibliotecaConstants.THANK_YOU_FOR_RETURNING_THE_MOVIE.toString();
        new AssetService().returnAsset(assets, movieNoAvailable);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnInvalidReturningWhenTheTheReturningBookIsUnsuccessful() {
        String expected = BibliotecaConstants.INVALID_RETURN_BOOK.toString();
        new AssetService().returnAsset(null, bookAvailable);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnInvalidReturningWhenTheTheReturningMovieIsUnsuccessful() {
        String expected = BibliotecaConstants.INVALID_RETURN_MOVIE.toString();
        new AssetService().returnAsset(null, movieAvailable);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnBookDoesNotExitWhenUserTryToReturnAnNoExistentBook() throws IOException, SAXException, ParserConfigurationException {
        String expect = BibliotecaConstants.BOOK_NOT_EXISTS.toString();
        new Book().isAValidAsset(null);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnTheUserInformation() {
        String name = StringsGenerator.generateRandomChars(5);
        String email = StringsGenerator.generateRandomChars(5);
        String phoneNumber = StringsGenerator.generateRandomChars(5);
        User user = new User(name, email, phoneNumber, null, null);

        String expected = "\nName: " + name + "\nEmail: " + email + "\nPhone Number: " + phoneNumber+ "\n";
        String actual = user.showUserInformation();

        assertEquals(expected, actual);
    }
}
