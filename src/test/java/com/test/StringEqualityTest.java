package com.test;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringEqualityTest {

    @Test
    public void stringLiteralsAreComparableUsingEqualsMethodAndEqualsOperator() {
        String one = "Hello World!";
        String two = "Hello World!";
        assertTrue(one == two);
        assertTrue(one.equals(two));
        assertTrue(two.equals(one));
    }

    @Test
    public void deliberatlyCreatedStringsAreComparableUsingEqualsMethodButNotEqualsOperator() {
        String one = new String("Hello World!");
        String two = new String("Hello World!");
        assertFalse(one == two);
        assertTrue(one.equals(two));
        assertTrue(two.equals(one));
    }

    @Test
    public void stringsReadFromFileAreComparableUsingEqualsMethodButNotEqualsOperator() throws Exception {
        String expectedString = "Hello World!";
        for (String actualString : readStringsFromFile()) {
            assertFalse(actualString == expectedString);
            assertTrue(actualString.equals(expectedString));
            assertTrue(expectedString.equals(actualString));
        }
    }

    @Test
    public void internedStringsReadFromFileAreComparableUsingEqualsOperator() throws Exception {
        String expectedString = "Hello World!";
        for (String actualString : readStringsFromFile()) {
            assertTrue(actualString.intern() == expectedString.intern());
        }
    }

    private List<String> readStringsFromFile() throws Exception {
        List<String> strings = new ArrayList<>();
        DataInputStream dataInputStream = new DataInputStream(StringEqualityTest.class.getResourceAsStream("/hello.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(dataInputStream));
        String line;
        while ((line = br.readLine()) != null) {
            strings.add(line);
        }
        dataInputStream.close();
        return strings;
    }
}
