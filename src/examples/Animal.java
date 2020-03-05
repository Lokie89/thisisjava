package examples;

public class Animal {
    public void method1(){
        System.out.println("Animal");
    }
    public void method2(){}
}

class Cat extends Animal{
    public void method1(){
        System.out.println("Cat");
    }
    public void method2(){}
    public void method3(){}
}

class Main3{
    public static void main(String[] args) {
        Cat cat = new Cat();
        Animal animal = cat;
        System.out.println(animal == cat);
        cat.method3(); // 호출 가능
//        animal.method3(); // 호출 불가능

        cat.method1(); // Cat
        animal.method1(); // Cat
        Animal animal2 = new Animal();
        animal2.method1(); // Animal
    }
}