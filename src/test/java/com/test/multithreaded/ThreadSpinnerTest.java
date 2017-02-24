package com.test.multithreaded;

import org.junit.Ignore;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;

public class ThreadSpinnerTest {
    @Ignore ("Why does this test fail with an NPE?")
    @Test
    public void testRun() {
        Function<String, Integer> lengthCounter = s -> s.length();
        Consumer<Integer> onSuccess = i -> System.out.printf("Length successfully calculated as %d\n", i);
        Consumer<Exception> onFailure = e -> e.printStackTrace();
        ThreadSpinner<String, Integer> spinner = new ThreadSpinner<>(lengthCounter, onSuccess, onFailure);
        spinner.run("helloworld");
    }
}
