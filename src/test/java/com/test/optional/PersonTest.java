package com.test.optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.*;

public class PersonTest {
    private final Person person = new Person("John Doe", "1978-10-26");

    @DisplayName("Verify that year of birth is correctly returned")
    @Test
    public void testYearOfBirth() {
        assertThat(person.getYearOfBirth(), is(1978));
    }

    @DisplayName("Verify that century of birth is correctly returned")
    @Test
    public void testCenturyOfBirth() {
        assertThat(person.getCenturyOfBirth(), is(1900));
    }
}
