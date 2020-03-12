package examples;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class FunctionExample {
    private static List<Student3> list = Arrays.asList(
            new Student3("홍길동", 90, 96),
            new Student3("놀부", 99, 98)
    );

    public static void printString(Function<Student3, String> function) {
        for (Student3 student3 : list) {
            System.out.print(function.apply(student3) + " ");
        }
        System.out.println();
    }

    public static void printInt(ToIntFunction<Student3> function) {
        for (Student3 student3 : list) {
            System.out.print(function.applyAsInt(student3) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("[ 학생 이름 ]");
        printString(t -> t.getName());

        System.out.println("[ 영어 점수 ]");
        printInt(t -> t.getEnglishScore());

        System.out.println("[ 수학 점수 ]");
        printInt(t -> t.getMathScore());
    }
}

class Student3 {
    private String name;
    private int englishScore;
    private int mathScore;

    public Student3(String name, int englishScore, int mathScore) {
        this.name = name;
        this.englishScore = englishScore;
        this.mathScore = mathScore;
    }

    public String getName() {
        return name;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public int getMathScore() {
        return mathScore;
    }
}


