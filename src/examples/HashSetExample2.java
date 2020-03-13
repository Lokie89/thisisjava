package examples;

import java.util.HashSet;
import java.util.Set;

public class HashSetExample2 {
    public static void main(String[] args) {
        Set<Member4> set = new HashSet<>();
        set.add(new Member4("홍길동",30));
        set.add(new Member4("홍길동",30));
        System.out.println("총 객체 수: "+set.size());
    }
}

class Member4 {
    public String name;
    public int age;

    public Member4(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member4) {
            Member4 member4 = (Member4) obj;
            return member4.name.equals(name) && member4.age == age;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode() + age;
    }
}
