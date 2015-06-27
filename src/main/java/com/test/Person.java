package com.test;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
        System.out.printf("Person %s is being constructed\n", name);
    }

    protected void finalize() throws Throwable {
        System.out.printf("Person %s is being garbage collected\n", name);
    }
}
