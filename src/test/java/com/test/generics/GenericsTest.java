package com.test.generics;

import java.math.*;
import java.util.Optional;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.*;

@DisplayName("Test cases to demonstrate how generics work")
public class GenericsTest {
    /**
     * This test illustrates that with ordinary classes, we may simply assign an
     * object of type <code>Integer</code> to a variable of type <code>RealNumber</code>.
     * This is an illustration of polymorphism: because any Integer "is-a" ComplexNumber, this assignment works
     */
    @DisplayName("Verify that is-a relationship works as expected for number types")
    @Test
    public void testIsARelationshipWithSimpleObjects() {
        ComplexNumber complexNumber = createComplexNumber();
        ComplexNumber realNumber = createRealNumber();
        ComplexNumber rationalNumber = createRationalNumber();
        ComplexNumber integer = createInteger();

        assertThat(complexNumber, instanceOf(ComplexNumber.class));
        assertThat(realNumber, instanceOf(ComplexNumber.class));
        assertThat(rationalNumber, instanceOf(ComplexNumber.class));
        assertThat(integer, instanceOf(ComplexNumber.class));

        assertThat(realNumber, instanceOf(RealNumber.class));
        assertThat(rationalNumber, instanceOf(RealNumber.class));
        assertThat(integer, instanceOf(RealNumber.class));

        assertThat(rationalNumber, instanceOf(RationalNumber.class));
        assertThat(integer, instanceOf(RationalNumber.class));

        assertThat(integer, instanceOf(Integer.class));
    }

    /**
     * This test illustrates that when using Optional (which uses generics), we may not simply assign an
     * object of type <code>Optional<Integer></code> to a variable of type <code>Optional<RealNumber></code>.
     * That is, the following line does not compile:
     * <code>Optional<ComplexNumber> complexNumber = createOptionalInteger();</code>
     * This is how inheritance applies to Generic types.
     * See https://docs.oracle.com/javase/tutorial/java/generics/inheritance.html
     * See https://docs.oracle.com/javase/tutorial/java/generics/subtyping.html
     */
    @DisplayName("Show that is-a relationship is non-trivial when using generics")
    @Test public void testIsARelationshipWithOptionalObjects() {
        Optional<? extends ComplexNumber> complexNumber = createOptionalComplexNumber();
        Optional<? extends ComplexNumber> realNumber = createOptionalRealNumber();
        Optional<? extends ComplexNumber> rationalNumber = createOptionalRationalNumber();
        Optional<? extends ComplexNumber> integer = createOptionalInteger();

        assertThat(realNumber, instanceOf(complexNumber.getClass()));
        assertThat(rationalNumber, instanceOf(complexNumber.getClass()));
        assertThat(integer, instanceOf(complexNumber.getClass()));
    }

    private ComplexNumber createComplexNumber() {
        return new ComplexNumber(BigDecimal.TEN, BigDecimal.ONE);
    }

    private RealNumber createRealNumber() {
        return new RealNumber(new BigDecimal("42.123456789"));
    }

    private RationalNumber createRationalNumber() {
        return new RationalNumber(new BigInteger("420"), BigInteger.TEN);
    }

    private Integer createInteger() {
        return new Integer(new BigInteger("4200"));
    }

    private Optional<ComplexNumber> createOptionalComplexNumber() {
        return Optional.of(createComplexNumber());
    }

    private Optional<RealNumber> createOptionalRealNumber() {
        return Optional.of(createRealNumber());
    }


    private Optional<RationalNumber> createOptionalRationalNumber() {
        return Optional.of(createRationalNumber());
    }

    private Optional<Integer> createOptionalInteger() {
        return Optional.of(createInteger());
    }

}
