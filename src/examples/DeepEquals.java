package examples;

import java.util.Arrays;

public class DeepEquals {
    public static void main(String[] args) {
//        A a = new A(3);
//        A b = new A(4);
//        A[] as = new A[2];
//        as[0] = a;
//        as[1] = b;
//        A[] as2 = new A[2];
//        A c = new A(3);
//        A d = new A(4);
//        as2[0] = c;
//        as2[1] = d;
//        System.out.println(Arrays.equals(as, as2));
//        System.out.println(Arrays.deepEquals(as, as2));

        Integer[] ints = new Integer[2];
        ints[0] = 3;
        ints[1] = 4;
        Integer[] ints2 = new Integer[2];
        ints2[0] = 3;
        ints2[1] = 4;
        System.out.println(Arrays.equals(ints, ints2));
        System.out.println(Arrays.deepEquals(ints, ints2));
    }

    static class A {
        int a;

        public A(int a) {
            this.a = a;
        }
    }
}
