package com.test.multithreaded;

import java.io.*;
import org.junit.*;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Unit test class illustrating the asynchronous nature of threads.
 * Both the tests in this class will pass. However, their outputs may be intertwined, e.g. like this:
 * <pre>
 * {@code
    Failure calculating length: java.lang.NullPointerException
    Length successfully calculated as 11

    at com.test.multithreaded.ThreadSpinnerTest.lambda$setup$0(ThreadSpinnerTest.java:24)
    at com.test.multithreaded.ThreadSpinnerTest$$Lambda$1/1101288798.apply(Unknown Source)
 * }
 *
 * </pre>
 */
public class ThreadSpinnerTest {
    ThreadSpinner<String, Integer> spinner;
    @Before
    public void setup() {
        Function<String, Integer> lengthCounter = s -> s.length();
        Consumer<Integer> onSuccess = i -> System.out.printf("Length successfully calculated as %d%n", i);
        Consumer<Exception> onFailure = e -> {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            System.err.printf("Failure calculating length: %s%n", sw);
        };
        spinner = new ThreadSpinner<>(lengthCounter, onSuccess, onFailure);
    }

    @Test
    public void testOnSuccess() {
        spinner.run("hello world");
    }

    @Test
    public void testOnFailure() {
        spinner.run(null);
    }
}
