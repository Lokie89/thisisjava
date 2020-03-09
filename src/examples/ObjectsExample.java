package examples;

import java.util.Comparator;
import java.util.Objects;

public class ObjectsExample {
    static class A {
        int a;

        public A(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }
    }

    public static void main(String[] args) {
//        int a = Objects.compare(new A(4), new A(3), Comparator.comparingInt(A::getA));
//        System.out.println(a);
//        A c = new A(5);
//        A d = new A(5);
//        A e = c;
//        System.out.println(Objects.deepEquals(c, d));
//        System.out.println(Objects.deepEquals(c, e));

        System.out.println(System.getenv());
    }
}
