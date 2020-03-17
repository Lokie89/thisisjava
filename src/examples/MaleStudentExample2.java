package examples;

import java.util.Arrays;
import java.util.List;

public class MaleStudentExample2 {
    public static void main(String[] args) {
        List<Student7> totalList = Arrays.asList(
                new Student7("홍길동", 10, Student7.Sex.MALE),
                new Student7("김수애", 6, Student7.Sex.FEMALE),
                new Student7("신용권", 10, Student7.Sex.MALE),
                new Student7("박수미", 6, Student7.Sex.FEMALE)
        );
        MaleStudent maleStudent = totalList
                .parallelStream()
                .filter(s->s.getSex()== Student7.Sex.MALE)
                .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine)
                ;

        maleStudent.getList()
                .stream()
                .forEach(s-> System.out.println(s.getName()));
    }
}
