package com.twu.biblioteca.impl;

import com.twu.biblioteca.app.impl.AssetService;
import com.twu.biblioteca.app.util.BibliotecaConstants;
import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.util.StringsGenerator;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BookTest {
    private Book bookAvailable;
    private String fileName, bookUnavailable;

    @Before
    public void setUp() {
        bookAvailable = new Book("The Shadow of the Wind", "Carlos Ruíz Zafón", "2001", false);
        fileName = BibliotecaConstants.BOOK_FILE.toString();
        bookUnavailable = StringsGenerator.generateRandomChars(5);
    }

    @Test
    public void shouldReturnTheSameBookObjectWhenCreateABookObjectWithTheSameFields() {
        Random random = new Random();
        String title = StringsGenerator.generateRandomChars(5);
        String author = StringsGenerator.generateRandomChars(5);
        String year = String.valueOf(random.nextInt(2017));
        boolean checkout = false;

        Book bookExpected = new Book(title,author, year, checkout);
        Book bookActual = new Book(title,author, year, checkout);

        assertEquals(bookExpected, bookActual);
    }

    @Test
    public void shouldReturnTrueWhenSearchingABookThatNotInBooks() throws IOException, SAXException, ParserConfigurationException {
        List<Object> assets = new AssetService().getAssets(fileName, BibliotecaConstants.BOOK.toString());
        Book bookToSearch = bookAvailable;

        boolean expected = true;
        boolean actual = new AssetService().isAssetInAssets(assets, bookToSearch);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnFalseWhenSearchingABookThatNoIsInBooks() throws IOException, SAXException, ParserConfigurationException {
        List<Object> books = new AssetService().getAssets(fileName, BibliotecaConstants.BOOK.toString());
        Book bookToSearch = bookAvailable;
        bookToSearch.setName(bookUnavailable);

        boolean expected = false;
        boolean actual = new AssetService().isAssetInAssets(books, bookToSearch);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnABookUsingTheNameOfTheBook() throws Exception {
        List<Object> books = new AssetService().getAssets(BibliotecaConstants.BOOK_FILE.toString(), BibliotecaConstants.BOOK.toString());

        Book bookExpected = bookAvailable;
        Book bookActual = (Book) new AssetService().isAssetInAssets(books, bookAvailable.getName());

        assertEquals(bookExpected, bookActual);
    }

    @Test
    public void shouldReturnNullWhenEnteringABookNameThatNoIsInTheList() throws IOException, SAXException, ParserConfigurationException {
        List<Object> books = new AssetService().getAssets(BibliotecaConstants.BOOK_FILE.toString(), BibliotecaConstants.BOOK.toString());

        Book bookActual = (Book) new AssetService().isAssetInAssets(books, bookUnavailable);

        assertNull(bookActual);
    }

}
