package examples;

import java.util.Arrays;
import java.util.List;

public class MapAndReduceExample {
    public static void main(String[] args) {
        List<Student5> list = Arrays.asList(
                new Student5("홍길동", 10),
                new Student5("신용권", 20),
                new Student5("유미선", 30)
        );
        double avg = list.stream()
                // 중간 처리( 학생 객체를 점수로 매핑 )
                .mapToInt(Student5::getScore)
                // 최종 처리( 평균 점수 )
                .average()
                .getAsDouble()
                ;
        System.out.println("평균 점수: " + avg);
    }
}

class Student5 {
    String name;
    int score;

    public Student5(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
