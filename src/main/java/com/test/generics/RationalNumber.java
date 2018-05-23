package com.test.generics;

import java.math.*;
import java.util.*;

public class RationalNumber extends RealNumber {
    public static final int DEFAULT_SCALE = 0;

    public RationalNumber(BigInteger numerator, BigInteger denominator) {
        this(numerator, denominator, DEFAULT_SCALE);
    }

    public RationalNumber(BigInteger numerator, BigInteger denominator, int scale) {
        super(new BigDecimal(Objects.requireNonNull(numerator)).divide(new BigDecimal(Objects.requireNonNull(denominator)), scale, BigDecimal.ROUND_HALF_EVEN));
    }
}
