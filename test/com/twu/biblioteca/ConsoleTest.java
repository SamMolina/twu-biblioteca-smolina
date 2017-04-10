package com.twu.biblioteca;

import com.twu.biblioteca.app.Menu;
import com.twu.biblioteca.util.EMenu;
import com.twu.biblioteca.vo.Book;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ConsoleTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Book bookAvailable, bookNoAvailable;
    private String file;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        file = EMenu.BOOK_FILE.toString();
        bookAvailable = new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false);
        bookNoAvailable = new Book("Great Expectations","Charles Dickens", "1861", true);
    }

    @Test
    public void shouldShowTheWelcomeMessage() {
        String expected = EMenu.WELCOME_MESSAGE.toString();
        new Menu().sayHello(expected);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowAWhiteSpace() {
        String expected = "\n\n";
        new Menu().printLines(2);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowTheBookList() throws IOException, SAXException, ParserConfigurationException {
        Menu menu = new Menu();
        List<Book> books = new Book().getBooks(file);

        String expected = EMenu.SHOW_BOOKS.toString();
        expected += new Book().formatBookInformation(EMenu.TITLE.name(), EMenu.AUTHOR.name(), EMenu.YEAR.name());
        for (Book book: books) {
            expected += new Book().formatBookInformation(book.getTitle(), book.getAuthor(), book.getYear());
        }
        new Menu().menuOption(EMenu.OPTION_ONE.toString());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldShowTheMenuApp() throws ParserConfigurationException, SAXException, IOException {
        String expected = EMenu.CHOOSE_OPTIONS.toString();
        expected += EMenu.OPTION_ONE_FULL;
        expected += EMenu.OPTION_TWO_FULL;
        expected += EMenu.OPTION_THREE_FULL;
        expected += EMenu.OPTION_FOUR_FULL;

        new Menu().menuOption(EMenu.OPTION_ZERO.toString());

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnSelectAValidOptionWhenUserEnterAnInvalidOption() throws ParserConfigurationException, SAXException, IOException {
        Random random = new Random();
        String invalidOption = generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 4);

        String expect = EMenu.SELECT_A_VALID_OPTION.toString();
        String actual = new Menu().menuOption(String.valueOf(invalidOption));

        assertEquals(expect, actual);
    }

    public static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }

        return sb.toString();
    }

    @Test
    public void shouldReturnQuitWhenIExitOfTheApp() throws ParserConfigurationException, SAXException, IOException {
        String expect = EMenu.OPTION_TWO.name();
        String actual = new Menu().menuOption(EMenu.OPTION_TWO.toString());

        assertEquals(expect, actual);
    }

    @Test
    public void shouldReturnThankYouEnjoyTheBookWhenUserDoASuccessfulCheckout() throws ParserConfigurationException, SAXException, IOException {
        String expect = EMenu.ENJOY_THE_BOOK.toString();
        new Book().checkoutBook(bookAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnBookIsNotAvailableWhenUserDoAUnsuccessfulCheckout() throws IOException, SAXException, ParserConfigurationException {
        String expect = EMenu.BOOK_NO_AVAILABLE.toString();
        new Book().checkoutBook(bookNoAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnBookDoesNotExitWhenUserTryToCheckoutANoExistentBook() throws IOException, SAXException, ParserConfigurationException {
        String expect = EMenu.BOOK_NOT_EXISTS.toString();
        new Book().isAValidBook(null);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnThankYouForRetuningTheBookWhenTheTheReturningIsSuccessful() throws Exception {
        String expected = EMenu.THANK_YOU_FOR_RETURNING.toString();
        new Book().returnBook(bookNoAvailable);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnInvalidRetuningWhenTheTheReturningIsUnsuccessful() throws Exception {
        String expected = EMenu.INVALID_RETURN.toString();
        new Book().returnBook(bookAvailable);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void shouldReturnBookDoesNotExitWhenUserTryToReturnAnNoExistentBook() throws IOException, SAXException, ParserConfigurationException {
        String expect = EMenu.BOOK_NOT_EXISTS.toString();
        new Book().isAValidBook(null);

        assertEquals(expect, outContent.toString());
    }
}
