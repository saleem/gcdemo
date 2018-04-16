package com.test.optional;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.hamcrest.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright Sonic Drive-In ® 2018.
 */
class RolodexTest {

    private final Rolodex rolodex = new Rolodex();

    @DisplayName("Verify that real people are found, imaginary ones aren't")
    @Test
    public void findByName() {
        assertTrue(rolodex.findByName("Einstein").isPresent());
        assertFalse(rolodex.findByName("Gandalf").isPresent());
    }

    @DisplayName("Verify searching for people by the century of their birth")
    @Test
    public void findByCenturyOfBirth() {
        List<Person> bornIn1800s = rolodex.findByCenturyOfBirth(1800);
        List<Person> bornIn1900s = rolodex.findByCenturyOfBirth(1900);
        assertThat(bornIn1800s, containsByName("Einstein"));
        assertThat(bornIn1800s, containsByName("Meitner"));
        assertThat(bornIn1900s, containsByName("Adele"));
        assertThat(bornIn1900s, containsByName("Elvis"));
    }

    @DisplayName("Verify that when searching for people by the century of their birth, the year given must end in two zeros")
    @Test
    public void findByCenturyOfBirthWithInvalidCenturyYear() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> rolodex.findByCenturyOfBirth(1801));
        assertEquals("First year in century must end in 00, e.g. 1800, 1900", exception.getMessage());
    }

    private PersonNameMatcher containsByName(String actualName) {
        return new PersonNameMatcher(actualName);
    }
}

class PersonNameMatcher extends TypeSafeMatcher<List<Person>> {

    private final String actualName;

    PersonNameMatcher(String actualName) {
        this.actualName = actualName;
    }

    @Override
    protected boolean matchesSafely(List<Person> people) {
        for (Person p : people) {
            if (p.getName().equals(actualName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("should contain person with name").appendText(actualName);
    }
}