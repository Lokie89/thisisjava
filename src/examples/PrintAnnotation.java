package examples;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintAnnotation {
    String value() default "-";

    int number() default 15;
}


class Service {

    @PrintAnnotation
    public void method1() {
        System.out.println("example 1");
    }

    @PrintAnnotation("*")
    public void method2() {
        System.out.println("example 2");
    }

    @PrintAnnotation(value = "#", number = 20)
    public void method3() {
        System.out.println("example 3");
    }
}

class PrintAnnotationExample {
    public static void main(String[] args) {
        Method[] declaredMethods = Service.class.getDeclaredMethods();

        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(PrintAnnotation.class)) {
                PrintAnnotation printAnnotation = method.getAnnotation(PrintAnnotation.class);
                System.out.println(method.getName());
                for (int i = 0; i < printAnnotation.number(); i++) {
                    System.out.print(printAnnotation.value());
                }
                System.out.println();
                try {
                    method.invoke(new Service());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}