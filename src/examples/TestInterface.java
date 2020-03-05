package examples;

public interface TestInterface {
    int a = 1;
    void test();
    static void test2(){
        System.out.println("test2");
    }
    default void test3(){

    }
}
class TestClass implements TestInterface{

    @Override
    public void test() {
        System.out.println("TestClass");
    }


}

class TestClass2 {
    TestInterface testInterface;
    public TestClass2(TestInterface testInterface){
        this.testInterface = testInterface;
        testInterface.test();
    }

}
class Main4{
    public static void main(String[] args) {
        TestClass2 class2 = new TestClass2(() -> System.out.println("abcd"));
        TestInterface testInterface = new TestClass();
        TestClass2 class3 = new TestClass2(testInterface);
        System.out.println(TestInterface.a);
        TestInterface.test2();
    }
}