package com.twu.biblioteca.app.service;

import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.util.Menu;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookService implements Asset {
    @Override
    public List<Book> getAssets(String fileName) throws ParserConfigurationException, SAXException, IOException {
        List<Book> books = new XMLFileParser().parserFile(fileName);
        return books;
    }

    @Override
    public List<Book> getAvailableAssets(List assets) {
        List<Book> availableBooks = new ArrayList<>();
        for (Object asset: assets) {
            Book book = (Book) asset;
            if (book.getCheckout() == false)
                availableBooks.add(book);
        }
        return availableBooks;
    }

    @Override
    public void showAssets(List assets) {
        System.out.print(Menu.SHOW_BOOKS);
        System.out.print(new Book().formatBookInformation(Menu.TITLE.name(), Menu.AUTHOR.name(), Menu.YEAR.name()));
        for (Object asset: assets) {
            Book book = (Book) asset;
            System.out.print(new Book().formatBookInformation(book.getTitle(), book.getAuthor(), book.getYear()));
        }
    }

    @Override
    public boolean isAssetInAssets(List assets, Object assetToSearch) {
        for (Object asset: assets) {
            Book book = (Book) asset;
            if (book.equals((Book) assetToSearch)) return true;
        }
        return false;
    }

    @Override
    public Object isAssetInAssets(List assets, String assetToSearch) {
        for (Object asset: assets) {
            Book book = (Book) asset;
            if (book.getTitle().equals(assetToSearch)) return book;
        }
        return null;
    }

    @Override
    public Object checkoutAsset(Object asset) {
        Book book = (Book) asset;
        if (new Book().isAValidBook(book)) {
            if (book.getCheckout() == false) {
                updateCheckoutAsset(book, !book.getCheckout(), com.twu.biblioteca.app.util.Menu.ENJOY_THE_BOOK.toString());
            } else {
                updateCheckoutAsset(book, book.getCheckout(), com.twu.biblioteca.app.util.Menu.BOOK_NO_AVAILABLE.toString());
            }
        }
        return book;
    }

    @Override
    public Object returnAsset(Object asset) {
       Book book = (Book) asset;
        if (new Book().isAValidBook(book)) {
            if (book.getCheckout() == true) {
                updateCheckoutAsset(book, !book.getCheckout(), Menu.THANK_YOU_FOR_RETURNING.toString());
            } else {
                updateCheckoutAsset(book, book.getCheckout(), Menu.INVALID_RETURN.toString());
            }
        }
        return book;
    }

    @Override
    public void updateCheckoutAsset(Object asset, boolean checkout, String message) {
        Book book = (Book) asset;
        book.setCheckout(checkout);
        System.out.print(message);
    }

    @Override
    public List checkoutAsset(List assets, Object assetToCheckout) {
        assets.remove(assetToCheckout);
        return assets;
    }

    @Override
    public List returnAsset(List assets, Object assetToReturn) {
        assets.add(assetToReturn);
        return assets;
    }
}
