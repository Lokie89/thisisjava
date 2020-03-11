package examples;

public class Util2 {
    public static <T extends Number> int compare(T t1, T t2) {
        double v1 = t1.doubleValue();
        double v2 = t2.doubleValue();
        return Double.compare(v1, v2);
    }
}

class BoundedTypeParameterExample {
    public static void main(String[] args) {
//        int result1 = Util2.compare("a","b"); // Number 타입이 아니어서 에러

        int result1 = Util2.compare(10, 20);
        System.out.println(result1);

        int result2 = Util2.compare(4.5, 3);
        System.out.println(result2);
    }
}
