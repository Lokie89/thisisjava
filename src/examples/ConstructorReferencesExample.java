package examples;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorReferencesExample {
    public static void main(String[] args) {
        Function<String, Member3> function1 = Member3::new;
        Member3 member1 = function1.apply("angel");

        BiFunction<String, String, Member3> function2 = Member3::new;
        Member3 member2 = function2.apply("신천사", "angel");

        Supplier<Member3> supplier1 = Member3::new;
        Member3 member3 = supplier1.get();
    }
}

class Member3 {
    String name;
    String id;

    public Member3() {
        System.out.println("Member() 실행");
    }

    public Member3(String id) {
        this.id = id;
        System.out.println("Member(String id) 실행");
    }


    public Member3(String name, String id) {
        this.name = name;
        this.id = id;
        System.out.println("Member(String name, String id) 실행");
    }

    public String getId() {
        return id;
    }
}