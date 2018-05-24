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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexNumber)) return false;

        ComplexNumber that = (ComplexNumber) o;

        if (!realPart.equals(that.realPart)) return false;
        return imaginaryPart.equals(that.imaginaryPart);
    }

    @Override
    public int hashCode() {
        int result = realPart.hashCode();
        result = 31 * result + imaginaryPart.hashCode();
        return result;
    }
}
