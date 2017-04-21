package com.twu.biblioteca.app.impl;

import com.twu.biblioteca.app.model.User;
import com.twu.biblioteca.app.util.BibliotecaConstants;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class UserService {
    public static List<Object> getCustomers() throws ParserConfigurationException, SAXException, IOException {
        List<Object> users = new XMLFileParser().parserFile(BibliotecaConstants.USER_FILE.toString(), BibliotecaConstants.USER.toString());
        return users;
    }

    public User loginUser(String libraryNumber, String password) throws IOException, SAXException, ParserConfigurationException {
        List<Object> objectUsers = UserService.getCustomers();

        for (Object object: objectUsers) {
            User user = (User) object;
            if (user.getLibraryNumber().equals(libraryNumber) && user.getPassword().equals(password))
                return user;

        }
        return null;
    }
}
