package examples;

import java.util.function.IntBinaryOperator;

public class MethodReferencesExample {
    public static void main(String[] args) {
        IntBinaryOperator operator;

        operator = (x, y) -> Calculator3.staticMethod(x, y);
        System.out.println("결과1: " + operator.applyAsInt(1, 2));

        operator = Calculator3 :: staticMethod;
        System.out.println("결과2: " + operator.applyAsInt(1, 2));

        Calculator3 calculator3 = new Calculator3();

        operator = (x, y) -> calculator3.instanceMethod(x, y);
        System.out.println("결과3: " + operator.applyAsInt(1, 2));

        operator = calculator3::instanceMethod;
        System.out.println("결과4: " + operator.applyAsInt(1, 2));
    }

}

class Calculator3 {
    public static int staticMethod(int x, int y) {
        return x + y;
    }

    public int instanceMethod(int x, int y) {
        return x + y;
    }
}
