package com.test.generics;

import java.math.*;
import java.util.*;

public class ComplexNumber {
    private final BigDecimal realPart;
    private final BigDecimal imaginaryPart;

    public ComplexNumber(BigDecimal realPart, BigDecimal imaginaryPart) {
        this.realPart = Objects.requireNonNull(realPart);
        this.imaginaryPart = Objects.requireNonNull(imaginaryPart);
    }

    @Override
    public String toString() {
        return "ComplexNumber{" +
                "realPart=" + realPart +
                ", imaginaryPart=" + imaginaryPart +
                '}';
    }
}
