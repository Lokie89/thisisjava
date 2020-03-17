package examples;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingAndReductionExample {
    public static void main(String[] args) {
        List<Student7> totalList = Arrays.asList(
                new Student7("홍길동", 10, Student7.Sex.MALE),
                new Student7("김수애", 6, Student7.Sex.FEMALE),
                new Student7("신용권", 10, Student7.Sex.MALE),
                new Student7("박수미", 6, Student7.Sex.FEMALE)
        );

        // 성별로 평균 점수를 저장하는 Map 얻기
        Map<Student7.Sex, Double> mapBySex = totalList
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Student7::getSex,
                                Collectors.averagingDouble(Student7::getScore)
                        )
                );
        System.out.println("남학생 평균 점수: " + mapBySex.get(Student7.Sex.MALE));
        System.out.println("여학생 평균 점수: " + mapBySex.get(Student7.Sex.FEMALE));

        // 성별을 쉼표로 구분한 이름을 저장하는 Map 얻기
        Map<Student7.Sex, String> mapByName = totalList
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Student7::getSex,
                                Collectors.mapping(
                                        Student7::getName,
                                        Collectors.joining(",")
                                )
                        )
                );

        System.out.println("남학생 전체 이름: " + mapByName.get(Student7.Sex.MALE));
        System.out.println("여학생 전체 이름: " + mapByName.get(Student7.Sex.FEMALE));
    }
}
