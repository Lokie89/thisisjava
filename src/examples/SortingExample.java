package examples;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class SortingExample {
    public static void main(String[] args) {
        IntStream intStream = Arrays.stream(new int[]{5, 3, 2, 1, 4});
        intStream
                .sorted()
                .forEach(System.out::println);
        System.out.println();
        List<Student6> list = Arrays.asList(
                new Student6("홍길동", 30),
                new Student6("신용권", 10),
                new Student6("김자바", 20)
        );
        list
                .stream()
                .sorted()
                .forEach(s -> System.out.println(s.getScore()));
        System.out.println();

        list
                .stream()
                .sorted(Comparator.reverseOrder())
                .forEach(s -> System.out.println(s.getScore()));
        System.out.println();

    }
}
