package com.twu.biblioteca.app;

import com.twu.biblioteca.util.EMenu;
import com.twu.biblioteca.vo.Book;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Book> books = new ArrayList<>();
    private List<Book> availableBooks = new ArrayList<>();
    boolean fillBooks = true;

    public void sayHello(String welcomeMessage) {
        System.out.print(welcomeMessage);
    }

    public void printLines(int numberLineBreak) {
        for (int lines = 0; lines < numberLineBreak; lines ++)
            System.out.print(EMenu.LINE_BREAK);
    }

    public String printWhiteSpaces(String word, int length) {
        int numCharacters = word.length();
        for (int iterator = numCharacters; iterator < length; iterator ++) {
            word += EMenu.BLANK_SPACE.toString();
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
        if (response.equals(EMenu.OPTION_THREE.name()) || response.equals(EMenu.OPTION_FOUR.name())) {
            Book book = new Book().isBookInBooks(books, input);
            updateAvailableBooks(response, book);
            startMenu(EMenu.OPTION_ZERO.toString());
        } else if (!response.equals(EMenu.OPTION_TWO.name())) {
            System.out.println(response);
            startMenu(input);
        }
    }

    public void updateAvailableBooks (String option, Book book) throws IOException, SAXException, ParserConfigurationException {
        if (option.equals(EMenu.OPTION_THREE.name())) {
            book.checkoutBook(book);
            availableBooks = book.checkoutBook(availableBooks, book);
        } else if (option.equals(EMenu.OPTION_FOUR.name())) {
            book.returnBook(book);
            availableBooks = book.returnBook(availableBooks, book);
        }
    }

    private void fillBooks() throws IOException, SAXException, ParserConfigurationException {
        if (fillBooks == true) {
            books = new Book().getBooks(EMenu.BOOK_FILE.toString());
            availableBooks = new Book().getBooks(EMenu.BOOK_FILE.toString());
            fillBooks = false;
        }
    }

    public String menuOption(String option) throws IOException, SAXException, ParserConfigurationException {
        String response = "";
        switch (option) {
            case "0":
                System.out.print(EMenu.CHOOSE_OPTIONS.toString()
                        + EMenu.OPTION_ONE_FULL.toString()
                        + EMenu.OPTION_TWO_FULL.toString()
                        + EMenu.OPTION_THREE_FULL.toString()
                        + EMenu.OPTION_FOUR_FULL);
                break;

            case "1":
                fillBooks();
                new Book().showBooks(availableBooks);
                break;

            case "2":
                response = EMenu.OPTION_TWO.name();
                break;

            case "3":
                System.out.println(EMenu.ENTER_THE_BOOK_NAME.toString());
                response = EMenu.OPTION_THREE.name();
                break;

            case "4":
                System.out.println(EMenu.ENTER_THE_BOOK_NAME);
                response = EMenu.OPTION_FOUR.name();
                break;

            default:
                response = EMenu.SELECT_A_VALID_OPTION.toString();
                break;
        }
        return response;
    }
}
