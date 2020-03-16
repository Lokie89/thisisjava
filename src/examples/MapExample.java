package examples;

import java.util.Arrays;
import java.util.List;

public class MapExample {
    public static void main(String[] args) {
        List<Student2> studentList = Arrays.asList(
                new Student2("홍길동", 10),
                new Student2("신용권", 20),
                new Student2("유미선", 30)
        );
        studentList
                .stream()
                .mapToInt(Student2::getScore)
                .forEach(System.out::println);
    }
}
