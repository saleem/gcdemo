package com.test;

public class Apartment {
    private int number;
    public Apartment(int number) {
        this.number = number;
        System.out.printf("Apartment %d is being constructed\n", number);
    }

    protected void finalize() throws Throwable {
        System.out.printf("Apartment %d is being garbage collected\n", number);
    }
}
