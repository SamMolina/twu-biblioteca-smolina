package com.twu.biblioteca.util;

public enum EMenu {
    BOOK("book"),
    BOOK_FILE("books.xml"),
    TITLE("title"),
    AUTHOR("author"),
    YEAR("year"),

    OPTION_ZERO("0"),
    OPTION_ONE("1"),
    OPTION_TWO("2"),
    OPTION_THREE("3"),
    OPTION_FOUR("4"),

    OPTION_ONE_FULL("1. Show List Books\n"),
    OPTION_TWO_FULL("2. Quit\n"),
    OPTION_THREE_FULL("3. Checkout book\n"),
    OPTION_FOUR_FULL("4. Return book\n"),

    WELCOME_MESSAGE("==== Welcome to the TWU Biblioteca App ===\n"),
    CHOOSE_OPTIONS("Choose one of the following options:\n"),
    SHOW_BOOKS("The books list is showing below:\n"),
    SELECT_A_VALID_OPTION("Select a valid option!\n"),
    ENJOY_THE_BOOK("Thank you! Enjoy the book\n"),
    BOOK_NO_AVAILABLE("That book is not available.\n"),
    BOOK_NOT_EXISTS("That Book doesn't exists!\n"),
    QUIT("Quit");

    private final String name;

    EMenu(String word) {
        this.name = word;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
