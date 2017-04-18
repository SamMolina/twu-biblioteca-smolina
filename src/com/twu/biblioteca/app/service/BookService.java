package com.twu.biblioteca.app.service;

import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.util.Menu;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookService implements Asset {

    public List<Book> getBooks(String fileName) throws ParserConfigurationException, SAXException, IOException {
        List<Book> books = new XMLFileParser().parserFile(fileName);
        return books;
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

    public List<Book> getAvailableBooks(List<Book> books) {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book: books) {
            if (book.getCheckout() == false)
                availableBooks.add(book);
        }
        return availableBooks;
    }

    public void showBooks(List<Book> books) {
        System.out.print(Menu.SHOW_BOOKS);
        System.out.print(new Book().formatBookInformation(Menu.TITLE.name(), Menu.AUTHOR.name(), Menu.YEAR.name()));
        for (Book book: books) {
            System.out.print(new Book().formatBookInformation(book.getTitle(), book.getAuthor(), book.getYear()));
        }
    }

    public Book checkoutBook(Book book) {
        if (new Book().isAValidBook(book)) {
            if (book.getCheckout() == false) {
                updateCheckoutBook(book, !book.getCheckout(), com.twu.biblioteca.app.util.Menu.ENJOY_THE_BOOK.toString());
            } else {
                updateCheckoutBook(book, book.getCheckout(), com.twu.biblioteca.app.util.Menu.BOOK_NO_AVAILABLE.toString());
            }
        }
        return book;
    }

    public Book returnBook(Book book) {
        if (new Book().isAValidBook(book)) {
            if (book.getCheckout() == true) {
                updateCheckoutBook(book, !book.getCheckout(), Menu.THANK_YOU_FOR_RETURNING.toString());
            } else {
                updateCheckoutBook(book, book.getCheckout(), Menu.INVALID_RETURN.toString());
            }
        }
        return book;
    }

    public void updateCheckoutBook(Book book, boolean checkout, String message) {
        book.setCheckout(checkout);
        System.out.print(message);
    }

    public List<Book> checkoutBook(List<Book> books, Book book) {
        books.remove(book);
        return books;
    }

    public List<Book> returnBook(List<Book> books, Book book) {
        books.add(book);
        return books;
    }
}
