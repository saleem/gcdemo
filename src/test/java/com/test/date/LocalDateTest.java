package com.test.date;

import java.util.*;
import org.junit.Test;

import java.time.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;


public class LocalDateTest {

    @Test
    public void localDateTimeWithFixedClock() {
        Clock clock = Clock.fixed(Instant.ofEpochSecond(1236092750L), ZoneId.of("UTC"));
        LocalDateTime ldt = LocalDateTime.now(clock);
        assertThat(ldt.toString(), is("2009-03-03T15:05:50"));
    }

    @Test
    public void utcClockIsLaterThanChicagoClock() throws InterruptedException {
        LocalDateTime ldtUTC = LocalDateTime.now(Clock.systemUTC());
        Thread.sleep(2000);
        LocalDateTime ldtChicago = LocalDateTime.now(Clock.system(ZoneId.of("America/Chicago")));
        assertTrue(ldtUTC.isAfter(ldtChicago));
    }

    @Test
    public void currentLocalDateTimeDependsOnTimezone() {
        LocalDateTime ldtUTC = Objects.requireNonNull(LocalDateTime.now(ZoneId.of("UTC")));
        LocalDateTime ldt = Objects.requireNonNull(LocalDateTime.now());
        if(ldt.isBefore(ldtUTC)) {
            System.out.println("Your system timezone is probably west of UTC");
        }
        else {
            System.out.println("Your system timezone is probably UTC or east of UTC");
        }
    }

    @Test
    public void ldtCanJumpIfZonedForDSTGap() {
        LocalDateTime ldtMinuteBeforeDST = LocalDateTime.of(2017, Month.MARCH, 12, 1, 59);
        ZonedDateTime zdtChicagoMinuteBeforeDST = ldtMinuteBeforeDST.atZone(ZoneId.of("America/Chicago"));
        LocalDateTime ldtMinuteOfDSTSwitch = LocalDateTime.of(2017, Month.MARCH, 12, 2, 0);
        ZonedDateTime zdtChicagoMinuteOfDSTSwitch = ldtMinuteOfDSTSwitch.atZone(ZoneId.of("America/Chicago"));
        assertThat(zdtChicagoMinuteOfDSTSwitch.getHour(), is(ldtMinuteOfDSTSwitch.getHour() + 1));
        assertThat(zdtChicagoMinuteBeforeDST.getHour(), is(ldtMinuteBeforeDST.getHour()));
    }

}
