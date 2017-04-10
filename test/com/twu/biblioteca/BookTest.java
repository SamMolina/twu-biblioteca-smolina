package com.twu.biblioteca;

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
    private Book bookAvailableOne, bookAvailableTwo, bookAvailableThree, bookAvailableFour,bookAvailableFive, bookNoAvailable;
    private String fileName;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        bookAvailableOne = new Book("The Shadow of the Wind", "Carlos Ruíz Zafón", "2001", false);
        bookAvailableTwo = new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false);
        bookAvailableThree = new Book("Little Women","Louisa May Alcott", "1868", false);
        bookAvailableFour = new Book("Great Expectations","Charles Dickens", "1861", false);
        bookAvailableFive = new Book("Les Misérables","Victor Hugo", "1862", false);
        bookNoAvailable = new Book("Great Expectations","Charles Dickens", "1861", true);
        fileName = EMenu.BOOK_FILE.toString();
    }

    @Test
    public void shouldReturnBooksFromAXMLFile() throws IOException, SAXException, ParserConfigurationException {
        List<Book> booksExpected = new ArrayList<>();
        List<Book> booksActual = new Book().getBooks(fileName);
        booksExpected.add(bookAvailableOne);
        booksExpected.add(bookAvailableTwo);
        booksExpected.add(bookAvailableThree);
        booksExpected.add(bookAvailableFour);
        booksExpected.add(bookAvailableFive);

        assertEquals(booksExpected, booksActual);
    }

    @Test
    public void shouldReturnTrueWhenSearchingABookThatIsInBooks() throws Exception {
        List<Book> books = new Book().getBooks(fileName);
        Book bookToSearch = bookAvailableOne;

        boolean expected = true;
        boolean actual = new Book().isBookInBooks(books, bookToSearch);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnFalseWhenSearchingABookThatNoIsInBooks() throws Exception {
        List<Book> books = new Book().getBooks(fileName);
        Book bookToSearch = new Book("The Shadow of the Wind 1", "Carlos Ruíz Zafón", "2001", false);

        boolean expected = false;
        boolean actual = new Book().isBookInBooks(books, bookToSearch);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTheAvailableBooks() throws Exception {
        List<Book> books = new Book().getBooks(fileName);
        books.get(0).setCheckout(true);
        books.get(4).setCheckout(true);
        List<Book> booksAvailableExpected = new ArrayList<>();

        List<Book> booksAvailableActual = new Book().getAvailableBooks(books);
        booksAvailableExpected.add(bookAvailableTwo);
        booksAvailableExpected.add(bookAvailableThree);
        booksAvailableExpected.add(bookAvailableFour);

        assertEquals(booksAvailableExpected, booksAvailableActual);
    }

    @Test
    public void shouldReturnABookUsingTheNameOfTheBook() throws Exception {
        List<Book> books = new Book().getBooks(EMenu.BOOK_FILE.toString());

        Book bookExpected = new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false);
        Book bookActual = new Book().isBookInBooks(books, "The Angel's Game");

        assertEquals(bookExpected, bookActual);
    }

    @Test
    public void shouldReturnTheBookDoesNotExitWhenEnteringABookNameThatNoIsInTheList() throws IOException, SAXException, ParserConfigurationException {
        List<Book> books = new Book().getBooks(EMenu.BOOK_FILE.toString());;

        Book bookActual = new Book().isBookInBooks(books, "The Angel's Game 1");

        assertNull(bookActual);
    }

    @Test
    public void shouldModifyTheCheckoutValueFromABookWhenCheckingOutABook() throws Exception {
        boolean expect = true;
        Book book = new Book().checkoutBook(bookAvailableOne);

        assertEquals(expect, book.getCheckout());
    }

    @Test
    public void shouldRefreshBooksWhenCheckoutABook() throws Exception {
        List<Book> books = new Book().getBooks(EMenu.BOOK_FILE.toString());
        Book book = new Book().checkoutBook(bookAvailableOne);

        List<Book> booksActual = new Book().checkoutBook(books, book);
        List<Book> booksExpected = new ArrayList<>();
        booksExpected.add(bookAvailableTwo);
        booksExpected.add(bookAvailableThree);
        booksExpected.add(bookAvailableFour);
        booksExpected.add(bookAvailableFive);

        assertEquals(booksExpected, booksActual);
    }

    @Test
    public void shouldModifyCheckoutValueFromABookWhenReturnABook() throws Exception {
        boolean expected = false;
        Book book = new Book().returnBook(bookNoAvailable);

        assertEquals(expected, book.getCheckout());
    }

    @Test
    public void shouldRefreshBooksWhenReturnABook() throws Exception {
        List<Book> booksActual;
        List<Book> books = new Book().getBooks(EMenu.BOOK_FILE.toString());
        Book bookOne = new Book().checkoutBook(bookAvailableOne);
        Book bookTwo = new Book().checkoutBook(bookAvailableTwo);

        booksActual = new Book().checkoutBook(books, bookOne);
        booksActual = new Book().checkoutBook(booksActual, bookTwo);

        booksActual = new Book().returnBook(booksActual, bookOne);
        List<Book> booksExpected = new ArrayList<>();
        booksExpected.add(bookAvailableThree);
        booksExpected.add(bookAvailableFour);
        booksExpected.add(bookAvailableFive);
        booksExpected.add(bookAvailableOne);

        assertEquals(booksExpected, booksActual);
    }


}
