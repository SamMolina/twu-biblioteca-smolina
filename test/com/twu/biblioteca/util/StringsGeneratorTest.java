package com.twu.biblioteca.util;

import com.twu.biblioteca.app.util.StringsGenerator;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StringsGeneratorTest {

    @Test
    public void shouldReturnARandomCharsWithADefineLength() {
        Random random = new Random();
        int length = random.nextInt(100);

        int expected = length;
        int actual = StringsGenerator.generateRandomChars(length).length();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotBeEqualsWhenCompareTwoRandomCharGenerated() {
        Random random = new Random();
        int length = random.nextInt(100);

        String randomStringOne = StringsGenerator.generateRandomChars(length);
        String randomStringTwo = StringsGenerator.generateRandomChars(length);

        assertNotEquals(randomStringOne, randomStringTwo);
    }
}
