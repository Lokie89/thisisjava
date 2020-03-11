package examples;


public class GenericMethodExample {
    public static <T> BoxGeneric<T> boxing(T t){
        BoxGeneric<T> box = new BoxGeneric<>();
        box.set(t);
        return box;
    }
}
