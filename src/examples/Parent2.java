package examples;

public class Parent2 {

}
class Child2 extends Parent2{

}

class Son{
    final Parent2 parent2;
    public Son(Parent2 parent2){
        this.parent2 = parent2;
    }
}
class Main2{
    public static void main(String[] args) {
        Child2 child2 = new Child2();
        new Son(child2);
    }
}