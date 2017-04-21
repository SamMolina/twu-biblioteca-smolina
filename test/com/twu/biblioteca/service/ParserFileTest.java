package com.twu.biblioteca.service;

import com.twu.biblioteca.app.impl.XMLFileParser;
import com.twu.biblioteca.app.model.User;
import com.twu.biblioteca.app.util.BibliotecaConstants;
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
    private String bookFileName, movieFileName, invalidFileName;
    private XMLFileParser parser;
    private String customerFileName;

    @Before
    public void setUp() {
        parser = Mockito.mock(XMLFileParser.class);
        bookFileName = BibliotecaConstants.BOOK_FILE.toString();
        movieFileName = BibliotecaConstants.MOVIE_FILE.toString();
        customerFileName = BibliotecaConstants.USER_FILE.toString();
        invalidFileName = StringsGenerator.generateRandomChars(5);
    }

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

    @Test
    public void shouldReturnUsersListWhenReadTheXMLUsersDocument() throws Exception {
        Document customersDocument = parser.parserDocument(parser.createXMLFile(customerFileName));
        List<Object> users = new ArrayList<>();

        Mockito.when(parser.readDocumentUser(customersDocument)).thenReturn(users);
    }
}
