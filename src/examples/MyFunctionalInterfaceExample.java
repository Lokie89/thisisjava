package examples;

public class MyFunctionalInterfaceExample {
    public static void main(String[] args) {
        TestFun testFun = new TestFun();
        testFun.test();
    }
}

@FunctionalInterface
interface MyFunctionalInterface {
    public int method(int x);
}

class TestFun {
    public void test() {
        MyFunctionalInterface myFunctionalInterface = (x) -> {
            test2();
            return x * 5;
        };
        myFunctionalInterface.method(3);
    }

    private void test2() {
        System.out.println("test2");
    }
}