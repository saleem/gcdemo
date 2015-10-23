package com.test;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringEqualityTest {

    @Test
    public void stringLiteralsAreComparableUsingDoubleEquals() {
        String one = "Hello World!";
        String two = "Hello World!";
        assertTrue(one == two);
        assertTrue(one.equals(two));
        assertTrue(two.equals(one));
    }

    @Test
    public void deliberatlyCreatedStringsAreComparableUsingEqualsMethodOnly() {
        String one = new String("Hello World!");
        String two = new String("Hello World!");
        assertFalse(one == two);
        assertTrue(one.equals(two));
        assertTrue(two.equals(one));
    }

    @Test
    public void stringsReadFromFileAreComparableUsingEqualsMethodOnly() throws Exception {
        String expectedString = "Hello World!";
        for (String actualString : readStringsFromFile()) {
            assertFalse(actualString == expectedString);
            assertTrue(actualString.equals(expectedString));
            assertTrue(expectedString.equals(actualString));
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
