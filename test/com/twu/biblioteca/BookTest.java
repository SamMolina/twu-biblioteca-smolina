package com.twu.biblioteca;

import com.twu.biblioteca.app.Menu;
import com.twu.biblioteca.util.EMenu;
import com.twu.biblioteca.vo.Book;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BookTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Book bookAvailable, bookNoAvailable;
    private String fileName;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        bookAvailable = new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false);
        bookNoAvailable = new Book("Great Expectations","Charles Dickens", "1861", true);
        fileName = EMenu.BOOK_FILE.toString();
    }

    @Test
    public void shouldReturnBooksFromAXMLFile() throws IOException, SAXException, ParserConfigurationException {
        List<Book> booksExpected = new ArrayList<>();
        List<Book> booksActual = Book.getBooks(fileName);
        booksExpected.add(new Book("The Shadow of the Wind", "Carlos Ruíz Zafón", "2001", false));
        booksExpected.add(new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false));
        booksExpected.add(new Book("Little Women","Louisa May Alcott", "1868", false));
        booksExpected.add(new Book("Great Expectations","Charles Dickens", "1861", false));
        booksExpected.add(new Book("Les Misérables","Victor Hugo", "1862", false));

        assertEquals(booksExpected, booksActual);
    }

    @Test
    public void shouldReturnTrueWhenSearchingABookThatIsInBooks() throws Exception {
        List<Book> books = Book.getBooks(fileName);
        Book bookToSearch = new Book("The Shadow of the Wind", "Carlos Ruíz Zafón", "2001", false);

        boolean expected = true;
        boolean actual = Book.isBookInBooks(books, bookToSearch);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnFalseWhenSearchingABookThatNoIsInBooks() throws Exception {
        List<Book> books = Book.getBooks(fileName);
        Book bookToSearch = new Book("The Shadow of the Wind 1", "Carlos Ruíz Zafón", "2001", false);

        boolean expected = false;
        boolean actual = Book.isBookInBooks(books, bookToSearch);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTheAvailableBooks() throws Exception {
        List<Book> books = Book.getBooks(fileName);
        books.get(0).setCheckout(true);
        books.get(4).setCheckout(true);
        List<Book> booksAvailableExpected = new ArrayList<>();

        List<Book> booksAvailableActual = Book.getAvailableBooks(books);
        booksAvailableExpected.add(new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false));
        booksAvailableExpected.add(new Book("Little Women","Louisa May Alcott", "1868", false));
        booksAvailableExpected.add(new Book("Great Expectations","Charles Dickens", "1861", false));

        assertEquals(booksAvailableExpected, booksAvailableActual);
    }

    @Test
    public void shouldReturnABookUsingTheNameOfTheBook() throws Exception {
        List<Book> books = Book.getBooks(EMenu.BOOK_FILE.toString());

        Book bookExpected = new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false);
        Book bookActual = Book.isBookInBooks(books, "The Angel's Game");

        assertEquals(bookExpected, bookActual);
    }

    @Test
    public void shouldReturnTheBookDoesNotExitWhenEnteringABookNameThatNoIsInTheList() throws IOException, SAXException, ParserConfigurationException {
        List<Book> books = Book.getBooks(EMenu.BOOK_FILE.toString());;

        Book bookActual = Book.isBookInBooks(books, "The Angel's Game 1");

        assertNull(bookActual);
    }

    @Test
    public void shouldModifyTheCheckoutValueFromABookWhenCheckingOutABook() throws Exception {
        boolean expect = true;
        Book.checkoutBook(bookAvailable);

        assertEquals(expect, bookAvailable.getCheckout());
    }

    @Test
    public void shouldReturnThankYouEnjoyTheBookWhenUserDoASuccessfulCheckout() throws ParserConfigurationException, SAXException, IOException {
        String expect = EMenu.ENJOY_THE_BOOK.toString();
        Book.checkoutBook(bookAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldReturnBookIsNotAvailableWhenUserDoAUnsuccessfulCheckout() throws IOException, SAXException, ParserConfigurationException {
        String expect = EMenu.BOOK_NO_AVAILABLE.toString();
        Book.checkoutBook(bookNoAvailable);

        assertEquals(expect, outContent.toString());
    }

    @Test
    public void shouldRefreshBooksWhenCheckingABook() throws Exception {
        List<Book> books = Book.getBooks(EMenu.BOOK_FILE.toString());
        Book book = Book.checkoutBook(bookAvailable);

        List<Book> booksActual = Book.removeCheckoutsFromBooks(books, book);
        List<Book> booksExpected = new ArrayList<>();
        booksExpected.add(new Book("The Shadow of the Wind", "Carlos Ruíz Zafón", "2001", false));
        booksExpected.add(new Book("Little Women","Louisa May Alcott", "1868", false));
        booksExpected.add(new Book("Great Expectations","Charles Dickens", "1861", false));
        booksExpected.add(new Book("Les Misérables","Victor Hugo", "1862", false));

        assertEquals(booksExpected, booksActual);
    }

    @Test
    public void should() throws Exception {
    }
}
