package examples;

import java.util.Arrays;
import java.util.List;

public class StreamPipelinesExample {
    public static void main(String[] args) {
        List<Member5> list = Arrays.asList(
                new Member5("홍길동", Member5.MALE, 30),
                new Member5("김나리", Member5.FEMALE, 20),
                new Member5("신용권", Member5.MALE, 45),
                new Member5("박수미", Member5.FEMALE, 27)
        );
        double ageAvg = list.stream()
                .filter(member5 -> member5.getSex() == Member5.MALE)
                .mapToInt(member5 -> member5.getAge())
                .average()
                .getAsDouble()
                ;
        System.out.println("남자 평균 나이: " + ageAvg);
    }
}

class Member5 {
    static int MALE = 0;
    static int FEMALE = 1;

    private String name;
    private int sex;
    private int age;

    public Member5(String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}