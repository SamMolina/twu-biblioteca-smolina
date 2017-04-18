package com.twu.biblioteca.app.ui;

import com.twu.biblioteca.app.service.BookService;
import com.twu.biblioteca.app.model.Book;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Object> books = new ArrayList<>();
    private List<Object> availableBooks = new ArrayList<>();
    boolean fillBooks = true;

    public void sayHello(String welcomeMessage) {
        System.out.print(welcomeMessage);
    }

    public void printLines(int numberLineBreak) {
        for (int lines = 0; lines < numberLineBreak; lines ++)
            System.out.print(com.twu.biblioteca.app.util.Menu.LINE_BREAK);
    }

    public String printWhiteSpaces(String word, int length) {
        int numCharacters = word.length();
        for (int iterator = numCharacters; iterator < length; iterator ++) {
            word += com.twu.biblioteca.app.util.Menu.BLANK_SPACE.toString();
        }
        return word;
    }

    public String getInputStream() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public void startMenu(String option) throws ParserConfigurationException, SAXException, IOException {
        String response = menuOption(option);
        String input = getInputStream();
        if (response.equals(com.twu.biblioteca.app.util.Menu.OPTION_THREE.name()) || response.equals(com.twu.biblioteca.app.util.Menu.OPTION_FOUR.name())) {
            Book book = (Book) new BookService().isAssetInAssets(books, input);
            updateAvailableBooks(response, book);
            startMenu(com.twu.biblioteca.app.util.Menu.OPTION_ZERO.toString());
        } else if (!response.equals(com.twu.biblioteca.app.util.Menu.OPTION_TWO.name())) {
            System.out.println(response);
            startMenu(input);
        }
    }

    public void updateAvailableBooks (String option, Book book) {
        if (option.equals(com.twu.biblioteca.app.util.Menu.OPTION_THREE.name())) {
            new BookService().checkoutAsset(book);
            availableBooks = new BookService().checkoutAsset(availableBooks, book);
        } else if (option.equals(com.twu.biblioteca.app.util.Menu.OPTION_FOUR.name())) {
            new BookService().returnAsset(book);
            availableBooks = new BookService().returnAsset(availableBooks, book);
        }
    }

    private void fillBooks() throws IOException, SAXException, ParserConfigurationException {
        if (fillBooks == true) {
            books = new BookService().getAssets(com.twu.biblioteca.app.util.Menu.BOOK_FILE.toString());
            availableBooks = new BookService().getAssets(com.twu.biblioteca.app.util.Menu.BOOK_FILE.toString());
            fillBooks = false;
        }
    }

    public String menuOption(String option) throws IOException, SAXException, ParserConfigurationException {
        String response = "";
        switch (option) {
            case "0":
                System.out.print(com.twu.biblioteca.app.util.Menu.CHOOSE_OPTIONS.toString()
                        + com.twu.biblioteca.app.util.Menu.OPTION_ONE_FULL.toString()
                        + com.twu.biblioteca.app.util.Menu.OPTION_TWO_FULL.toString()
                        + com.twu.biblioteca.app.util.Menu.OPTION_THREE_FULL.toString()
                        + com.twu.biblioteca.app.util.Menu.OPTION_FOUR_FULL);
                break;

            case "1":
                fillBooks();
                new BookService().showAssets(availableBooks);
                break;

            case "2":
                response = com.twu.biblioteca.app.util.Menu.OPTION_TWO.name();
                break;

            case "3":
                System.out.println(com.twu.biblioteca.app.util.Menu.ENTER_THE_BOOK_NAME.toString());
                response = com.twu.biblioteca.app.util.Menu.OPTION_THREE.name();
                break;

            case "4":
                System.out.println(com.twu.biblioteca.app.util.Menu.ENTER_THE_BOOK_NAME);
                response = com.twu.biblioteca.app.util.Menu.OPTION_FOUR.name();
                break;

            default:
                response = com.twu.biblioteca.app.util.Menu.SELECT_A_VALID_OPTION.toString();
                break;
        }
        return response;
    }
}
