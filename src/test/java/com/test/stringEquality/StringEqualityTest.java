package com.test.stringEquality;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DisplayName("Various tests to demonstrate when Java Strings are interned, and when they're not")
public class StringEqualityTest {

    @DisplayName("Verify that two string literals can be compared by using both the == operator and equals() method")
    @Test public void stringLiteralsAreComparableUsingEqualsMethodAndEqualsOperator() {
        String one = "Hello World!";
        String two = "Hello World!";
        assertTrue(one == two);
        assertTrue(one.equals(two));
        assertTrue(two.equals(one));
    }

    @DisplayName("Verify that two unique string objects can only be compared by using the equals() method")
    @Test public void deliberatelyCreatedStringsAreComparableUsingEqualsMethodButNotEqualsOperator() {
        String one = new String("Hello World!");
        String two = new String("Hello World!");
        assertFalse(one == two);
        assertTrue(one.equals(two));
        assertTrue(two.equals(one));
    }

    @DisplayName("Verify that a string literal and a string read from a file can only be compared by using the equals() method")
    @Test public void stringsReadFromFileAreComparableUsingEqualsMethodButNotEqualsOperator() throws Exception {
        String expectedString = "Hello World!";
        for (String actualString : readStringsFromFile()) {
            assertFalse(actualString == expectedString);
            assertTrue(actualString.equals(expectedString));
            assertTrue(expectedString.equals(actualString));
        }
    }

    @DisplayName("Verify that interned strings objects can be compared by using both the == operator")
    @Test public void internedStringsReadFromFileAreComparableUsingEqualsOperator() throws Exception {
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
