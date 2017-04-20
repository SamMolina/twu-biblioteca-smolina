package com.twu.biblioteca.app.model;

import com.twu.biblioteca.app.ui.Biblioteca;

public class Asset {
    private String type;
    private String file;
    private String name;
    private String author;
    private String year;
    private boolean checkout;

    public Asset() {

    }

    public Asset(String type, String file) {
        this.file = file;
        this.type = type;
    }

    public Asset(String title, String author, String year, boolean checkout, String type, String file) {
        this.name = title;
        this.author = author;
        this.year = year;
        this.checkout = checkout;
        this.type = type;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getCheckout() {
        return checkout;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }

    public boolean isAValidAsset(Asset asset) {
        if (asset == null) {
            System.out.print(successCheckout());
            return false;
        }
        return true;
    }

    public String showAsset() {
        return "The assets are below";
    }

    public String successCheckout() {
        return "Success Checkout!";
    }

    public String unsuccessfulCheckout() {
        return "Unsuccessful Checkout!";
    }

    public String noAvailableAsset() {
        return "Asset Not Available!";
    }

    public String successReturn() {
        return "Success Return";
    }

    public String unsuccessfulReturn() {
        return "Unsuccessful Return";
    }

    public String getType() {
        return type;
    }

    public String getFile() {
        return file;
    }

    @Override
    public String toString() {
        return name + "\t" + author + "\t" + year;
    }

    @Override
    public boolean equals(Object object) {
        Asset asset = (Asset) object;
        return asset.name.equals(name) && asset.author.equals(author) && asset.year.equals(year);
    }

    public String formatAssetInformation(String title, String author, String year) {
        return "|" + new Biblioteca().printWhiteSpaces(title, 30) +
                "|" + new Biblioteca().printWhiteSpaces(author, 30) +
                "|" + year + "|\n";
    }
}
