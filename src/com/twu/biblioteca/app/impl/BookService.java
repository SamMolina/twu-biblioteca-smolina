package com.twu.biblioteca.app.impl;

public class BookService {
    /*
    @Override
    public List<Object> getAssets(String fileName, String type) throws ParserConfigurationException, SAXException, IOException {
        List<Object> books = new XMLFileParser().parserFile(fileName, type);
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
        System.out.print(BibliotecaConstants.SHOW_BOOKS);
        System.out.print(new Book().formatAssetInformation(BibliotecaConstants.TITLE.name(), BibliotecaConstants.AUTHOR.name(), BibliotecaConstants.YEAR.name()));
        for (Object asset: assets) {
            Book book = (Book) asset;
            System.out.print(new Book().formatAssetInformation(book.getName(), book.getAuthor(), book.getYear()));
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
            if (book.getName().equals(assetToSearch)) return book;
        }
        return null;
    }

    @Override
    public List checkoutAsset(List assets, Object asset) {
        Book book = (Book) asset;
        if (new Book().isAValidAsset(book)) {
            if (book.getCheckout() == false) {
                updateCheckoutAsset(book, !book.getCheckout(), BibliotecaConstants.ENJOY_THE_BOOK.toString());
                assets.remove(book);
            } else {
                updateCheckoutAsset(book, book.getCheckout(), BibliotecaConstants.BOOK_NO_AVAILABLE.toString());
            }
        }
        return assets;
    }

    @Override
    public List<Object> returnAsset(List assets, Object asset) {
       Book book = (Book) asset;
        if (new Book().isAValidAsset(book)) {
            if (book.getCheckout() == true) {
                updateCheckoutAsset(book, !book.getCheckout(), BibliotecaConstants.THANK_YOU_FOR_RETURNING_THE_BOOK.toString());
                assets.add(asset);
            } else {
                updateCheckoutAsset(book, book.getCheckout(), BibliotecaConstants.INVALID_RETURN_BOOK.toString());
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
    */
}
