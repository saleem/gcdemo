package com.test.optional;

import java.text.*;
import java.util.*;

public class Person {

    private final String name;
    private final Date dateOfBirth;
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    public Person(String name, Date dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Person(String name, String dateOfBirth) throws IllegalArgumentException {
        try {
            this.name = Objects.requireNonNull(name);
            this.dateOfBirth = FORMATTER.parse(dateOfBirth);
        } catch(ParseException | NullPointerException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String getName() {
        return name;
    }

    public int getCenturyOfBirth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOfBirth);
        int year = cal.get(Calendar.YEAR);
        return year - year % 100;
    }
}

