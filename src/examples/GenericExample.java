package examples;

public class GenericExample {
    public static void main(String[] args) {
        BoxObject box = new BoxObject();
        box.set("물건");
        String someStr = (String) box.get();
        box.set(1);
        Integer someInt = (Integer) box.get();

        BoxGeneric<String> boxGeneric = new BoxGeneric();
        boxGeneric.set("물건");
        String someStr2 = boxGeneric.get();
        BoxGeneric<Integer> boxGeneric2 = new BoxGeneric();
        boxGeneric2.set(1);
        Integer someInt2 = boxGeneric2.get();
    }
}

class BoxObject {
    private Object object;

    public void set(Object object) {
        this.object = object;
    }

    public Object get() {
        return object;
    }
}

class BoxGeneric<T> {
    private T object;

    public void set(T object) {
        this.object = object;
    }

    public T get() {
        return object;
    }
}
