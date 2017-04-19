package com.twu.biblioteca.app.service;

import com.twu.biblioteca.app.model.Book;
import com.twu.biblioteca.app.util.Asset;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookService implements com.twu.biblioteca.app.service.Asset {
    @Override
    public List<Object> getAssets(String fileName) throws ParserConfigurationException, SAXException, IOException {
        List<Object> books = new XMLFileParser().parserFile(fileName, Asset.BOOK.toString());
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
        System.out.print(Asset.SHOW_BOOKS);
        System.out.print(new Book().formatBookInformation(Asset.TITLE.name(), Asset.AUTHOR.name(), Asset.YEAR.name()));
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
    public List checkoutAsset(List assets, Object asset) {
        Book book = (Book) asset;
        if (new Book().isAValidBook(book)) {
            if (book.getCheckout() == false) {
                updateCheckoutAsset(book, !book.getCheckout(), Asset.ENJOY_THE_BOOK.toString());
                assets.remove(book);
            } else {
                updateCheckoutAsset(book, book.getCheckout(), Asset.BOOK_NO_AVAILABLE.toString());
            }
        }
        return assets;
    }

    @Override
    public List<Object> returnAsset(List assets, Object asset) {
       Book book = (Book) asset;
        if (new Book().isAValidBook(book)) {
            if (book.getCheckout() == true) {
                updateCheckoutAsset(book, !book.getCheckout(), Asset.THANK_YOU_FOR_RETURNING.toString());
                assets.add(asset);
            } else {
                updateCheckoutAsset(book, book.getCheckout(), Asset.INVALID_RETURN.toString());
            }
        }
        return assets;
    }

    @Override
    public void updateCheckoutAsset(Object asset, boolean checkout, String message) {
        Book book = (Book) asset;
        book.setCheckout(checkout);
        System.out.print(message);
    }
}
