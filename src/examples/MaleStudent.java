package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaleStudent {
    private List<Student7> list;

    public MaleStudent() {
        list = new ArrayList<>();
        System.out.println("[" + Thread.currentThread().getName() + "] MaleStudent()");
    }

    public void accumulate(Student7 student7) {
        list.add(student7);
        System.out.println("[" + Thread.currentThread().getName() + "] accumulate()");
    }

    public void combine(MaleStudent other) {
        list.addAll(other.getList());
        System.out.println("[" + Thread.currentThread().getName() + "] combine()");
    }

    public List<Student7> getList() {
        return list;
    }
}

class MaleStudentExample {
    public static void main(String[] args) {
        List<Student7> totalList = Arrays.asList(
                new Student7("홍길동", 10, Student7.Sex.MALE),
                new Student7("김수애", 6, Student7.Sex.FEMALE),
                new Student7("신용권", 10, Student7.Sex.MALE),
                new Student7("박수미", 6, Student7.Sex.FEMALE)
        );

        MaleStudent maleStudent = totalList
                .stream()
                .filter(s -> s.getSex() == Student7.Sex.MALE)
                .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine)
                ;

        maleStudent.getList()
                .stream()
                .forEach(s -> System.out.println(s.getName()))
        ;

    }
}