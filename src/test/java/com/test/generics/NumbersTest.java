package com.test.generics;

import java.math.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.is;

@DisplayName("Test cases for various \"Number\" classes")
public class NumbersTest {
    @DisplayName("Tests for our domain-specific Integer class")
    @Test public void testInteger() {
        assertThat(new Integer(BigInteger.ZERO).toString(), is("ComplexNumber{realPart=0, imaginaryPart=0}"));
        assertThat(new Integer(new BigInteger("42")).toString(), is("ComplexNumber{realPart=42, imaginaryPart=0}"));
        assertThat(new Integer(new BigInteger("1234567890987654321")).toString(), is("ComplexNumber{realPart=1234567890987654321, imaginaryPart=0}"));
        assertThat(new Integer(new BigInteger("-1234567890987654321")).toString(), is("ComplexNumber{realPart=-1234567890987654321, imaginaryPart=0}"));
    }

    @DisplayName("Tests for our domain-specific RationalNumber class")
    @Test public void testRationalNumber() {
        assertThat(new RationalNumber(BigInteger.ZERO, BigInteger.ONE).toString(), is("ComplexNumber{realPart=0, imaginaryPart=0}"));
        assertThat(new RationalNumber(BigInteger.ONE, new BigInteger("42"), 20).toString(), is("ComplexNumber{realPart=0.02380952380952380952, imaginaryPart=0}"));
        assertThat(new RationalNumber(BigInteger.ONE, new BigInteger("-42"), 20).toString(), is("ComplexNumber{realPart=-0.02380952380952380952, imaginaryPart=0}"));
    }
}
