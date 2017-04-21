package com.twu.biblioteca.app.model;

import javax.rmi.CORBA.StubDelegate;

public class User {

    private String libraryNumber;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;

    public User(String name, String email, String phoneNumber, String libraryNumber, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public User() {

    }

    public String showUserInformation() {
        return "Name: Samantha\nEmail: smolina@thoughtworks.com\nPhone Number: 0992922991";
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return name.equals(user.getName()) && email.equals(user.getEmail()) && phoneNumber.equals(user.getPhoneNumber())
                && libraryNumber.equals(user.getLibraryNumber()) && password.equals(user.getPassword());
    }
}
