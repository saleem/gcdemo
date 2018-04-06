package com.test.gcdemo;

import com.test.gcdemo.Apartment;
import com.test.gcdemo.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.ref.WeakReference;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class GarbageCollectionTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream originalOutStream;
    private PrintStream originalErrStream;

    @Before public void setUpStreams() {
        originalOutStream = System.out;
        originalErrStream = System.err;
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After public void cleanUpStreams() {
        System.setOut(originalOutStream);
        System.setErr(originalErrStream);
    }

    @Test public void testConstructors() {
        Person person = new Person("John");
        Apartment apartment = new Apartment(73);
        assertThat(outContent.toString(), containsString("Person John is being constructed"));
        assertThat(outContent.toString(), containsString("Apartment 73 is being constructed"));
    }

    @Test public void testStrongReferencesNotSetToNullAreNotGarbageCollected() {
        Person person = new Person("John");
        Apartment apartment = new Apartment(73);
        gc();
        assertThat(outContent.toString(), not(containsString("Person John is being garbage collected")));
        assertThat(outContent.toString(), not(containsString("Apartment 73 is being garbage collected")));
    }

    @Test public void testStrongReferencesMustBeSetToNullToBeGarbageCollected() {
        Person person = new Person("John");
        Apartment apartment = new Apartment(73);
        person = null;
        apartment = null;
        gc();
        assertThat(outContent.toString(), containsString("Person John is being garbage collected"));
        assertThat(outContent.toString(), containsString("Apartment 73 is being garbage collected"));
    }

    @Test public void testWeakReferencesNotSetToNullAreGarbageCollected() {
        WeakReference<Person> person = new WeakReference<>(new Person("John"));
        WeakReference<Apartment> apartment = new WeakReference<>(new Apartment(73));
        gc();
        assertThat(outContent.toString(), containsString("Person John is being garbage collected"));
        assertThat(outContent.toString(), containsString("Apartment 73 is being garbage collected"));
    }

    @Test public void testStrongCircularReferencesAreGarbageCollectedWhenUnreachable() {
        Person person = new Person("John");
        Apartment apartment = new Apartment(73);
        person.setApartment(apartment);
        apartment.setTenant(person);
        person = null;
        apartment = null;
        gc();
        assertThat(outContent.toString(), containsString("Person John is being garbage collected"));
        assertThat(outContent.toString(), containsString("Apartment 73 is being garbage collected"));
    }

    @Test public void testWeakCircularReferencesAreGarbageCollectedAggressively() {
        WeakReference<Person> person = new WeakReference<>(new Person("John"));
        WeakReference<Apartment> apartment = new WeakReference<>(new Apartment(73));
        person.get().setApartment(apartment.get());
        apartment.get().setTenant(person.get());
        gc();
        assertThat(outContent.toString(), containsString("Person John is being garbage collected"));
        assertThat(outContent.toString(), containsString("Apartment 73 is being garbage collected"));
    }

    private void gc() {
        System.gc();
        halt();
    }

    private void halt() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            System.err.println("Warning: thread interrupted after System.gc() call; garbage collection may not be finished");
        }
    }
}
