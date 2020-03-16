package examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LambdaExpressionsExample {
    public static void main(String[] args) {
        List<Student2> list = Arrays.asList(new Student2("홍길동", 90),
                new Student2("신용권", 92));

        Stream<Student2> stream = list.stream();
        stream.forEach(student -> {
            String name = student.name;
            int score = student.score;
            System.out.println(name + "-" + score);
        });
    }
}

class Student2 {
    String name;
    int score;

    public Student2(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
