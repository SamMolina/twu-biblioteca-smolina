package com.twu.biblioteca.util;

import com.twu.biblioteca.vo.Book;
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

public class DOMParser {

    public List<Book> parserFile(String file) throws IOException, SAXException, ParserConfigurationException {
        File xmlFile= new File(file);
        Document doc = getDocument(xmlFile);
        return readDOMTree(doc);
    }

    private Document getDocument(File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
        dbFactory.setValidating(true);
        DocumentBuilder parser = dbFactory.newDocumentBuilder();
        return parser.parse(xmlFile);
    }

    private List<Book> readDOMTree(Document doc) {
        List<Book> books = new ArrayList<>();
        NodeList nodeList = doc.getElementsByTagName(EMenu.BOOK.toString());
        NodeList titleList = doc.getElementsByTagName(EMenu.TITLE.toString());
        NodeList authorList = doc.getElementsByTagName(EMenu.AUTHOR.toString());
        NodeList yearList = doc.getElementsByTagName(EMenu.YEAR.toString());

        for (int numberNodes = 0; numberNodes < nodeList.getLength(); numberNodes++) {
            books.add(new Book(titleList.item(numberNodes).getTextContent(),
                    authorList.item(numberNodes).getTextContent(),
                    yearList.item(numberNodes).getTextContent(), false));
        }
        return books;
    }

}
