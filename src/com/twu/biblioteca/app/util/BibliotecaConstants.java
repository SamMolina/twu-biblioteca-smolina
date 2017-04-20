package com.twu.biblioteca.app.util;

public enum BibliotecaConstants {
    BOOK("book"),
    BOOK_FILE("books.xml"),
    BOOK_NO_AVAILABLE("That book is not available.\n"),
    BOOK_NOT_EXISTS("That book doesn't exists!\n"),

    TITLE("title"),
    AUTHOR("author"),
    YEAR("year"),

    NAME("name"),
    DIRECTOR("director"),
    RATING("rating"),

    OPTION_ZERO("0"),
    OPTION_ONE("1"),
    OPTION_TWO("2"),
    OPTION_THREE("3"),
    OPTION_FOUR("4"),

    OPTION_ONE_FULL("1. Show List Items\n"),
    OPTION_TWO_FULL("2. Quit\n"),
    OPTION_THREE_FULL("3. Checkout item\n"),
    OPTION_FOUR_FULL("4. Return item\n"),

    BLANK_SPACE(" "),

    WELCOME_MESSAGE("==== Welcome to the TWU Biblioteca App ===\n"),
    CHOOSE_OPTIONS("Choose one of the following options:\n"),
    SHOW_BOOKS("The books list is showing below:\n"),
    SHOW_MOVIES("The movie list is showing below:\n"),
    SELECT_A_VALID_OPTION("Select a valid option!\n"),

    ENJOY_THE_BOOK("Thank you! Enjoy the book\n"),
    ENTER_THE_ASSET_NAME("Enter the item name:"),

    THANK_YOU_FOR_RETURNING_THE_BOOK("Thank you for returning the book.\n"),
    THANK_YOU_FOR_RETURNING_THE_MOVIE("Thank you for returning the movie.\n"),
    INVALID_RETURN_BOOK("That is not a valid book to return.\n"),
    INVALID_RETURN_MOVIE("That is not a valid movie to return.\n"),

    MOVIE_FILE("movies.xml"),
    MOVIE("movie"),
    CHOOSE_TYPE_ITEM("Do you want 'book' list or 'movie' list?\n"),
    SELECT_A_TYPE_BOOK("Enter 'book' for book list or 'movie' for movie list\n"),
    ENJOY_THE_MOVIE("Thank you! Enjoy the movie\n"),
    MOVIE_NOT_EXISTS("That movie doesn't exists!\n"),
    MOVIE_NO_AVAILABLE("That movie is not available.\n");

    private final String name;

    BibliotecaConstants(String word) {
        this.name = word;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
