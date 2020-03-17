package examples;

import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;

public class OptionalExample {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();

//        double avg = list
//                .stream()
//                .mapToInt(Integer::intValue)
//                .average()
//                .getAsDouble()
//                ; // 예외발생 NoSuchElementException
        // 방법 1.
        OptionalDouble optionalDouble = list
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                ;
        if (optionalDouble.isPresent()) {
            System.out.println("평균: " + optionalDouble);
        } else {
            System.out.println("평균: 0.0");
        }

        // 방법 2.
        double avg2 = list
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0)
                ;
        System.out.println("평균: " + avg2);

        // 방법 3.
        list
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .ifPresent(a -> System.out.println("평균: " + a))
        ;
    }
}
