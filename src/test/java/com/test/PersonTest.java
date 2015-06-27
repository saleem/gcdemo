package com.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.ref.WeakReference;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class PersonTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
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
