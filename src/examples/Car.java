package examples;

public class Car {
    String model;
    String color;
    int maxSpeed;

    Car(String model) {
        this(model, "은색", 250);
    }

    Car(String model, String color) {
        this(model, color, 250);
    }

    Car(String model, String color, int maxSpeed) {
        this.model = model;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }
}

class Television {
    static String company = "SAMSUNG";
    static String model = "LCD";
    static String info;
    static String info2 = company + " - " + model;

    static {
        info = company + " - " + model;
    }
}

class TelevisionExample {
    public static void main(String[] args) {
        System.out.println(Television.info);
        System.out.println(Television.info2);
    }
}