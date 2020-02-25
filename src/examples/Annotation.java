package examples;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface Annotation {
    String value();

    int elementName2() default 5;
}

@Annotation("a")
class testAnnotation {

}

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@interface Annotation2 {

}

@Annotation2
class testAnnotation2 {

    @Annotation2
    final int a = 1;

    //    @Annotation2
    public testAnnotation2() {

    }

    @Annotation2
    public void test2() {

    }
}

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface Annotation3{

}