package examples;

public abstract class AbstractExample {
    public void breath(){
        System.out.println("숨셔");
    }
    public abstract void sound();
}
class Bird extends AbstractExample{
    public void breath(){

    }
    @Override
    public void sound() {
        System.out.println("짹짹");
    }
}
