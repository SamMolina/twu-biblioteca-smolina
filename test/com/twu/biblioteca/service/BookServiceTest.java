package com.twu.biblioteca.service;

import com.twu.biblioteca.app.impl.AssetService;
import com.twu.biblioteca.app.model.Book;
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

public class BookServiceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Book bookAvailableOne, bookAvailableTwo, bookNoAvailable;
    private String fileName;
    private AssetService manager = new AssetService();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        bookAvailableOne = new Book("The Shadow of the Wind", "Carlos Ruíz Zafón", "2001", false);
        bookAvailableTwo = new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false);
        bookNoAvailable = new Book("Great Expectations","Charles Dickens", "1861", true);
        fileName = BibliotecaConstants.BOOK_FILE.toString();
    }

    @Test
    public void shouldReturnAValidListOfBooks() throws ParserConfigurationException, SAXException, IOException {
        List<Object> books = new XMLFileParser().parserFile(fileName, BibliotecaConstants.BOOK.toString());

        assertNotEquals(0, books.size());
    }

/*    @Test
    public void shouldModifyTheCheckoutValueFromABookWhenCheckingOutABook() throws Exception {
        List<Object> assets = new BookService().getAssets(fileName);
        //Book

        boolean expect = true;
        assets = manager.checkoutAsset(assets, bookAvailableOne);
        Book book = (Book) assets.get(assets.indexOf(bookAvailableOne));

        assertEquals(expect, book.getCheckout());
    }

    @Test
    public void shouldNoModifyCheckoutValueFromABookWhenReturnABook() throws Exception {
        List<Object> assets = new BookService().getAssets(fileName);

        boolean expected = false;
        assets = manager.returnAsset(assets, bookAvailableOne);
        Book book = (Book) assets.get(assets.indexOf(bookAvailableOne));

        assertEquals(expected, book.getCheckout());
    }*/

    @Test
    public void shouldRemoveTheCheckoutBooksFromAvailableBooks() throws Exception {
        List<Object> assets = new AssetService().getAssets(fileName, BibliotecaConstants.BOOK.toString());
        List<Object> booksAvailableExpected = new AssetService().getAssets(fileName, BibliotecaConstants.BOOK.toString());

        booksAvailableExpected.remove(bookAvailableOne);

        Book book = (Book) assets.get(0);
        book.setCheckout(true);
        assets.set(0, book);

        List<Object> booksAvailableActual = manager.getAvailableAssets(assets);

        assertEquals(booksAvailableExpected, booksAvailableActual);
    }

    @Test
    public void shouldRefreshBooksWhenCheckoutABook() throws Exception {
        List<Object> booksExpected = new AssetService().getAssets(fileName, BibliotecaConstants.BOOK.toString());
        //Book book = (Book) new BookService().checkoutAsset(null, bookAvailableOne);

        List<Book> booksActual = (List<Book>) manager.checkoutAsset(booksExpected, bookAvailableOne);
        booksExpected.remove(bookAvailableOne);

        assertEquals(booksExpected, booksActual);
    }

    @Test
    public void shouldRefreshBooksWhenReturnABook() throws Exception {
        List<Object> booksExpected = new AssetService().getAssets(fileName, BibliotecaConstants.BOOK.toString());
        List<Object> booksActual;

        //Book bookOne = (Book) new BookService().checkoutAsset(null, bookAvailableOne);
        //Book bookTwo = (Book) new BookService().checkoutAsset(null, bookAvailableTwo);

        booksActual = manager.checkoutAsset(booksExpected, bookAvailableOne);
        booksActual = manager.checkoutAsset(booksActual, bookAvailableTwo);

        booksActual = manager.returnAsset(booksActual, bookAvailableOne);
        booksExpected.remove(bookAvailableTwo);

        assertEquals(booksExpected, booksActual);
    }
}
