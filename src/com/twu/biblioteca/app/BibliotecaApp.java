package com.twu.biblioteca.app;

import com.twu.biblioteca.util.EMenu;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        Menu menu = new Menu();
        menu.sayHello(EMenu.WELCOME_MESSAGE.toString());
        menu.startMenu(EMenu.OPTION_ZERO.toString());
    }
}
