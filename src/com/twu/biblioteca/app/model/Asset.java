package com.twu.biblioteca.app.model;

public class Asset {
    private String title;
    private String author;
    private String year;
    private boolean checkout;

    Asset(String title, String author, String year, boolean checkout) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.checkout = checkout;
    }
}
