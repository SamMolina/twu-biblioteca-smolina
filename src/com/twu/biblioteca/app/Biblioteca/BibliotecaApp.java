package com.twu.biblioteca.app.Biblioteca;

import com.twu.biblioteca.app.util.Menu;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        com.twu.biblioteca.app.ui.Menu menu = new com.twu.biblioteca.app.ui.Menu();
        menu.sayHello(Menu.WELCOME_MESSAGE.toString());
        menu.startMenu(Menu.OPTION_ZERO.toString());
    }
}
