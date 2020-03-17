package examples;

import java.util.Arrays;
import java.util.List;

public class ReductionExample {
    public static void main(String[] args) {
        List<Student6> student6List = Arrays.asList(
                new Student6("홍길동", 92),
                new Student6("김자바", 95),
                new Student6("신용권", 88)
        );

        int sum1 = student6List
                .stream()
                .mapToInt(Student6::getScore)
                .sum()
                ;

        int sum2 = student6List
                .stream()
                .map(Student6::getScore)
                .reduce((a, b) -> a + b)
                .get()
                ;

        int sum3 = student6List
                .stream()
                .map(Student6::getScore)
                .reduce(0, (a, b) -> a + b)
                ;
        System.out.println(sum1);
        System.out.println(sum2);
        System.out.println(sum3);
    }
}
