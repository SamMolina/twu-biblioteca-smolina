package com.twu.biblioteca.service;

import com.twu.biblioteca.app.service.BookService;
import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.util.Menu;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ManagerBookTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Book bookAvailableOne, bookAvailableTwo, bookNoAvailable;
    private String fileName;
    private BookService manager = new BookService();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        bookAvailableOne = new Book("The Shadow of the Wind", "Carlos Ruíz Zafón", "2001", false);
        bookAvailableTwo = new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false);
        bookNoAvailable = new Book("Great Expectations","Charles Dickens", "1861", true);
        fileName = Menu.BOOK_FILE.toString();
    }

    @Test
    public void shouldModifyTheCheckoutValueFromABookWhenCheckingOutABook() throws Exception {
        boolean expect = true;
        Book book = manager.checkoutBook(bookAvailableOne);

        assertEquals(expect, book.getCheckout());
    }

    @Test
    public void shouldNoModifyCheckoutValueFromABookWhenReturnABook() throws Exception {
        boolean expected = false;
        Book book = new BookService().returnBook(bookNoAvailable);

        assertEquals(expected, book.getCheckout());
    }

    @Test
    public void shouldRemoveTheCheckoutBooksFromAvailableBooks() throws Exception {
        List<Book> books = new BookService().getBooks(fileName);
        List<Book> booksAvailableExpected = new BookService().getBooks(fileName);

        booksAvailableExpected.remove(bookAvailableOne);

        books.get(0).setCheckout(true);

        List<Book> booksAvailableActual = manager.getAvailableBooks(books);

        assertEquals(booksAvailableExpected, booksAvailableActual);
    }

    @Test
    public void shouldRefreshBooksWhenCheckoutABook() throws Exception {
        List<Book> booksExpected = new BookService().getBooks(Menu.BOOK_FILE.toString());
        Book book = new BookService().checkoutBook(bookAvailableOne);

        List<Book> booksActual = new BookService().checkoutBook(booksExpected, book);
        booksExpected.remove(bookAvailableOne);

        assertEquals(booksExpected, booksActual);
    }

    @Test
    public void shouldRefreshBooksWhenReturnABook() throws Exception {
        List<Book> booksExpected = new BookService().getBooks(Menu.BOOK_FILE.toString());
        List<Book> booksActual;
        Book bookOne = new BookService().checkoutBook(bookAvailableOne);
        Book bookTwo = new BookService().checkoutBook(bookAvailableTwo);

        booksActual = new BookService().checkoutBook(booksExpected, bookOne);
        booksActual = new BookService().checkoutBook(booksActual, bookTwo);

        booksActual = new BookService().returnBook(booksActual, bookOne);
        booksExpected.remove(bookAvailableTwo);

        assertEquals(booksExpected, booksActual);
    }
}
