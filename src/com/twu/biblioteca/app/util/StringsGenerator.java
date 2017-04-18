package com.twu.biblioteca.app.util;

import java.util.Random;

public class StringsGenerator {

    private static String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String generateRandomChars(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            builder.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }

        return builder.toString();
    }

}
