package com.twu.biblioteca.vo;

import com.twu.biblioteca.app.Menu;
import com.twu.biblioteca.util.DOMParser;
import com.twu.biblioteca.util.EMenu;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

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

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }

    public List<Book> getBooks(String fileName) throws ParserConfigurationException, SAXException, IOException {
        List<Book> books = new DOMParser().parserFile(fileName);
        return books;
    }

    public List<Book> getAvailableBooks(List<Book> books) {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book: books) {
            if (book.getCheckout() == false)
                availableBooks.add(book);
        }
        return availableBooks;
    }

    public void showBooks(List<Book> books) {
        System.out.print(EMenu.SHOW_BOOKS);
        System.out.print(formatBookInformation(EMenu.TITLE.name(), EMenu.AUTHOR.name(),EMenu.YEAR.name()));
        for (Book book: books) {
            System.out.print(formatBookInformation(book.getTitle(), book.getAuthor(), book.getYear()));
        }
    }

    public boolean isBookInBooks(List<Book> books, Book bookToSearch) {
        for (Book book: books) {
            if (book.equals(bookToSearch)) return true;
        }
        return false;
    }

    public Book isBookInBooks(List<Book> books, String bookToSearch) {
        for (Book book: books) {
            if (book.getTitle().equals(bookToSearch)) return book;
        }
        return null;
    }

    public boolean isAValidBook(Book book) {
        if (book == null) {
            System.out.print(EMenu.BOOK_NOT_EXISTS.toString());
            return false;
        }
        return true;
    }

    public void changeCheckoutBook (Book book, boolean checkout, String message) {
        book.setCheckout(checkout);
        System.out.print(message);
    }

    public Book checkoutBook(Book book) throws ParserConfigurationException, SAXException, IOException {
        if (isAValidBook(book)) {
            if (book.getCheckout() == false) {
                changeCheckoutBook(book, !book.getCheckout(), EMenu.ENJOY_THE_BOOK.toString());
            } else {
                changeCheckoutBook(book, book.getCheckout(), EMenu.BOOK_NO_AVAILABLE.toString());
            }
        }
        return book;
    }

    public Book returnBook(Book book) {
        if (isAValidBook(book)) {
            if (book.getCheckout() == true) {
                changeCheckoutBook(book, !book.getCheckout(), EMenu.THANK_YOU_FOR_RETURNING.toString());
            } else {
                changeCheckoutBook(book, book.getCheckout(), EMenu.INVALID_RETURN.toString());
            }
        }
        return book;
    }

    public List<Book> checkoutBook(List<Book> books, Book book) {
        books.remove(book);
        return books;
    }

    public List<Book> returnBook(List<Book> books, Book book) {
        books.add(book);
        return books;
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

    public String formatBookInformation(String title, String author, String year) {
        return "|" + new Menu().printWhiteSpaces(title, 30) +
                "|" + new Menu().printWhiteSpaces(author, 30) +
                "|" + year + "|\n";
    }
}
