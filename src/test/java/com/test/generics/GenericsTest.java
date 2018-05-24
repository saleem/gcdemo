package com.test.generics;

import java.math.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.is;

@DisplayName("Test cases to demonstrate how generics work")
public class GenericsTest {
    @DisplayName("Verify that is-a relationship works as expected for number types")
    @Test public void testIsARelationship() {
        ComplexNumber complexNumber = new ComplexNumber(new BigDecimal("42"), BigDecimal.ZERO);
        RealNumber realNumber = new RealNumber(new BigDecimal("42"));
        RationalNumber rationalNumber = new RationalNumber(new BigInteger("420"), BigInteger.TEN);
        Integer integer = new Integer(new BigInteger("42"));

        // twelve assertions of equality; both directions for each pair of numbers
        assertThat(complexNumber, is(realNumber));
        assertThat(complexNumber, is(rationalNumber));
        assertThat(complexNumber, is(integer));

        assertThat(realNumber, is(rationalNumber));
        assertThat(realNumber, is(integer));

        assertThat(rationalNumber, is(integer));

        assertThat(realNumber, is(complexNumber));
        assertThat(rationalNumber, is(complexNumber));
        assertThat(integer, is(complexNumber));

        assertThat(rationalNumber, is(realNumber));
        assertThat(integer, is(realNumber));

        assertThat(integer, is(rationalNumber));
    }
}
