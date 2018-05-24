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

    @DisplayName("Tests for our domain-specific RealNumber class")
    @Test public void testRealNumber() {
        final String pi_1000 = "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326117931051185480744623799627495673518857527248912279381830119491298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051320005681271452635608277857713427577896091736371787214684409012249534301465495853710507922796892589235420199561121290219608640344181598136297747713099605187072113499999983729780499510597317328160963185950244594553469083026425223082533446850352619311881710100031378387528865875332083814206171776691473035982534904287554687311595628638823537875937519577818577805321712268066130019278766111959092164201989";
        assertThat(new RealNumber(new BigDecimal(pi_1000)).toString(), is("ComplexNumber{realPart=" + pi_1000 + ", imaginaryPart=0}"));
    }

    @DisplayName("Tests for our domain-specific ComplexNumber class")
    @Test public void testComplexNumber() {
        assertThat(new ComplexNumber(BigDecimal.TEN, BigDecimal.ONE).toString(), is("ComplexNumber{realPart=10, imaginaryPart=1}"));
    }

    @DisplayName("Tests for equality of the values of various numbers, regardless of the type / class of Number")
    @Test void testEquality() {
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
