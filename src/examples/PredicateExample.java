package examples;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExample {
    private static List<Student4> list = Arrays.asList(
            new Student4("홍길동", "남자", 90),
            new Student4("김순희", "여자", 90),
            new Student4("감자바", "남자", 95),
            new Student4("박한나", "여자", 92)
    );

    public static double avg(Predicate<Student4> predicate) {
        int count = 0, sum = 0;
        for (Student4 student4 : list) {
            if (predicate.test(student4)) {
                count++;
                sum += student4.getScore();
            }
        }
        return (double) sum / count;
    }

    public static void main(String[] args) {
        double maleAvg = avg(t -> t.getSex().equals("남자"));
        System.out.println("남자 평균 점수: " + maleAvg);

        double femaleAvg = avg(t -> t.getSex().equals("여자"));
        System.out.println("여자 평균 점수: " + femaleAvg);
    }
}

class Student4 {
    private String name;
    private String sex;
    private int score;

    public Student4(String name, String sex, int score) {
        this.name = name;
        this.sex = sex;
        this.score = score;
    }

    public String getSex() {
        return sex;
    }

    public int getScore() {
        return score;
    }
}