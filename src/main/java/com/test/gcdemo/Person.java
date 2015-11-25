package com.test.gcdemo;

public class Person {
    private String name;
    private Apartment apartment;

    public Person(String name) {
        this.name = name;
        System.out.printf("Person %s is being constructed\n", name);
    }

    protected void finalize() throws Throwable {
        System.out.printf("Person %s is being garbage collected\n", name);
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
