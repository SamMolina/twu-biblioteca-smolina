package com.twu.biblioteca.app.model;

import com.twu.biblioteca.app.util.BibliotecaConstants;

public class Book extends Asset{

    public Book() {
        super(BibliotecaConstants.BOOK.toString(), BibliotecaConstants.BOOK_FILE.toString());
    }

    public Book(String title, String author, String year, boolean checkout) {
        super(title, author, year, checkout, BibliotecaConstants.BOOK.toString(), BibliotecaConstants.BOOK_FILE.toString());
    }

    public String showAsset() {
        return BibliotecaConstants.SHOW_BOOKS.toString();
    }

    public String unsuccessfulCheckout() {
        return BibliotecaConstants.ENJOY_THE_BOOK.toString();
    }

    public String noAvailableAsset() {
        return BibliotecaConstants.BOOK_NO_AVAILABLE.toString();
    }

    public String successCheckout() {
        return BibliotecaConstants.BOOK_NOT_EXISTS.toString();
    }

    public String successReturn() {
        return BibliotecaConstants.THANK_YOU_FOR_RETURNING_THE_BOOK.toString();
    }

    public String unsuccessfulReturn() {
        return BibliotecaConstants.INVALID_RETURN_BOOK.toString();
    }
}
