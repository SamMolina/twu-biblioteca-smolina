package com.twu.biblioteca.service;

import com.twu.biblioteca.app.impl.UserService;
import com.twu.biblioteca.app.impl.XMLFileParser;
import com.twu.biblioteca.app.util.BibliotecaConstants;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserServiceTest {

    @Test
    public void shouldReturnAValidListOfBooks() throws ParserConfigurationException, SAXException, IOException {
        List<Object> books = new XMLFileParser().parserFile(BibliotecaConstants.USER_FILE.toString(), BibliotecaConstants.USER.toString());

        assertNotEquals(0, books.size());
    }

    @Test
    public void shouldReturnTrueWhenLoginUserIsSuccessful() throws ParserConfigurationException, SAXException, IOException {
        boolean expectedLogin = true;
        boolean actualLogin = new UserService().loginUser("123-4567", "password");

        assertEquals(expectedLogin, actualLogin);
    }

    @Test
    public void shouldReturnFalseWhenLoginUserIsUnsuccessful() throws ParserConfigurationException, SAXException, IOException {
        boolean expectedLogin = false;
        boolean actualLogin = new UserService().loginUser("123-4567", "pasword");

        assertEquals(expectedLogin, actualLogin);
    }
}
