package com.twu.biblioteca.service;

import com.twu.biblioteca.app.impl.UserService;
import com.twu.biblioteca.app.impl.XMLFileParser;
import com.twu.biblioteca.app.model.User;
import com.twu.biblioteca.app.util.BibliotecaConstants;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class UserServiceTest {

    @Test
    public void shouldReturnAValidListOfBooks() throws ParserConfigurationException, SAXException, IOException {
        List<Object> books = new XMLFileParser().parserFile(BibliotecaConstants.USER_FILE.toString(), BibliotecaConstants.USER.toString());

        assertNotEquals(0, books.size());
    }

    @Test
    public void shouldReturnTheUserWhenLoginUserIsSuccessful() throws ParserConfigurationException, SAXException, IOException {
        User expectedUser = new User("Samantha", "smolina@thoughtworks.com", "0992922991",
                "123-4567", "password");
        User actualUser = new UserService().loginUser("123-4567", "password");

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void shouldReturnNullWhenLoginUserIsUnsuccessful() throws ParserConfigurationException, SAXException, IOException {
        User actualUser = new UserService().loginUser("123-4567", "pasword");

        assertNull(actualUser);
    }
}
