package com.twu.biblioteca.app.ui;

import com.twu.biblioteca.app.service.BookService;
import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.util.Asset;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Object> books = new ArrayList<>();
    private List<Object> availableBooks = new ArrayList<>();
    boolean fillBooks = true;

    public void sayHello(String welcomeMessage) {
        System.out.print(welcomeMessage);
    }

    public String printWhiteSpaces(String word, int length) {
        int numCharacters = word.length();
        for (int iterator = numCharacters; iterator < length; iterator ++) {
            word += Asset.BLANK_SPACE.toString();
        }
        return word;
    }

    public String getInputStream() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public int start() throws ParserConfigurationException, SAXException, IOException {
        String option = Asset.OPTION_ZERO.toString();
        menu(option);
        return 0;
    }

    private void fillBooks() throws IOException, SAXException, ParserConfigurationException {
        if (fillBooks == true) {
            books = new BookService().getAssets(Asset.BOOK_FILE.toString());
            availableBooks = new BookService().getAssets(Asset.BOOK_FILE.toString());
            fillBooks = false;
        }
    }

    public String menu(String option) throws IOException, SAXException, ParserConfigurationException {
        String input = "", response = "";
        switch (option) {
            case "0":
                printMenu();
                input = getInputStream();
                menu(input);
                break;

            case "1":
                printBooks();
                input = getInputStream();
                menu(input);
                break;

            case "2":
                response = exit();
                break;

            case "3":
                response = actBook(option);
                menu(Asset.OPTION_ZERO.toString());
                break;

            case "4":
                response = actBook(option);
                menu(Asset.OPTION_ZERO.toString());
                break;

            default:
                response = optionDefault();
                System.out.println(response);
                menu(Asset.OPTION_ZERO.toString());
                break;
        }
        return response;
    }

    public void printMenu() {
        System.out.print(Asset.CHOOSE_OPTIONS.toString()
                + Asset.OPTION_ONE_FULL.toString()
                + Asset.OPTION_TWO_FULL.toString()
                + Asset.OPTION_THREE_FULL.toString()
                + Asset.OPTION_FOUR_FULL);
    }

    public void printBooks() throws ParserConfigurationException, SAXException, IOException {
        fillBooks();
        new BookService().showAssets(availableBooks);
    }

    public String exit() {
        return Asset.OPTION_TWO.name();
    }

    public String actBook(String option) throws IOException {
        System.out.println(Asset.ENTER_THE_BOOK_NAME.toString());
        String input = getInputStream();
        Book book = (Book) new BookService().isAssetInAssets(books, input);

        if (option.equals(Asset.OPTION_THREE.toString())) {
            availableBooks = new BookService().checkoutAsset(availableBooks, book);
            return Asset.OPTION_THREE.name();
        } else {
            availableBooks = new BookService().returnAsset(availableBooks, book);
            return Asset.OPTION_FOUR.name();
        }
    }

    public String optionDefault() {
        return Asset.SELECT_A_VALID_OPTION.toString();
    }

    public void printMovies() {
    }
}
