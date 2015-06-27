package com.test;

import java.lang.ref.WeakReference;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WeakReference<Person> personWeakReference = new WeakReference<>(new Person("John"));
        WeakReference<Apartment> apartmentWeakReference = new WeakReference<>(new Apartment(73));
//        personWeakReference = null;
//        apartmentWeakReference = null;
        System.gc();
        Thread.sleep(500);
    }
}
