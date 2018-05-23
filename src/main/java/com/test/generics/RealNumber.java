package com.test.generics;

import java.math.*;

public class RealNumber extends ComplexNumber {
    public RealNumber(BigDecimal value) {
        super(value, BigDecimal.ZERO);
    }
}
