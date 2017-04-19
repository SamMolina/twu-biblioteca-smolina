package com.twu.biblioteca.app.service;

import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.model.Movie;
import com.twu.biblioteca.app.util.AssetConstants;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLFileParser {

    public File createXMLFile (String file) {
        File xmlFile= new File(file);
        return  xmlFile;
    }

    public List<Object> parserFile(String file, String typeAsset) throws IOException, SAXException, ParserConfigurationException {
        File xmlFile= createXMLFile(file);
        Document doc = parserDocument(xmlFile);

        if (typeAsset.equals(AssetConstants.BOOK.toString())) {
            return readDocument(doc);
        } else if (typeAsset.equals(AssetConstants.MOVIE.toString())) {
            return readDocumentMovie(doc);
        }

        return null;
    }

    public Document parserDocument(File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setValidating(true);
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        return doc;
    }

    public List<Object> readDocument(Document doc) {
        List<Object> books = new ArrayList<>();
        NodeList nodeList = doc.getElementsByTagName(AssetConstants.BOOK.toString());

        for (int numberNodes = 0; numberNodes < nodeList.getLength(); numberNodes++) {
            NodeList informationBook = nodeList.item(numberNodes).getChildNodes();
            books.add(new Book(informationBook.item(1).getTextContent(),
                    informationBook.item(3).getTextContent(),
                    informationBook.item(5).getTextContent(),
                    false));
        }

        return books;
    }

    public List<Object> readDocumentMovie(Document doc) {
        List<Object> movies = new ArrayList<>();
        NodeList nodeList = doc.getElementsByTagName(AssetConstants.MOVIE.toString());

        for (int numberNodes = 0; numberNodes < nodeList.getLength(); numberNodes++) {
            NodeList informationMovie = nodeList.item(numberNodes).getChildNodes();
            movies.add(new Movie(informationMovie.item(1).getTextContent(),
                    informationMovie.item(3).getTextContent(),
                    informationMovie.item(5).getTextContent(),
                    Integer.parseInt(informationMovie.item(7).getTextContent()),
                    false));
        }

        return movies;
    }
}
