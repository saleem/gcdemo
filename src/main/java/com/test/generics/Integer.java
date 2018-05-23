package com.test.generics;

import java.math.*;

public class Integer extends RationalNumber {
        public Integer(BigInteger value) {
            super(value, BigInteger.ONE);
        }
}
