package com.test.gcdemo;

import java.lang.ref.WeakReference;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WeakReference<Person> personWeakReference = new WeakReference<>(new Person("John"));
        WeakReference<Apartment> apartmentWeakReference = new WeakReference<>(new Apartment(73));
        System.gc();
        Thread.sleep(500);
    }
}
