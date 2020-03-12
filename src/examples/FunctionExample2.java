package examples;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;

public class FunctionExample2 {
    private static List<Student3> list = Arrays.asList(
            new Student3("홍길동", 90, 96),
            new Student3("놀부", 99, 98)
    );

    public static double avg(ToIntFunction<Student3> function) {
        int sum = 0;
        for (Student3 student3 : list) {
            sum += function.applyAsInt(student3);
        }
        return sum / list.size();
    }

    public static void main(String[] args) {
        double englishAvg = avg(s -> s.getEnglishScore());
        System.out.println("영어 평균 점수: " + englishAvg);
        double mathAvg = avg(s -> s.getMathScore());
        System.out.println("수학 평균 점수: " + mathAvg);
    }
}
