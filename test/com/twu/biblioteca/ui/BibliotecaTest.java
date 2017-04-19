package com.twu.biblioteca.ui;

import com.twu.biblioteca.app.model.Movie;
import com.twu.biblioteca.app.service.BookService;
import com.twu.biblioteca.app.service.MovieService;
import com.twu.biblioteca.app.ui.Biblioteca;
import com.twu.biblioteca.app.util.Asset;
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
    private String fileBook;
    private Biblioteca biblioteca = Mockito.mock(Biblioteca.class);

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        fileBook = Asset.BOOK_FILE.toString();
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
        String expected = Asset.WELCOME_MESSAGE.toString();
        new Biblioteca().sayHello(expected);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowTheBookList() throws IOException, SAXException, ParserConfigurationException {
        List<Object> assets = new BookService().getAssets(fileBook);

        String expected = Asset.SHOW_BOOKS.toString();
        expected += new Book().formatBookInformation(Asset.TITLE.name(), Asset.AUTHOR.name(), Asset.YEAR.name());
        for (Object asset: assets) {
            Book book = (Book) asset;
            expected += new Book().formatBookInformation(book.getTitle(), book.getAuthor(), book.getYear());
        }

        Mockito.when(biblioteca.menu(Asset.OPTION_ONE.toString())).thenReturn(expected);

        new Biblioteca().printBooks();
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowTheMenuApp() throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        String expected = Asset.CHOOSE_OPTIONS.toString();
        expected += Asset.OPTION_ONE_FULL;
        expected += Asset.OPTION_TWO_FULL;
        expected += Asset.OPTION_THREE_FULL;
        expected += Asset.OPTION_FOUR_FULL;

        biblioteca.menu(Asset.OPTION_ZERO.toString());

        Mockito.when(biblioteca.menu(Asset.OPTION_ZERO.toString())).thenReturn(expected);
    }

    @Test
    public void shouldReturnSelectAValidOptionWhenUserEnterAnInvalidOption() throws ParserConfigurationException, SAXException, IOException {
        Random random = new Random();
        String invalidOption = StringsGenerator.generateRandomChars(random.nextInt(100));

        String expect = Asset.SELECT_A_VALID_OPTION.toString();

        Mockito.when(biblioteca.menu(String.valueOf(invalidOption))).thenReturn(expect);
    }

    @Test
    public void shouldReturnQuitWhenIExitOfTheApp() throws ParserConfigurationException, SAXException, IOException {
        String expect = Asset.OPTION_TWO.name();

        Mockito.when(biblioteca.menu(Asset.OPTION_TWO.toString())).thenReturn(expect);
    }

    @Test
    public void shouldReturnThankYouEnjoyTheBookWhenUserDoASuccessfulCheckout() throws ParserConfigurationException, SAXException, IOException {
        List<Object> assets = new BookService().getAssets(Asset.BOOK_FILE.toString());

        String expect = Asset.ENJOY_THE_BOOK.toString();
        new BookService().checkoutAsset(assets, bookAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnBookIsNotAvailableWhenUserDoAUnsuccessfulCheckout() throws IOException, SAXException, ParserConfigurationException {
        List<Object> assets = new BookService().getAssets(Asset.BOOK_FILE.toString());

        String expect = Asset.BOOK_NO_AVAILABLE.toString();
        new BookService().checkoutAsset(assets, bookNoAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnBookDoesNotExitWhenUserTryToCheckoutANoExistentBook() throws IOException, SAXException, ParserConfigurationException {
        String expect = Asset.BOOK_NOT_EXISTS.toString();
        new Book().isAValidBook(null);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnThankYouForRetuningTheBookWhenTheTheReturningIsSuccessful() throws Exception {
        List<Object> assets = new BookService().getAssets(Asset.BOOK_FILE.toString());

        String expected = Asset.THANK_YOU_FOR_RETURNING.toString();
        new BookService().returnAsset(assets, bookNoAvailable);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnInvalidRetuningWhenTheTheReturningIsUnsuccessful() throws Exception {
        String expected = Asset.INVALID_RETURN.toString();
        new BookService().returnAsset(null, bookAvailable);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnBookDoesNotExitWhenUserTryToReturnAnNoExistentBook() throws IOException, SAXException, ParserConfigurationException {
        String expect = Asset.BOOK_NOT_EXISTS.toString();
        new Book().isAValidBook(null);

        assertEquals(expect, outContent.toString());
    }
}
