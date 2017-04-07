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
    boolean fillBooks = true;

    public void sayHello(String welcomeMessage) {
        System.out.print(welcomeMessage);
    }

    public void printLines(int numberLineBreak) {
        for (int lines = 0; lines < numberLineBreak; lines ++)
            System.out.println();
    }

    public String printWhiteSpaces(String word, int length) {
        int numCharacters = word.length();
        for (int iterator = numCharacters; iterator < length; iterator ++) {
            word += " ";
        }
        return word;
    }

    public String getInputStream() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public void startMenu(String option) throws ParserConfigurationException, SAXException, IOException {
        if (option.equals(EMenu.OPTION_ZERO.toString()) || option.equals(EMenu.OPTION_ONE.toString())) {
            menuOption(option);
            option = getInputStream();
            startMenu(option);
        } else if (option.equals(EMenu.OPTION_TWO.toString())) {
            String response = menuOption(option);
            System.out.println(response);
        } else if (option.equals(EMenu.OPTION_THREE.toString())) {
            System.out.println("Enter the book name:");
            String bookName = getInputStream();
            Book book = Book.isBookInBooks(books, bookName);
            checkoutBook(book);
            books = Book.removeCheckoutsFromBooks(books, book);
            startMenu(EMenu.OPTION_ZERO.toString());
        } else {
            String response = menuOption(option);
            System.out.println(response);
            startMenu(EMenu.OPTION_ZERO.toString());
        }
    }

    private void fillBooks() throws IOException, SAXException, ParserConfigurationException {
        if (fillBooks == true) {
            books = Book.getBooks(EMenu.BOOK_FILE.toString());
            fillBooks = false;
        }
    }

    public String checkoutBook(Book book) throws IOException, SAXException, ParserConfigurationException {
        if (book != null) {
            Book.checkoutBook(book);
            return null;
        } else {
            System.out.print(EMenu.BOOK_NOT_EXISTS.toString());
            startMenu(EMenu.OPTION_THREE.toString());
            return EMenu.BOOK_NOT_EXISTS.toString();
        }
    }

    public String menuOption(String option) throws IOException, SAXException, ParserConfigurationException {
        String response = null;
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
                Book.showBooks(books);
                break;

            case "2":
                response = EMenu.QUIT.toString();
                break;

            default:
                response = EMenu.SELECT_A_VALID_OPTION.toString();
                break;
        }
        return response;
    }
}
