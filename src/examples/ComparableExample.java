package examples;

import java.util.Iterator;
import java.util.TreeSet;

public class ComparableExample {
    public static void main(String[] args) {
        TreeSet<Person2> treeSet = new TreeSet<>();
        treeSet.add(new Person2("홍길동", 45));
        treeSet.add(new Person2("김자바", 25));
        treeSet.add(new Person2("박지원", 31));
        Iterator<Person2> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            Person2 person2 = iterator.next();
            System.out.println(person2.name + ":" + person2.age);
        }
    }


}

class Person2 implements Comparable<Person2> {
    String name;
    int age;

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person2 person2) {
        if (age < person2.age) {
            return -1;
        } else if (age == person2.age) {
            return 0;
        } else {
            return 1;
        }
    }
}
