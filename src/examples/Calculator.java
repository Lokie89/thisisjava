package examples;

public class Calculator {
    double areaCircle(double r) {
        System.out.println("Calculator 객체의 areaCircle 실행");
        return 3.14159 * r * r;
    }
}

class Computer extends Calculator {
    @Override
    double areaCircle(double r) throws RuntimeException{
        System.out.println("Computer 객체의 areaCircle 실행");
        throw new RuntimeException();
    }
}

class Main {
    public static void main(String[] args) {
        Computer computer = new Computer();
        System.out.println(computer.areaCircle(3));
    }
}