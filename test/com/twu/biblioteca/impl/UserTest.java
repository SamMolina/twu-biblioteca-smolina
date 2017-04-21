package com.twu.biblioteca.impl;

import com.twu.biblioteca.app.model.User;
import com.twu.biblioteca.app.util.StringsGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserTest {

    @Test
    public void shouldReturnTheSameUserObjectWhenCreateAnUserObjectWithTheSameFields() {
        String name = StringsGenerator.generateRandomChars(5);
        String email = StringsGenerator.generateRandomChars(5);
        String phoneNumber = StringsGenerator.generateRandomChars(5);
        String libraryNumber = StringsGenerator.generateRandomChars(8);
        String password = StringsGenerator.generateRandomChars(10);

        User userExpected = new User(name, email, phoneNumber, libraryNumber, password);
        User userActual = new User(name, email, phoneNumber, libraryNumber, password);

        assertEquals(userExpected, userActual);
    }

    @Test
    public void shouldNotReturnTheSameUserObjectWhenCreateAnUserObjectWithTheDifferentFields() {
        String nameOne = StringsGenerator.generateRandomChars(5);
        String emailOne = StringsGenerator.generateRandomChars(5);
        String phoneNumberOne = StringsGenerator.generateRandomChars(5);
        String libraryNumberOne = StringsGenerator.generateRandomChars(8);
        String passwordOne = StringsGenerator.generateRandomChars(10);

        String nameTwo = StringsGenerator.generateRandomChars(5);
        String emailTwo = StringsGenerator.generateRandomChars(5);
        String phoneNumberTwo = StringsGenerator.generateRandomChars(5);
        String libraryNumberTwo = StringsGenerator.generateRandomChars(8);
        String passwordTwo = StringsGenerator.generateRandomChars(10);

        User userExpected = new User(nameOne, emailOne, phoneNumberOne, libraryNumberOne, passwordOne);
        User userActual = new User(nameTwo, emailTwo, phoneNumberTwo, libraryNumberTwo, passwordTwo);

        assertNotEquals(userExpected, userActual);
    }
}
