package com.test.optional;

import java.util.*;
import java.util.stream.*;

public class Rolodex {
    private final Set<Person> people = new HashSet<>();

    public Rolodex() {
        initializeWithSomePeople();
    }

    public Optional<Person> findByName(String name) {
        for (Person p : people) {
            if (p.getName().equals(name)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public List<Person> findByCenturyOfBirth(int firstYearInCentury) {
        if (firstYearInCentury % 100 !=0 ) {
            throw new IllegalArgumentException("First year in century must end in 00, e.g. 1800, 1900");
        }
        return people.stream().filter(p -> p.getCenturyOfBirth() == firstYearInCentury).collect(Collectors.toList());
    }

    private void initializeWithSomePeople() {
        people.add(new Person("Adele", "1988-05-05"));
        people.add(new Person("Einstein", "1879-03-14"));
        people.add(new Person("Elvis", "1935-01-08"));
        people.add(new Person("Meitner", "1878-11-07"));
    }
}
