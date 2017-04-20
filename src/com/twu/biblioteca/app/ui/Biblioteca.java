package com.twu.biblioteca.app.ui;

import com.twu.biblioteca.app.impl.AssetService;
import com.twu.biblioteca.app.model.Asset;
import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.model.Movie;
import com.twu.biblioteca.app.util.BibliotecaConstants;
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
            word += BibliotecaConstants.BLANK_SPACE.toString();
        }
        return word;
    }

    public String getInputStream() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public int start() throws ParserConfigurationException, SAXException, IOException {
        String option = BibliotecaConstants.OPTION_ZERO.toString();
        menu(option, null);
        return 0;
    }

    private void fillBooks() throws IOException, SAXException, ParserConfigurationException {
        if (fillBooks == true) {
            books = new AssetService().getAssets(new Book().getFile(), new Book().getType());
            availableBooks = new AssetService().getAssets(new Book().getFile(), new Book().getType());
            fillBooks = false;
        }
    }

    private void fillMovies() throws IOException, SAXException, ParserConfigurationException {
        if (fillMovies == true) {
            movies = new AssetService().getAssets(new Movie().getFile(), new Movie().getType());
            availableMovies = new AssetService().getAssets(new Movie().getFile(), new Movie().getType());
            fillMovies = false;
        }
    }

    public String menu(String option, String type) throws IOException, SAXException, ParserConfigurationException {
        String input, response = "";
        switch (option) {
            case "0":
                printMenu();
                input = getInputStream();
                menu(input, null);
                break;

            case "1":
                System.out.printf(BibliotecaConstants.CHOOSE_TYPE_ITEM.toString());
                type = getInputStream();
                printAsset(type);
                input = getInputStream();
                menu(input, type);
                break;

            case "2":
                response = exit();
                break;

            case "3":
                if (type.equals(BibliotecaConstants.BOOK.toString())) {
                    response = actAsset(option, books, availableBooks);
                } else if (type.equals(BibliotecaConstants.MOVIE.toString())) {
                    response = actAsset(option, movies, availableMovies);
                }
                menu(BibliotecaConstants.OPTION_ZERO.toString(), type);
                break;

            case "4":
                if (type.equals(BibliotecaConstants.BOOK.toString())) {
                    response = actAsset(option, books, availableBooks);
                } else if (type.equals(BibliotecaConstants.MOVIE.toString())) {
                    response = actAsset(option, movies, availableMovies);
                }
                menu(BibliotecaConstants.OPTION_ZERO.toString(), type);
                break;

            default:
                response = optionDefault();
                System.out.println(response);
                menu(BibliotecaConstants.OPTION_ZERO.toString(), type);
                break;
        }
        return response;
    }

    public void printMenu() {
        System.out.print(BibliotecaConstants.CHOOSE_OPTIONS.toString()
                + BibliotecaConstants.OPTION_ONE_FULL.toString()
                + BibliotecaConstants.OPTION_TWO_FULL.toString()
                + BibliotecaConstants.OPTION_THREE_FULL.toString()
                + BibliotecaConstants.OPTION_FOUR_FULL);
    }

    public void printAsset(String typeAsset) throws ParserConfigurationException, SAXException, IOException {
        if (typeAsset.equals(BibliotecaConstants.BOOK.toString())) {
            fillBooks();
            new AssetService().showAssets(availableBooks, typeAsset);
        } else if (typeAsset.equals(BibliotecaConstants.MOVIE.toString())) {
            fillMovies();
            new AssetService().showAssets(availableMovies, typeAsset);
        } else {
            System.out.printf(BibliotecaConstants.SELECT_A_TYPE_BOOK.toString());
            menu(BibliotecaConstants.OPTION_ONE.toString(), null);
        }
    }

    public String exit() {
        return BibliotecaConstants.OPTION_TWO.name();
    }

    public String actAsset(String option, List assets, List availableAssets) throws IOException {
        System.out.println(BibliotecaConstants.ENTER_THE_ASSET_NAME.toString());
        String input = getInputStream();
        Asset asset = (Asset) new AssetService().isAssetInAssets(assets, input);

        if (option.equals(BibliotecaConstants.OPTION_THREE.toString())) {
            availableAssets = new AssetService().checkoutAsset(availableAssets, asset);
            return BibliotecaConstants.OPTION_THREE.name();
        } else {
            availableAssets = new AssetService().returnAsset(availableAssets, asset);
            return BibliotecaConstants.OPTION_FOUR.name();
        }
    }

    public String optionDefault() {
        return BibliotecaConstants.SELECT_A_VALID_OPTION.toString();
    }
}
