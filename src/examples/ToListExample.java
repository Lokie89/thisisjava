package examples;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ToListExample {
    public static void main(String[] args) {
        List<Student7> totalList = Arrays.asList(
                new Student7("홍길동", 10, Student7.Sex.MALE),
                new Student7("김수애", 6, Student7.Sex.FEMALE),
                new Student7("신용권", 10, Student7.Sex.MALE),
                new Student7("박수미", 6, Student7.Sex.FEMALE)
        );

        // 남학생 List 생성
        List<Student7> maleList = totalList
                .stream()
                .filter(s -> s.getSex() == Student7.Sex.MALE)
                .collect(Collectors.toList())
                ;
        maleList
                .stream()
                .forEach(s -> System.out.println(s.getName()))
        ;

        System.out.println();

        // 여학생 HashSet 생성
        Set<Student7> femaleSet = totalList
                .stream()
                .filter(s -> s.getSex() == Student7.Sex.FEMALE)
                .collect(Collectors.toCollection(HashSet::new))
                ;
        femaleSet
                .stream()
                .forEach(s -> System.out.println(s.getName()))
        ;
    }
}

class Student7 {
    public enum Sex {
        MALE,
        FEMALE,
        ;
    }

    public enum City {
        SEOUL,
        PUSAN,
        ;
    }

    private String name;
    private int score;
    private Sex sex;
    private City city;

    public Student7(String name, int score, Sex sex) {
        this.name = name;
        this.score = score;
        this.sex = sex;
    }

    public Student7(String name, int score, Sex sex, City city) {
        this(name, score, sex);
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Sex getSex() {
        return sex;
    }

    public City getCity() {
        return city;
    }
}