package com.twu.biblioteca.ui;

import com.twu.biblioteca.app.service.BookService;
import com.twu.biblioteca.app.util.Menu;
import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.util.StringsGenerator;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MenuTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Book bookAvailable, bookNoAvailable;
    private String file;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        file = Menu.BOOK_FILE.toString();
        bookAvailable = new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false);
        bookNoAvailable = new Book("Great Expectations","Charles Dickens", "1861", true);
    }

    @Test
    public void shouldShowTheWelcomeMessage() {
        String expected = Menu.WELCOME_MESSAGE.toString();
        new com.twu.biblioteca.app.ui.Menu().sayHello(expected);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowAWhiteSpace() {
        String expected = "\n\n";
        new com.twu.biblioteca.app.ui.Menu().printLines(2);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowTheBookList() throws IOException, SAXException, ParserConfigurationException {
        com.twu.biblioteca.app.ui.Menu menu = new com.twu.biblioteca.app.ui.Menu();
        List<Book> books = new BookService().getAssets(file);

        String expected = Menu.SHOW_BOOKS.toString();
        expected += new Book().formatBookInformation(Menu.TITLE.name(), Menu.AUTHOR.name(), Menu.YEAR.name());
        for (Book book: books) {
            expected += new Book().formatBookInformation(book.getTitle(), book.getAuthor(), book.getYear());
        }
        new com.twu.biblioteca.app.ui.Menu().menuOption(Menu.OPTION_ONE.toString());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowTheMenuApp() throws ParserConfigurationException, SAXException, IOException {
        String expected = Menu.CHOOSE_OPTIONS.toString();
        expected += Menu.OPTION_ONE_FULL;
        expected += Menu.OPTION_TWO_FULL;
        expected += Menu.OPTION_THREE_FULL;
        expected += Menu.OPTION_FOUR_FULL;

        new com.twu.biblioteca.app.ui.Menu().menuOption(Menu.OPTION_ZERO.toString());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnSelectAValidOptionWhenUserEnterAnInvalidOption() throws ParserConfigurationException, SAXException, IOException {
        Random random = new Random();
        String invalidOption = StringsGenerator.generateRandomChars(random.nextInt(100));

        String expect = Menu.SELECT_A_VALID_OPTION.toString();
        String actual = new com.twu.biblioteca.app.ui.Menu().menuOption(String.valueOf(invalidOption));

        assertEquals(expect, actual);
    }

    @Test
    public void shouldReturnQuitWhenIExitOfTheApp() throws ParserConfigurationException, SAXException, IOException {
        String expect = Menu.OPTION_TWO.name();
        String actual = new com.twu.biblioteca.app.ui.Menu().menuOption(Menu.OPTION_TWO.toString());

        assertEquals(expect, actual);
    }

    @Test
    public void shouldReturnThankYouEnjoyTheBookWhenUserDoASuccessfulCheckout() throws ParserConfigurationException, SAXException, IOException {
        String expect = Menu.ENJOY_THE_BOOK.toString();
        new BookService().checkoutAsset(bookAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnBookIsNotAvailableWhenUserDoAUnsuccessfulCheckout() throws IOException, SAXException, ParserConfigurationException {
        String expect = Menu.BOOK_NO_AVAILABLE.toString();
        new BookService().checkoutAsset(bookNoAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnBookDoesNotExitWhenUserTryToCheckoutANoExistentBook() throws IOException, SAXException, ParserConfigurationException {
        String expect = Menu.BOOK_NOT_EXISTS.toString();
        new Book().isAValidBook(null);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnThankYouForRetuningTheBookWhenTheTheReturningIsSuccessful() throws Exception {
        String expected = Menu.THANK_YOU_FOR_RETURNING.toString();
        new BookService().returnAsset(bookNoAvailable);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnInvalidRetuningWhenTheTheReturningIsUnsuccessful() throws Exception {
        String expected = Menu.INVALID_RETURN.toString();
        new BookService().returnAsset(bookAvailable);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnBookDoesNotExitWhenUserTryToReturnAnNoExistentBook() throws IOException, SAXException, ParserConfigurationException {
        String expect = Menu.BOOK_NOT_EXISTS.toString();
        new Book().isAValidBook(null);

        assertEquals(expect, outContent.toString());
    }
}
