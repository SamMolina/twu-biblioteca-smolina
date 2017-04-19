package com.twu.biblioteca.app.ui;

import com.twu.biblioteca.app.service.BookService;
import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.service.MovieService;
import com.twu.biblioteca.app.util.AssetConstants;
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
    private List<Object> movies = new ArrayList<>();
    private List<Object> availableMovies = new ArrayList<>();
    boolean fillBooks = true, fillMovies = true;

    public void sayHello(String welcomeMessage) {
        System.out.print(welcomeMessage);
    }

    public String printWhiteSpaces(String word, int length) {
        int numCharacters = word.length();
        for (int iterator = numCharacters; iterator < length; iterator ++) {
            word += AssetConstants.BLANK_SPACE.toString();
        }
        return word;
    }

    public String getInputStream() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public int start() throws ParserConfigurationException, SAXException, IOException {
        String option = AssetConstants.OPTION_ZERO.toString();
        menu(option);
        return 0;
    }

    private void fillBooks() throws IOException, SAXException, ParserConfigurationException {
        if (fillBooks == true) {
            books = new BookService().getAssets(AssetConstants.BOOK_FILE.toString());
            availableBooks = new BookService().getAssets(AssetConstants.BOOK_FILE.toString());
            fillBooks = false;
        }
    }

    private void fillMovies() throws IOException, SAXException, ParserConfigurationException {
        if (fillMovies == true) {
            movies = new BookService().getAssets(AssetConstants.BOOK_FILE.toString());
            availableMovies = new MovieService().getAssets(AssetConstants.MOVIE_FILE.toString());
            fillMovies = false;
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
                System.out.printf(AssetConstants.CHOOSE_TYPE_ITEM.toString());
                input = getInputStream();
                printAsset(input);
                input = getInputStream();
                menu(input);
                break;

            case "2":
                response = exit();
                break;

            case "3":
                response = actBook(option);
                menu(AssetConstants.OPTION_ZERO.toString());
                break;

            case "4":
                response = actBook(option);
                menu(AssetConstants.OPTION_ZERO.toString());
                break;

            default:
                response = optionDefault();
                System.out.println(response);
                menu(AssetConstants.OPTION_ZERO.toString());
                break;
        }
        return response;
    }

    public void printMenu() {
        System.out.print(AssetConstants.CHOOSE_OPTIONS.toString()
                + AssetConstants.OPTION_ONE_FULL.toString()
                + AssetConstants.OPTION_TWO_FULL.toString()
                + AssetConstants.OPTION_THREE_FULL.toString()
                + AssetConstants.OPTION_FOUR_FULL);
    }

    public void printAsset(String typeAsset) throws ParserConfigurationException, SAXException, IOException {
        if (typeAsset.equals(AssetConstants.BOOK.toString())) {
            fillBooks();
            new BookService().showAssets(availableBooks);
        } else if (typeAsset.equals(AssetConstants.MOVIE.toString())) {
            fillMovies();
            new MovieService().showAssets(availableMovies);
        } else {
            System.out.printf(AssetConstants.SELECT_A_TYPE_BOOK.toString());
            menu(AssetConstants.OPTION_ONE.toString());
        }
    }

    public String exit() {
        return AssetConstants.OPTION_TWO.name();
    }

    public String actBook(String option) throws IOException {
        System.out.println(AssetConstants.ENTER_THE_BOOK_NAME.toString());
        String input = getInputStream();
        Book book = (Book) new BookService().isAssetInAssets(books, input);

        if (option.equals(AssetConstants.OPTION_THREE.toString())) {
            availableBooks = new BookService().checkoutAsset(availableBooks, book);
            return AssetConstants.OPTION_THREE.name();
        } else {
            availableBooks = new BookService().returnAsset(availableBooks, book);
            return AssetConstants.OPTION_FOUR.name();
        }
    }

    public String optionDefault() {
        return AssetConstants.SELECT_A_VALID_OPTION.toString();
    }
}
