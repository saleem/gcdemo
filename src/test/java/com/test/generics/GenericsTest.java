package com.test.generics;

import java.math.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.*;

@DisplayName("Test cases to demonstrate how generics work")
public class GenericsTest {
    @DisplayName("Verify that is-a relationship works as expected for number types")
    @Test
    public void testIsARelationship() {
        ComplexNumber complexNumber = new ComplexNumber(BigDecimal.TEN, BigDecimal.ONE);
        RealNumber realNumber = new RealNumber(new BigDecimal("42.123456789"));
        RationalNumber rationalNumber = new RationalNumber(new BigInteger("420"), BigInteger.TEN);
        Integer integer = new Integer(new BigInteger("42000"));

        assertThat(complexNumber, instanceOf(ComplexNumber.class));
        assertThat(realNumber, instanceOf(ComplexNumber.class));
        assertThat(rationalNumber, instanceOf(ComplexNumber.class));
        assertThat(integer, instanceOf(ComplexNumber.class));
    }
}
