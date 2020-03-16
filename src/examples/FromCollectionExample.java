package examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FromCollectionExample {
    public static void main(String[] args) {
        List<Student5> list = Arrays.asList(
                new Student5("홍길동", 10),
                new Student5("신용권", 20),
                new Student5("유미선", 30)
        );
        Stream<Student5> stream = list.stream();
        stream.forEach(s -> System.out.println(s.name));
    }
}
