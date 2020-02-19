package examples;

public class Dontknow {
    public static void main(String[] args) {
        int[] values = {1, 2, 3};
        dontknow(values);
        int value = 1;
        int value2 = 2;
        dontknow2(value, value2);
        dontknow2(values);
    }

    public static void dontknow(int[] values) {
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
    }

    public static void dontknow2(int... values) {
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
    }
}
