package examples;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByExample {
    public static void main(String[] args) {
        List<Student7> totalList = Arrays.asList(
                new Student7("홍길동", 10, Student7.Sex.MALE, Student7.City.SEOUL),
                new Student7("김수애", 6, Student7.Sex.FEMALE, Student7.City.SEOUL),
                new Student7("신용권", 10, Student7.Sex.MALE, Student7.City.PUSAN),
                new Student7("박수미", 6, Student7.Sex.FEMALE, Student7.City.PUSAN)
        );

        Map<Student7.Sex, List<Student7>> mapBySex = totalList
                .stream()
                .collect(Collectors.groupingBy(Student7::getSex));

        System.out.println("[ 남학생 ]");
        mapBySex
                .get(Student7.Sex.MALE)
                .stream()
                .forEach(s -> System.out.print(s.getName() + " "));

        System.out.println("\n[ 여학생 ]");
        mapBySex
                .get(Student7.Sex.FEMALE)
                .stream()
                .forEach(s -> System.out.print(s.getName() + " "));

        System.out.println();

        Map<Student7.City, List<String>> mapByCity = totalList
                .stream()
                .collect(Collectors.groupingBy(Student7::getCity,
                        Collectors.mapping(Student7::getName, Collectors.toList())));

        System.out.println("\n[ 서울 ]");
        mapByCity
                .get(Student7.City.SEOUL)
                .stream()
                .forEach(n -> System.out.print(n + " "));

        System.out.println("\n[ 부산 ]");
        mapByCity
                .get(Student7.City.PUSAN)
                .stream()
                .forEach(n -> System.out.print(n + " "));
    }
}

