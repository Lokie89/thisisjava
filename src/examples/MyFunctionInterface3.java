package examples;

@FunctionalInterface
public interface MyFunctionInterface3 {
    public void method();
}

class UsingLocalVariable {
    void method(int arg) {
        int localVar = 40;
//        arg = 31;
//        localVar = 41;
        MyFunctionInterface3 fi = () -> {
            System.out.println("arg: " + arg);
            System.out.println("localVar: " + localVar);
        };
        fi.method();
    }
}
