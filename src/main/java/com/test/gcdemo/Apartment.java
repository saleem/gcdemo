package com.test.gcdemo;

public class Apartment {
    private int number;
    private Person tenant;

    public Apartment(int number) {
        this.number = number;
        System.out.printf("Apartment %d is being constructed\n", number);
    }

    protected void finalize() throws Throwable {
        System.out.printf("Apartment %d is being garbage collected\n", number);
    }

    public Person getTenant() {
        return tenant;
    }

    public void setTenant(Person tenant) {
        this.tenant = tenant;
    }
}
