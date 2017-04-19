package com.twu.biblioteca.app.model;

import com.twu.biblioteca.app.ui.Biblioteca;
import com.twu.biblioteca.app.util.AssetConstants;

public class Book {
    private String title;
    private String author;
    private String year;
    private boolean checkout;

    public Book(String title, String author, String year, boolean checkout) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.checkout = checkout;
    }

    public Book() {
        this.checkout = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public boolean getCheckout() {
        return checkout;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }

    public boolean isAValidBook(Book book) {
        if (book == null) {
            System.out.print(AssetConstants.BOOK_NOT_EXISTS.toString());
            return false;
        }
        return true;
    }

    public String formatBookInformation(String title, String author, String year) {
        return "|" + new Biblioteca().printWhiteSpaces(title, 30) +
                "|" + new Biblioteca().printWhiteSpaces(author, 30) +
                "|" + year + "|\n";
    }

    @Override
    public String toString() {
        return title + "\t" + author + "\t" + year;
    }

    @Override
    public boolean equals(Object object) {
        Book book = (Book) object;
        return book.title.equals(title) && book.author.equals(author) && book.year.equals(year);
    }
}
