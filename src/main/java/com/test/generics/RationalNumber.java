package com.test.generics;

import java.math.*;
import java.util.*;

public class RationalNumber extends RealNumber {
    public RationalNumber(BigInteger numerator, BigInteger denominator) {
        super(new BigDecimal(Objects.requireNonNull(numerator)).divide(new BigDecimal(Objects.requireNonNull(denominator))));
    }
}
