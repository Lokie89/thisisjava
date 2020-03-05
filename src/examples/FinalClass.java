package examples;

public class FinalClass {
    public void p(){
        System.out.println("final");
    }
}

class ChildClass extends FinalClass{
    public void p() {
        System.out.println("final child");
    }
}