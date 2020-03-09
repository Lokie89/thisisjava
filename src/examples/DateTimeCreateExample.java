package examples;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DateTimeCreateExample {
    public static void main(String[] args) throws InterruptedException{
        LocalDate currDate = LocalDate.now();
        System.out.println(currDate);

        LocalDate targetDate = LocalDate.of(2024, 5, 10);
        System.out.println(targetDate);

        LocalTime currTime = LocalTime.now();
        System.out.println(currTime);

        LocalTime targetTime = LocalTime.of(6, 30, 0, 0);
        System.out.println(targetTime);

        LocalDateTime currDateTime = LocalDateTime.now();
        System.out.println(currDateTime);

        LocalDateTime targetDateTime = LocalDateTime.of(2024, 5, 10, 6, 30, 0, 0);
        System.out.println(targetDateTime);

        ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println(utcDateTime);
        ZonedDateTime newyorkDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(newyorkDateTime);

        Instant instant = Instant.now();
        Thread.sleep(10);
        Instant instant1 = Instant.now();
        if (instant.isBefore(instant1)) {
            System.out.println("instant 가 빠름");
        } else if (instant.isAfter(instant1)) {
            System.out.println("instant1 이 빠름");
        } else {
            System.out.println("같음");
        }
        System.out.println(instant.until(instant1, ChronoUnit.NANOS));
    }
}
