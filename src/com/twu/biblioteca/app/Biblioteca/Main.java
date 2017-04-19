package com.twu.biblioteca.app.Biblioteca;

import com.twu.biblioteca.app.ui.Biblioteca;
import com.twu.biblioteca.app.util.Asset;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.sayHello(Asset.WELCOME_MESSAGE.toString());
        biblioteca.start();
    }
}
