package com.twu.biblioteca.service;

import com.twu.biblioteca.app.service.XMLFileParser;
import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.model.Movie;
import com.twu.biblioteca.app.util.Menu;
import com.twu.biblioteca.app.util.StringsGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ParserFileTest {
    //private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //private Book bookAvailableOne, bookAvailableTwo, bookAvailableThree, bookAvailableFour,bookAvailableFive, bookNoAvailable;
    private String bookFileName, movieFileName, invalidFileName;
    private XMLFileParser parser;

    @Before
    public void setUp() {
        //System.setOut(new PrintStream(outContent));
        parser = Mockito.mock(XMLFileParser.class);
        bookFileName = Menu.BOOK_FILE.toString();
        movieFileName = Menu.MOVIE_FILE.toString();
        invalidFileName = StringsGenerator.generateRandomChars(5);
    }
/*

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        bookAvailableOne = new Book("The Shadow of the Wind", "Carlos Ruíz Zafón", "2001", false);
        bookAvailableTwo = new Book("The Angel's Game","Carlos Ruíz Zafón", "2009", false);
        bookAvailableThree = new Book("Little Women","Louisa May Alcott", "1868", false);
        bookAvailableFour = new Book("Great Expectations","Charles Dickens", "1861", false);
        bookAvailableFive = new Book("Les Misérables","Victor Hugo", "1862", false);
        bookNoAvailable = new Book("Great Expectations","Charles Dickens", "1861", true);
        bookFileName = Menu.BOOK_FILE.toString();
    }

    @Test
    public void shouldReturnBooksFromAXMLFile() throws IOException, SAXException, ParserConfigurationException {
        List<Book> booksExpected = new ArrayList<>();
        List<Book> booksActual;

        booksExpected.add(bookAvailableOne);
        booksExpected.add(bookAvailableTwo);
        booksExpected.add(bookAvailableThree);
        booksExpected.add(bookAvailableFour);
        booksExpected.add(bookAvailableFive);
        booksActual = new Book().getBooks(bookFileName);

        assertEquals(booksExpected, booksActual);
    }
*/

    @Test
    public void shouldReturnAXMLFileWhenCreateAXMLFile() {
        File fileExpected = new File(bookFileName);

        Mockito.when(parser.createXMLFile(bookFileName)).thenReturn(fileExpected);
    }

    @Test
    public void shouldReturnADocumentWhenParserAXMLFile() throws IOException, SAXException, ParserConfigurationException {
        File file = new XMLFileParser().createXMLFile(bookFileName);

        Document expected = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

        Mockito.when(parser.parserDocument(parser.createXMLFile(bookFileName))).thenReturn(expected);
    }

    @Test
    public void shouldReturnFileNotFoundExceptionADocumentWhenParserAnInvalidXMLFileName() throws IOException, SAXException, ParserConfigurationException {
        Mockito.when(parser.parserDocument(parser.createXMLFile(invalidFileName))).thenThrow(new FileNotFoundException());
    }

    @Test
    public void shouldReturnBooksListWhenReadTheXMLBooksDocument() throws IOException, SAXException, ParserConfigurationException {
        List<Object> books = new ArrayList<>();

        Document bookDocument = parser.parserDocument(parser.createXMLFile(bookFileName));

        Mockito.when(parser.readDocument(bookDocument)).thenReturn(books);
    }

    @Test
    public void shouldReturnMoviesListWhenReadTheXMLMoviesDocument() throws IOException, SAXException, ParserConfigurationException {
        Document movieDocument = parser.parserDocument(parser.createXMLFile(movieFileName));
        List<Object> movies = new ArrayList<>();

        Mockito.when(parser.readDocumentMovie(movieDocument)).thenReturn(movies);
    }
}
