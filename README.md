# 몰랐던거 정리
### 1. JVM 
운영체제 위에서(종속되어) 실행되는 자바 Java 가상 Virtual 머신 Machine<br>

자바 언어를 컴파일하여 바이트 코드를 번역한 후 JVM에서 실행하면<br>
다른 운영체제에서도 같은 결과를 얻을 수 있다

따라서 java 언어는 운영체제와 별개로움직이지만 jvm 은 종속적이다

java -> byte code -> JVM(번역) -> OS

JVM 만 해당 OS 타입에 맞게 번역 해주면 됨

### 2. 연산시 타입
int(4byte) 보다 작은 타입의 정수 계산은 int 타입을 기본으로 한다.<br>
연산자의 크기가 4byte 기 때문

```java
class CompileError{
    public static void main(String[] args){
        byte b = 4;
        byte result = b + b; // 컴파일 에러
    }
}
```

### 3. 쓰레기값 생성
가수를 사용하는 부동소수점 타입 (float, double) 은 0.1을 정확하게 표현할 수 없어 근사치로 처리함<br>
만약 정확하게 표현하고 싶으면 int 형으로 변경후 계산 
```java
class Trash{
    public static void main(String[] args){
        int apple = 1;
        double pieceUnit = 0.1;
        int number = 7;
        double result = apple - number * pieceUnit; // 쓰레기값 생성
        System.out.println(result); // 0.29999999999999993
    }
}
```

```java
class Bit{
    public static void main(String[] args){
        double v4 = 0.1;
        float v5 = 0.1f;
        System.out.println(v4 == v5); // false
    }
}
```

### 4. 비트 논리 연산자
2진법으로 바꾼 후 같은 자리수를 연산하여 나타냄
```java
class Bit{
    public static void main(String[] args){
        System.out.println("45 & 25 = " + (45 & 25)); // 45 & 25 = 9
        System.out.println("45 | 25 = " + (45 | 25)); // 45 | 25 = 61
        System.out.println("45 ^ 25 = " + (45 ^ 25)); // 45 ^ 25 = 52
        System.out.println("~45 = " + (~45)); // ~45 = -46
    }   
}
```

### 5. 비트 이동 연산자
2진법으로 바꾼 후 옆으로 밈 빈 공간은 0으로 채움<br>
a << b  : 정수 a를 b만큼 왼쪽으로 이동 (빈칸은 0으로 채움)<br>
a >> b  : 정수 a를 b만큼 오른쪽으로 이동 (빈칸은 부호비트로 채움 -일때는 1 +일때는 0)<br>
a >>> b : 정수 a를 b만큼 오른쪽으로 이동 (빈칸은 0으로 채움)
```java
class Bit{
    public static void main(String[] args){
        System.out.println("1 << 3 = " + (1 << 3));     // 1 << 3 = 8
        System.out.println("-8 >> 3 = " + (-8 >> 3));   // -8 >> 3 = -1
        System.out.println("-8 >>> 3 = " + (-8 >>> 3)); // -8 >>> 3 = 536870911
    }
}
```

### 6. 메모리 사용 영역
JVM이 실행되면 운영체제에서 메모리 영역을 할당 받는다
#### 메소드 영역
    클래스( ~.class )들을 클래스 로더로 읽어 클래스별로
    런타임 상수풀, 필드데이터, 메소드 데이터, 메소드 코드, 생성자 코드 등을
    분류하여 저장, JVM이 시작할 때 생성되고 모든 스레드가 공유
#### 힙 영역
    객체와 배열이 생성되는 영역
    스택영역에서 변수에 할당하는 주소값이 이 힙 영역의 주소값이다.
    참조하는 변수나 필드가 없다면 JVM은 Garbage Collector 를 실행시켜 제거함
#### 스택 영역
    스택 영역은 각 스레드마다 하나씩 존재하며 스레드가 시작될 때 할당됨
    스레드를 생성하지 않으면 main 스레드 한개임
    메소드를 호출할 때마다 프레임을 추가(push) 메소드가 종료되면 해당 프레임을 제거(pop)
    프레임 안에는 로컬 변수 스택이 있는데, 기본타입, 참조 타입 변수가 추가(push) 제거(pop) 된다
    정리 : 1스레드 안 1JVMStack 안 n개프레임 안 n개변수스택
    
### 7. main() 메소드
```java
class MainStringArrayArgument {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("프로그램의 사용법");
            System.out.println("java MainStringArrayArgument num1 num2");
            System.exit(0);
        }
        String strNum1 = args[0];
        String strNum2 = args[1];

        int num1 = Integer.parseInt(strNum1);
        int num2 = Integer.parseInt(strNum2);
        int result = num1 + num2;
        System.out.println(num1 + " + " + num2 + " = " + result);
    }
}
```
    run configuration 에서 arguments 를 10 20 으로 수정하면 10 + 20 = 30
    근데 이거 어디다가 쓰는건지 모르겠음
    Command line 에서 java 파일을 실행할때 ( class 파일로 변환하여 main 함수 실행 )
    뒤쪽에 옵션 형태로 입력 하면 String[] args 배열로 그 문자들이 들어옴
    e.g) javac Foo.java
        java Foo 111 222 333 444
        -> main 함수의 String[] args = {"111","222","333","444"} 로 입력되어 실행
        
### 8. 다차원 배열
배열 안의 배열, 맨 안쪽 배열 힙 영역에 생성, 안쪽배열의 주소값 '도' 힙영역에 생성
가장 바깥쪽 배열의 주소값만 스택영역에 저장 

### 9. ENUM
```java
public enum Week {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
```
열거형 상수 enum 은 Method 영역에 생성, 한 상수가 객체형이므로 Heap 영역에 한 상수 당 한 객체(위에선 Week)가 생성된다.<br>
위의 enum 에선 7개의 Week 객체가 Heap 영역에 생성되며 열거형 상수 값을 들고있다.<br>
열거형 상수의 값을 다른 객체에 대입 하였을 경우
```java
class EnumWeekExample{
    public static void main(String[] args){
        Week today = Week.SUNDAY;
    }
}
```
Method 영역에 들어가있는 주소 값 (Heap 영역의 SUNDAY 상수 값을 가진 Week 객체)<br>
Stack 영역의 today 변수에 넣는다.<br>
그러므로 Stack 영역의 today 변수와 Method 영역의 SUNDAY 변수는 같은 주소 값(Heap 영역의 SUNDAY 상수 값을 가진 Week 객체)를 갖게 된다.
```java
class EnumWeekExample{
    public static void main(String[] args){
        today == Week.SUNDAY; // true
    }
}
```

#### ENUM method
```java
class enumMethod{
    public static void main(String[] args) {
        System.out.println(Week.SUNDAY.name()); // 열거 타입을 정의할 때 사용한 상수 이름
        System.out.println(Week.FRIDAY.ordinal()); // 열거 객체가 전체 열거 객체에서 몇 번째 순번
        System.out.println(Week.FRIDAY.compareTo(Week.MONDAY)); // 앞.compareTo(뒤) 뒤에 객체 기준 앞에 객체 순번차이
        System.out.println(Week.valueOf("SATURDAY")); // 매개값과 동일한 문자열을 가지는 객체 반환
        System.out.println();
        Week[] values = Week.values(); // 전체 열거 객체를 배열로 반환
        for(Week value : values){
            System.out.println(value);
        }
    }
}
```

### 10. 다른생성자 호출 this()
```java
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
```

### 11. 메소드 선언 시 매개변수의 수를 모를 경우
```java
public class Dontknow {
    public static void main(String[] args) {
        int[] values = {1, 2, 3};
        dontknow(values);
        int value = 1;
        int value2 = 2;
        dontknow2(value, value2);
        dontknow2(values);
    }

    public static void dontknow(int[] values) {
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
    }

    public static void dontknow2(int... values) {
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
    }
}
```
    배열 변수 또는 ... 매개 변수를 통해 수를 예상 하지 못하는 상황에 대비할 수 있다.

### 12. 정적 멤버 ( 메소드 ) 선언
    메소드의 경우, 인스턴스 메소드르 선언할 것인가, 아니면 정적 메소드로 선언할 것인가의 판단 기준은
    '인스턴스 필드' 를 이용해서 실행해야 한다면 인스턴스 메소드,
    '인스턴스 필드' 를 이용하지 않는다면 정적 메소드로 선언
```java
public class Calculator{
    String color;                                       // 인스턴스 필드
    void setColor(String color){ this.color = color; }  // 인스턴스 메소드
    static int plus(int x, int y) { return x + y; }     // 정적 메소드
    static int minus(int x, int y) { return x - y; }    // 정적 메소드
}
```

### 13. 정적 블록
    정적 필드 선언 할 때 계산이 필요한 경우
```java
class Television {
    static String company = "SAMSUNG";
    static String model = "LCD";
    static String info;
    static String info2 = company + " - " + model; 

    static {
        info = company + " - " + model;
    }

    public static void main(String[] args) {
        System.out.println(info);   // 둘다 가틍ㄴ 결과
        System.out.println(info2);
    }
}
```

### 14. 어노테이션 타입 정의와 적용 ( ★ 다시 공부 )
    어노테이션 타입의 엘리먼트는 기본 데이터 타입, 열거 타입, Class 타입, 이들의 배열 타입을 사용
    엘리먼트의 default 값이 없을 경우 선언 시 반드시 기술
    기본 엘리먼트는 value
```java
public @interface Annotation {
    String value();
    String elementName1();
    int elementName2() default 5;
}
```
### 15. 어노테이션 적용 대상 ( ★ 다시 공부 )
    ElementType 열거 상수에 따른 적용 대상
    TYPE                :       클래스, 인터페이스, 열거 타입
    ANNOTATION_TYPE     :       어노테이션
    FIELD               :       필드
    CONSTRUCTOR         :       생성자
    METHOD              :       메소드
    LOCAL_VARIABLE      :       로컬변수
    PACKAGE             :       패키지

```java
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@interface Annotation2 {

}

@Annotation2
class testAnnotation2 {
    
    @Annotation2
    final int a = 1;
    
    //    @Annotation2       - 적용안됨
    public testAnnotation2() {

    }

    @Annotation2
    public void test2() {

    }
}
```
    위의 예시처럼 어노테이션이 적용될 대상을 한정할때는 @Target 어노테이션을 사용하여 정의
### 16. 어노테이션 유지 정책 ( ★ 다시 공부 )
    어노테이션 정의 시 사용 용도에 따라 어느 범위까지 유지할 것인지 지정해야함
    java.lang.annotation.RetentionPolicy 의 열거상수로 정의
    
    RetentionPolicy 열거 상수
        SOURCE : 소스상에서만 어노테이션 정보를 유지함, 소스 코드 분석때만 의미가 있으면 바이트 코드에는 남지않는다.
        CLASS : 바이트 코드 파일까지 정보를 유지한다. 하지만 리플렉션을 이용하여 정보를 얻을 수 없다.
        RUNTIME : 바이트 코드 파일까지 정보를 유지한다. 리플렉션을 이용하여 정보를 얻을 수 있다.
        
```java
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface Annotation3{
    
}
```

### 17. 런타임 시 어노테이션 정보 사용하기 ( ★ 다시 공부 )
    리플렉션을 이용해 어노테이션의 적용여부와 엘리먼트값을 읽을 수 있음.
    Field[]         getFields()             필드 정보를 Field 배열로 리턴
    Constructor[]   getConstuctors()        생성자 정보를 Constructor 배열로 리턴
    Method[]        getDeclaredMethods()    메소드 정보를 Method 배열로 리턴
    
    Class, Field, Constructor, Method가 가지고 있는 다음 메소드를 호출하여 어노테이션 정보 얻음
    리턴 타입           메소드명
    boolean         isAnnotationPresent( Class<? extends Annotation> annotationClass )
                    지정한 어노테이션이 적용되었는지 여부, Class 에서 호출했을 때 상위클래스에
                    적용된 경우에도 true 리턴
                   
    Annotation      getAnnotation( Class<T> annotationClass )
                    지정한 어노테이션이 적용되어 있으면 어노테이션을 리턴 else null
                    Class에서 호출했을 때 상위 클래스에 적용된 경우에도 어노테이션을 리턴
    
    Annotation[]    getAnnotations()
                    적용된 모든 어노티에션을 리턴, Class에서 호출했을 때 상위 클래스에
                    적용된 어노테이션도 모두 포함된다. 없을때는 길이가 0인 배열을 리턴
    
    Annotation[]    getDeclaredAnnotations()
                    직접 적용된 모든 어노테이션을 리턴한다. Class에서 호출했을 때
                    상위 클래스에 적용된 어노테이션은 포함하지 않는다.
```java
@Target(ElementType.METHOD) // 적용 범위 지정
@Retention(RetentionPolicy.RUNTIME) // 대부분 RUNTIME
public @interface PrintAnnotation { // 인터페이스 생성
    String value() default "-"; // String value default "-"
    int number() default 15;
}


class Service {

    @PrintAnnotation // default 값을 가지고 있는 PrintAnnotation 지정?
    public void method1() {
        System.out.println("example 1");
    }

    @PrintAnnotation("*") // value 값 * 로 초기화 시킨 PrintAnnotation 지정?
    public void method2() {
        System.out.println("example 2");
    }

    @PrintAnnotation(value = "#", number = 20) // value 값 # number 20 으로 초기화 시킨 PrintAnnotation 지정?
    public void method3() {
        System.out.println("example 3");
    }
}

class PrintAnnotationExample {
    public static void main(String[] args) {
        Method[] declaredMethods = Service.class.getDeclaredMethods(); // Method 정보를 리턴

        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(PrintAnnotation.class)) { // Annotation 이 지정되었다면
                PrintAnnotation printAnnotation = method.getAnnotation(PrintAnnotation.class); // Annotation 을 가져온다
                System.out.println(method.getName()); // method 이름을 출력한다
                for (int i = 0; i < printAnnotation.number(); i++) { // Annotation number 값을 가져온다
                    System.out.print(printAnnotation.value()); // Annotation value 값을 가져온다.
                }
                System.out.println();
                try {
                    method.invoke(new Service()); // 생성된 객체에 해당하는 method 를 호출한다
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

### 18. 상속 ( 오버라이딩 )
    규칙
        1. 부모의 메소드와 동일한 시그너처( 리턴 타입, 메소드 이름, 매개 변수 리스트 ) 를 가져야 한다.
        2. 접근 제한을 더 강하게 오버라이딩할 수 없다.
        3. 새로운 예외( Exception )를 throws 할 수 없다.
        
```java
public class Calculator {
    double areaCircle(double r) {
        System.out.println("Calculator 객체의 areaCircle 실행");
        return 3.14159 * r * r;
    }
}

class Computer extends Calculator {

    // 1. 리턴 타입 변경 : Error, 메소드 이름 변경 : 부모 메소드 호출, 매개 변수 리스트 변경 : 부모 메소드 호출 ( overloading )
    // 2. private 으로 접근 제한자 변경 : Error
    // 3. 메소드의 throws Exception 을 맞춰야 한다는 뜻같은데 RuntimeException 은 허용됨? 
    @Override
    double areaCircle(double r) {  
        System.out.println("Computer 객체의 areaCircle 실행");
        return Math.PI * r * r;
    }
}

class Main {
    public static void main(String[] args) {
        Computer computer = new Computer();
        System.out.println(computer.areaCircle(3));
    }
}
```
### 19. final 클래스
    클래스 선언 시 final 키워드를 붙이면 상속할 수 없는 클래스가 된다.
```java
public final class FinalClass {
}

class ChildClass extends FinalClass { // FinalClass 에러
    
}
```
    메소드 선언 시 final 키워드를 붙이면 Override 할수 없는 메소드가 된다.
```java
public class FinalClass {
    public final void p(){
        System.out.println("final");
    }
}

class ChildClass extends FinalClass{
    public void p() { // 에러 발생
        System.out.println("final child");
    }
}
```
### 20. 자동 타입 변환
    부모클래스 변수 = 자식클래스타입; // 이때 자식클래스 타입을 대입하여도 부모클래스로 자동 타입변환이 일어남
    
```java
// Cat 클래스가 Animal 클래스를 상속 받았다고 하자.

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
        Animal animal = cat; // 이때 animal 변수와 cat 변수는 같은 주소값을 가지고 있다.
        System.out.println(animal==cat); // true 동일한 Cat 객체를 가리키고 있다.
        cat.method3(); // 호출 가능
        animal.method3(); // 호출 불가능

        cat.method1(); // Cat 
        animal.method1(); // Cat
        Animal animal2 = new Animal();
        animal2.method1(); // Animal
    }
}
```
### 21. instanceof
    boolean result = 객체 instanceof 타입
    e.g) boolean isChild = parent instanceof Child
### 22. 추상클래스
    접근제한자 abstract class 클래스명 {}
    
    실체 간에 공통되는 특성을 추출하여 선언한 클래스
    new 로 객체를 생성할 수 없다.
    
    용도
        1. 실체 클래스들의 공통된 필드와 메소드의 이름을 통일할 목적
        2. 실체 클래스를 작성할 때 시간을 절약
        
        설계자가 코더들에게 설계 내용을 전달하면서 추상 클래스를 함께 전달
        코더는 전달받은 추상 클래스를 공통적으로 갖고 하위 클래스를 작성한다.
        
### 23. 추상메소드
    접근제한자(public || protect) abstract 리턴타입 메소드명 ( 매개변수 );
    직접 구현하지 않고 실체 클래스가 공통적으로 가져야할 기능들을 정의. 만 한다.
    실체클래스에 강제 구현
    
### 24. 인터페이스 사용 가능 Element
    상수 필드
        모든 필드는 컴파일 과정에서 상수 필드( final static ) 으로 변환됨
        선언과 동시에 초기화 시켜줘야함
    추상 메소드
        기본 메소드는 모두 추상 메소드 자동으로 ( public abstract ) 가 붙음
    디폴트 메소드
        메소드 앞에 default 선언 모두 public 접근자를 갖는다. 
    정적 메소드
        메소드 앞에 static 선언 모두 public 접근자를 갖는다.
        
```java
public interface RemoteController {
    int MAX_VOLUME = 10;
    int MIN_VOLUME = 0;
    
    void turnOn();
    void turnOff();
    void setVolume(int volume);
    
    default void setMute(boolean mute){
        if(mute){
            System.out.println("무음 처리 합니다.");
            return;
        }
        System.out.println("무음 해제합니다.");
    }
    
    static void changeBattery(){
        System.out.println("건전지를 교환 합니다.");
    }
}
```

### 25. 인터페이스 디폴트 메소드의 필요성
    인터페이스를 상속받은 클래스A가 있는데, 인터페이스의 기능을 확장 시켜야 할때
    메소드를 추가하면 클래스 A에서 컴파일 에러가 발생, 따라서 영향 없이 메소드 추가가
    가능하게 하기 위해 디폴트 메소드가 탄생함.
    
### 26. 중첩클래스 사용 이유
    두 클래스의 멤버들을 서로 쉽게 접근할 수 있다는 장점,
    외부에는 불필요한 관계 클래스를 감출수 있어 코드의 복잡성이 줄어든다는 장점
    
### 27. 중첩클래스 바이트 코드 파일
    중첩 클래스의 경우도 하나의 클래스이기 때문에 컴파일하면 별도로 바이트 코드 파일 생성
    멤버 클래스 : 바깥클래스 $ 멤버클래스 .class
    로컬 클래스 : 바깥클래스 $1 로컬클래스 .class
### 28. 로컬클래스에서 사용 제한
    로컬 클래스의 내부에서는 바깥 클래스의 필드나 메소드를 제한 없이 사용
    그러나 메소드안의 매개 변수나 로컬 변수를 로컬 클래스에서 사용할 때,
    로컬 클래스의 객체는 힙메모리에 존재하여 사용할 수 있지만 메소드의 매개변수나 로컬 변수는
    실행이 끝나면 사라진다. 따라서 로컬 클래스에서 매개변수나 로컬 변수값을 사용할 때는
    로컬 클래스 내부에 복사하여 사용 ( 자바 8부터는 자동으로 final 변수로 선언 )
```java
public class Outter {
    // 자바 8 이전 버전
    public void method1(final int arg) {
        final int localVariable = 1;

        class Inner {
            public void method() {
                int result = arg + localVariable;
            }
        }
    }

    // 자바 8 이후 버전
    public void method2(int arg) {
        int localVariable = 1;
        class Inner {
            public void method() {
                int result = arg + localVariable;
            }
        }
    }
}
```

### 29. 중첩 클래스에서 바깥 클래스 참조 얻기
    바깥 클래스의 참조를 얻을때는 바깥클래스.this.필드, 바깥클래스.this.메소드 로 호출할 수 있다.
```java
public class Outter {
    String field = "Outter-Field";

    void method() {
        System.out.println("Outter-Method");
    }

    class Nested {
        String field = "Nested-Field";

        void method() {
            System.out.println("Nested-Method");
        }

        void print() {
            System.out.println(this.field); // 자신의 필드
            this.method(); // 자신의 메소드
            System.out.println(Outter.this.field); // 바깥 클래스 필드
            Outter.this.method(); // 바깥 클래스 메소드
        }
    }

}
class Main6 {
    public static void main(String[] args) {
        Outter outter = new Outter();
        Outter.Nested nested = outter.new Nested();
        nested.print();
    }
}
```
### 30. 익명 객체 ( ★ 다시 공부 )
    이름 없는 객체를 말한다.
    단독으로 생성할 수 없고 클래스를 상속하거나 인터페이스를 구현해야만 생성할 수 있다.
    필드의 초기값이나 로컬변수의 초기값, 매개 변수의 매개값으로 주로 대입된다.
    UI 이벤트 처리 객체나 스레드 객체를 간편하게 생성할 목적으로 많이 활용된다.
    
    부모타입으로 선언, 자식 객체를 초기값으로 대입할 경우
    
    기본
```java
public class Parent {}
class Child extends Parent{}
class Another{
    Parent parent = new Child();
    void method(){
        Parent localParent = new Child();
    }
}
```
    그러나 자식 클래스가 "재사용되지 않고" 오로지 해당 필드와 변수의 초기값으로만 사용할 경우,
    익명 자식 객체를 생성하여 초기값으로 대입
```java
public class Parent {}
class Child extends Parent{}
class Another{
    Parent parent = new Parent(){
        int childField;
        void childMethod(){
            
        }
        @Override
        void parentMethod(){
            
        }
    };
}
```
    메소드의 매개 변수가 부모 타입일 경우 메소드 호출 코드에서 
    익명 자식 객체를 생성해서 매개값으로 대입할 수 있다.
```java
public class Parent {}
class Child extends Parent{}
class Another {
    void method1(Parent parent){
        
    }
    void method2(){
        method1(new Parent(){
           int childField;
           void childMethod(){}
           @Override
           void parentMethod(){}
        });
    }
}
```
    
### 31. 예외처리 중 try catch
    try catch 블록에서 return 문을 사용하더라도 finally 블록은 항상 실행된다.
### 32. hashCode();
    객체를 식별할 하나의 정수값, Object의 hashCode() 메소드는 객체의 메모리 번지를
    이용해서 해시코드를 만들어 리턴
### 33. clone()
    Cloneable 인터페이스를 상속하는 클래스의 clone() 메소드는 얕은 복사를 한다.
### 34. finalize()
    객체가 소멸(Garbage Collector가 실행) 될때 실행되는 메소드
### 35. Objects 클래스의 메소드
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>int</td>
        <td>compare(T a, T b, Comparator&lt;T&gt; c)</td>
        <td>두 객체 a와 b를 Comparator 를 사용하여 비교</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>deepEquals(Object a, Object b)</td>
        <td>두 객체의 깊은 비교( 배열의 항목까지 비교 )</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>equals(Object a, Object b)</td>
        <td>두 객체의 얕은 비교( 번지만 비교 )</td>
    </tr>
    <tr>
        <td>int</td>
        <td>hash(Object... values)</td>
        <td>매개값이 저장된 배열의 해시코드 생성</td>
    </tr>
    <tr>
        <td>int</td>
        <td>hashCode(Object o)</td>
        <td>객체의 해시코드 생성</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isNull(Object obj)</td>
        <td>객체가 null 인지 조사</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>nonNull(Object obj)</td>
        <td>객체가 null 이 아닌지 조사</td>
    </tr>
    <tr>
        <td>T</td>
        <td>requireNonNull(T obj)</td>
        <td>객체가 null 인 경우 예외 발생</td>
    </tr>
    <tr>
        <td>T</td>
        <td>requireNonNull(T obj, String message)</td>
        <td>객체가 null 인 경우 예외 발생( 주어진 예외 메시지 포함 )</td>
    </tr>
    <tr>
        <td>T</td>
        <td>requireNonnull(T obj, Supplier&lt;String&gt; messageSupplier)</td>
        <td>객체가 null 인 경우 예외 발생( 람다식이 만든 예외 메시지 포함 )</td>
    </tr>
    <tr>
        <td>String</td>
        <td>toString(Object o)</td>
        <td>객체의 toString() 리턴값 리턴</td>
    </tr>
    <tr>
        <td>String</td>
        <td>toString(Object o, String nullDefault)</td>
        <td>객체의 toString() 리턴값 리턴, 첫 번째 매개값이 null 일 경우 두 번째 매개값 리턴</td>
    </tr>
</table>

### 36. System 클래스
    exit()  :   JVM 종료
    gc()    :   Garbage Collector '가능한 빨리' 실행
    currentTimeMillies()    :   현재 시간을 1/1000 초로 계산
    nanoTime()    :   현재 시간을 1/1000000000 (10의 9승) 초로 계산
    getProperty()
        java.version    :   자바의 버전
        java.home   :   사용하는 JRE의 파일 경로
        os.name :   Operating system name
        file.separator  :   File separator ( "/" on UNIX )
        user.name   :   사용자의 이름
        user.home   :   사용자의 홈 디렉토리
        user.dir    :   사용자가 현재 작업 중인 디렉토리 경로
    getenv()    :   환경변수
### 37. Class 클래스
    getClass()  :   생성된 객체의 클래스값
    forName()   :   패키지가 포함된 이름으로 클래스 얻기
    newInstance()   :   기본생성자로 객체생성
        예외 처리   : InstantiationException - 추상클래스 또는 인터페이스일경우
                    IllegalAccessException - 생성자의 접근제한자 오류
### 38. reflection
    Class 객체를 이용하여 생성자, 필드, 메소드 정보 알아내기
    getDeclaredConstructors()   :    constructor 배열 리턴
    getDeclaredFields()   :    field 배열 리턴
    getDeclaredMethods()   :    method 배열 리턴
    
    getDeclared 메소드는 클래스에 선언된 멤버만 가져옴
    
    상속된 멤버까지 가져온다면
    getFields, getMethods 메소드 사용
    
### 39. String 클래스
    StringTokenizer 클래스는 문자로 구분, split() 은 정규표현식으로 구분
### 40. Pattern 클래스
    정규 표현식으로 문자열을 검증
### 41. Arrays 클래스
    배열 조작 기능
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드 이름</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>int</td>
        <td>binarySearch( 배열, 찾는값 )</td>
        <td>전체 배열 항목에서찾는 값이 있는 인덱스 리턴</td>
    </tr>
    <tr>
        <td>배열</td>
        <td>copy( 원본배열, 복사할 길이 )</td>
        <td>원본 배열의 0번 인덱스에서 복사할 길이 만큼 복사한 배열 리턴,<br>
        복사할 길이는 원본 배열의 길이보다 커도 됨</td>
    </tr>
    <tr>
        <td>배열</td>
        <td>copyOfRange( 원본배열, 시작인덱스, 끝인덱스 )</td>
        <td>원본 배열의 시작 인덱스에서 끝 인덱스까지 복사한 배열 리턴</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>deepEquals( 배열, 배열 )</td>
        <td>두 배열의 깊은 비교 ( 중첩 배열의 항목까지 비교 )</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>equals( 배열, 배열 )</td>
        <td>두 배열의 얕은 비교 ( 중첩 배열의 항목은 비교하지 않음 )</td>
    </tr>
    <tr>
        <td>void</td>
        <td>fill( 배열, 값 )</td>
        <td>전체 배열 항목에 동일한 값을 저장</td>
    </tr>
    <tr>
        <td>void</td>
        <td>fill( 배열, 시작인덱스, 끝인덱스, 값 )</td>
        <td>시작 인덱스부터 끝 인덱스까지의 항목에만 동일한 값을 저장</td>
    </tr>
    <tr>
        <td>void</td>
        <td>sort( 배열 )</td>
        <td>배열 전체 항목을 오름차순으로 정렬</td>
    </tr>
    <tr>
        <td>String</td>
        <td>toString( 배열 )</td>
        <td>"[값1, 값2, ... ]"와 같은 문자열 리턴</td>
    </tr>
</table>

### 42. Calendar 클래스
    getInstance() 메소드를 이용해여 현재 운영체제에 설정되어 있는
    시간대(TimeZone)를 기준으로 한 Calendar 하위 객체를 얻을 수 있음.
### 43. 멀티 스레드
    멀티 스레드는 하나의 프로세스 내부에 생성되기 때문에
    하나의 스레드가 예외를 발생시키면 프로세스 자체가 종료될 수 있다
### 44. 스레드의 이름
    생성한 스레드는 자동적으로 Thread-n 의 이름을 갖는다.
    이를 변경하려면 thread.setName("이름") 으로 변경 가능하다.
### 45. 현재 스레드
    Thread.currentThread()로 코드를 실행하는 현재 스레드의 참조를 얻을 수 있다.  
### 46. 스레드 우선순위
    멀티 스레드는 동시성(Concurrency) 또는 병렬성(Parallelism) 으로 실행된다.
    동시성은 멀티 작업을 위해 하나의 코어에서 멀티 스레드가 번갈아가며 실행,
    병렬성은 멀티 작업을 위해 멀티 코어에서 개별 스레드를 동시에 실행
    
    스레드 스케줄링
        우선순위(Priority), 순환할당(Round-Robin) 방식이 있음
        
        우선순위는 우선순위가 높은 스레드가 실행 상태를 더 많이 가지도록 스케줄링,
        순환할당은 시간 할당량을 정해서 하나의 스레드를 정해진 시간만큼 실행하도록 스케줄링
        
        우선순위는 개발자가 코드로 순위 번호를 제어할 수 있음, (setPriority(1 ~ 10) 숫자 높을수록 우선순위 높음)
        순환할당은 JVM에서 정하기 때문에 개발자가 제어할 수 없음.
### 47. 동기화 메소드
    공유 객체 사용
```java
public class MainThreadExample {
    public static void main(String[] args) {
        Calculator2 calculator2 = new Calculator2();

        User1 user1 = new User1();
        user1.setCalculator2(calculator2);
        user1.start();

        User2 user2 = new User2();
        user2.setCalculator2(calculator2);
        user2.start();
    }
}

class Calculator2 {
    private int memory;

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        //  실행 결과
        //  User2: 50
        //  User1: 50
        System.out.println(Thread.currentThread().getName() + ": " + this.memory);
    }
}

class User1 extends Thread {
    private Calculator2 calculator2;

    public void setCalculator2(Calculator2 calculator2) {
        this.setName("User1");
        this.calculator2 = calculator2;
    }

    @Override
    public void run() {
        calculator2.setMemory(100);
    }
}
class User2 extends Thread {
    private Calculator2 calculator2;

    public void setCalculator2(Calculator2 calculator2) {
        this.setName("User2");
        this.calculator2 = calculator2;
    }

    @Override
    public void run() {
        calculator2.setMemory(50);
    }
}
```
    동기화 메소드 사용
        스레드가 사용 중인 객체를 다른 스레드가 변경할 수 없도록 하려면 
        스레드 작업이 끝날 때까지 "객체에 잠금"을 걸어서 
        다른 스레드가 사용할 수 없도록 해야 한다. 
        
    임계 영역(critical section)
        멀티 스레드 프로그램에서 단 하나의 스레드만 실행할 수 있는 코드 영역
        
    동기화 메소드 정의 (synchronized)
```java
class Calculator2 {
    private int memory;

    public int getMemory() {
        return memory;
    }

    public synchronized void setMemory(int memory) {
        this.memory = memory;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        //  실행 결과
        //  User1: 100
        //  User2: 50
        System.out.println(Thread.currentThread().getName() + ": " + this.memory);
    }
}

// or

class Calculator2 {
    private int memory;

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        synchronized (this) {
            this.memory = memory;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ": " + this.memory);
        }
    }
}

```

### 48. 스레드 상태
    스레드 객체를 생성하고 start() 메소드를 실행 시키면
    실행대기 (Runnable) - cpu점유 - 실행 (Running) - ( 반복 ) - 일시 정지 (Wating) - 종료 (Terminated)
    로 진행된다. getState()로 현재 상태 확인 가능
<table>
    <tr>
        <th>상태</th>
        <th>열거 상수</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>객체 생성</td>
        <td>NEW</td>
        <td>스레드 객체가 생성, 아직 start() 메소드가 호출되지 않은 상태</td>
    </tr>
    <tr>
        <td>실행 대기</td>
        <td>RUNNABLE</td>
        <td>실행 상태로 언제든지 갈 수 있는 상태</td>
    </tr>
    <tr>
        <td rowspan="3">일시 정지</td>
        <td>WAITING</td>
        <td>다른 스레드가 통지할 때까지 기다리는 상태</td>
    </tr>
    <tr>
        <td>TIMED_WAITING</td>
        <td>주어진 시간 동안 기다리는 상태</td>
    </tr>
    <tr>
        <td>BLOCKED</td>
        <td>사용하고자 하는 객체의 락이 풀리 때까지 기다리는 상태</td>
    </tr>
    <tr>
        <td>종료</td>
        <td>TERMINATED</td>
        <td>실행을 마친 상태</td>
    </tr>
</table>

    NEW -> RUNNABLE -> TIMED_WAITING -> RUNNABLE -> TERMINATED

### 49. 스레드 상태 제어
<table style="width: 100%;">
    <tr>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td><b>interrupt()</b></td>
        <td>일시 정지 상태의 스레드에서 InterruptedException 예외를 발생시켜,<br>
        예외처리 코드(catch)에서 실행 대기 상태로 가거나종료 상태로 갈 수 있도록 한다.</td>
    </tr>
    <tr>
        <td><b>notify()
        notifyAll()</b></td>
        <td>동기화 블록 내에서 wait() 메소드에 의해 일시 정지 상태에 있는<br>
        스레드를 실행 대기 상태로 만든다.</td>
    </tr>
    <tr>
        <td><b>resume()</b></td>
        <td>suspend() 메소드에 의해 일시 정지 상태에 있는 스레드를 실행 대기 상태로 만든다.<br>
        -Deprecated ( 대신 notify(), notifyAll() 사용 )</td>
    </tr>
    <tr>
        <td><b>sleep(long millis)
        sleep(long millis, int nanos)</b></td>
        <td>주어진 시간 동안 스레드를 일시 정지 상태로 만든다. <br>
        주어진 시간이 지나면 자동적으로 실행 대기 상태가 된다.</td>
    </tr>
    <tr>
        <td><b>join()
        join(long millis)
        join(long millis, int nanos)</b></td>
        <td>join() 메소드를 호출한 스레드는 일시 정지 상태가 된다.
        실행 대기 상태로 가려면, join() 메소드를 멤버로 가지는 스레드가 종료되거나,
        매개값으로 주어진 시간이 지나야 한다.</td>
    </tr>
    <tr>
        <td><b>wait()
        wait(long millis)
        wait(long millis, int nanos)</b></td>
        <td>동기화(synchronized) 블록 내에서 스레드를 일시 정지 상태로 만든다.<br> 
        매개값으로 주어진 시간이 지나면 자동적으로 실행 대기 상태가 된다.<br>
        시간이 주어지지 않으면 notify(), notifyAll() 메소드에 의해<br>
        실행 대기 상태로 갈 수 있다.</td>
    </tr>
    <tr>
        <td><b>suspend()</b></td>
        <td>스레드를 일시 정지 상태로 만든다. <br>
        resume() 메소드를 호출하면 다시 실행 대기 상태가 된다.<br>
        -Deprecated ( 대신 wait() 사용 )</td>
    </tr>
    <tr>
        <td><b>yield()</b></td>
        <td>실행 중에 우선순위가 동일한 다른 스레드에게 실행을 양보하고 <br>
        실행 대기 상태가 된다.</td>
    </tr>
    <tr>
        <td><b>stop()</b></td>
        <td>스레드를 즉시 종료시킨다. -Deprecated</td>
    </tr>    
</table>
    
#### Sleep
```java
public class ThreadSleepExample {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("삡");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
        }
    }
}
```
#### Yield
```java
public class ThreadYieldExample {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        threadA.start();
        threadB.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        threadA.work = false;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        threadA.work = true;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        threadA.stop = true;
        threadB.stop = true;
    }
}

class ThreadA extends Thread {
    public boolean stop = false;
    public boolean work = true;

    @Override
    public void run() {
        while (!stop) {
            if (work) {
                System.out.println("ThreadA");
            } else {
                Thread.yield();
            }
        }
        System.out.println("ThreadA 종료");
    }
}

class ThreadB extends Thread {
    public boolean stop = false;
    public boolean work = true;

    @Override
    public void run() {
        while (!stop) {
            if (work) {
                System.out.println("ThreadB");
            } else {
                Thread.yield();
            }
        }
        System.out.println("ThreadB 종료");
    }
}
```
#### Join
```java
public class ThreadJoinExample {
    public static void main(String[] args) {
        SumThread sumThread = new SumThread();
        sumThread.start();

        try {
            sumThread.join(); // sumThread가 종료할 때까지 메인 스레드 정지
        } catch (InterruptedException e) {
        }
        System.out.println("합 : " + sumThread.getSum());
    }

}

class SumThread extends Thread {
    private long sum;

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
    }
}
```
#### Wait, Notify, NotifyAll
    두 스레드가 작업할 내용을 "동기화(synchronized) 메소드"로 구분
    한 메소드가 작업을 완료하면 notify() ( 다른 스레드를 실행 대기 상태로 만듬 )
    wait() ( 자신을 일시 정지 상태로 만듬 )
```java
public class WorkObject {
    public synchronized void methodA() {
        System.out.println("ThreadA의 methodA() 작업 실행");
        notify();
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }

    public synchronized void methodB() {
        System.out.println("ThreadB의 methodB() 작업 실행");
        notify();
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }
}

class ThreadA2 extends Thread {
    private WorkObject workObject;

    public ThreadA2(WorkObject workObject) {
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            workObject.methodA();
        }
    }
}

class ThreadB2 extends Thread {
    private WorkObject workObject;

    public ThreadB2(WorkObject workObject) {
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            workObject.methodB();

        }
    }
}

class WaitNotifyExample{
    public static void main(String[] args) {
        WorkObject sharedWorkObject = new WorkObject();
        ThreadA2 threadA2 = new ThreadA2(sharedWorkObject);
        ThreadB2 threadB2 = new ThreadB2(sharedWorkObject);

        threadA2.start();
        threadB2.start();
    }
}
```
```java
public class DataBox {
    private String data;

    public synchronized String getData() {
        if (this.data == null) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        String returnValue = data;
        System.out.println(returnValue);
        data = null;
        notify();
        return returnValue;
    }

    public synchronized void setData(String data) {
        if (this.data != null) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.data = data;
        System.out.println(data);
        notify();
    }
}

class ProducerThread extends Thread {
    private DataBox dataBox;

    public ProducerThread(DataBox dataBox) {
        this.dataBox = dataBox;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            String data = "Data-" + i;
            dataBox.setData(data);
        }
    }
}

class ConsumerThread extends Thread {
    private DataBox dataBox;

    public ConsumerThread(DataBox dataBox) {
        this.dataBox = dataBox;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            dataBox.getData();
        }
    }
}

class WaitNotifyExample2 {
    public static void main(String[] args) {
        DataBox dataBox = new DataBox();

        ProducerThread producerThread = new ProducerThread(dataBox);
        ConsumerThread consumerThread = new ConsumerThread(dataBox);

        producerThread.start();
        consumerThread.start();
    }
}
```

#### Stop, Interrupt
    stop() 메소드는 갑자기 종료하면 스레드가 사용하던 자원들이 불안정한 상태로 남기때문에
    deprecated 되었다. 스레드를 종료시키는 방법
    
    stop 플래그 사용
```java
public class StopFlagExample {
    public static void main(String[] args) {
        PrintThread1 printThread1 = new PrintThread1();
        printThread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        printThread1.setStop(true);
    }
}

class PrintThread1 extends Thread {
    private boolean stop;

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {
        while(!stop){
            System.out.println("실행 중");
        }
        System.out.println("자원 정리");
        System.out.println("실행 종료");
    }
}
```
    interrupt 메소드 사용
        스레드가 실행 대기 또는 실행 상태에 있을 때 interrupt() 메소드가
        실행되면 즉시 InterruptedException 예외가 발생하는 것이 아니라,
        스레드가 미래에 일시 정지 상태가 되면 예외가 발생하는 것이다.
```java
public class InterruptExample {
    public static void main(String[] args) {
        Thread thread = new PrintThread2();
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        thread.interrupt();
    }
}

class PrintThread2 extends Thread {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("실행 중");
                Thread.sleep(1); // interrupt 가 발생하면 예외발생
            }
        } catch (InterruptedException e) {

        }
        System.out.println("자원 정리");
        System.out.println("실행 종료");
    }
}
```
    interrupt 상태 확인
        Thread.interrupted();
        objThread.isInterrupted();
```java
class PrintThread2 extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("실행 중");
            if (Thread.interrupted()) {
                break;
            }
        }
        System.out.println("자원 정리");
        System.out.println("실행 종료");
    }
}
```
### 50. 데몬 스레드
    주 스레드의 작업을 돕는 보조적인 역할을 수행하는 스레드
    주 스레드의 작업이 종료되면 데몬 스레드도 강제적으로 종료
    e.g) 워드프로세스, 미디어 플레이어, JVM 등
    데몬 스레드로 사용할 스레드를 setDaemon 하면 등록됨
    + start() 메소드 실행전에 해야함 안그러면 InterruptedException 발생
```java
public class AutoSaveThread extends Thread {
    public void save() {
        System.out.println("작업 내용 저장");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
            save();
        }
    }
}

class DaemonExample {
    public static void main(String[] args) {
        AutoSaveThread autoSaveThread = new AutoSaveThread();
        autoSaveThread.setDaemon(true);
        autoSaveThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("메인 스레드 종료");
    }
}
```
### 51. 스레드 그룹
    스레드 그룹은 관련된 스레드를 묶어서 관리할 목적으로 이용
    JVM 이 실행되면 system 스레드 그룹을 만들고, 
    JVM 운영에 필요한 스레드들을 생성해서 system 그룹에 포함시킨다.
    main 스레드는 system 하위 그룹인 main 스레드 그룹에 포함됨.
    스레드 생성 시 스레드 그룹 명시하지 않으면 생성한 스레드가 속해있는 그룹에 포함.
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        String groupName = group.getName();
```java
public class ThreadInfoExample {
    public static void main(String[] args) {
        AutoSaveThread autoSaveThread = new AutoSaveThread();
        autoSaveThread.setName("AutoSaveThread");
        autoSaveThread.setDaemon(true);
        autoSaveThread.start();
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces(); // 프로세스에서 실행하는 모든 스레드의 정보
        for (Thread thread : map.keySet()) {
            System.out.println("Name: " + thread.getName()
                    + (thread.isDaemon() ? "(데몬)" : "주"));
            System.out.println("\t" + "소속그룹: " + thread.getThreadGroup().getName());
            System.out.println();
        }
    }
}
```
#### 생성
    ThreadGroup tg = new ThreadGroup(String name);
    ThreadGroup tg = new ThreadGroup(ThreadGroup parent, String name);
    부모 스레드 그룹 지정하지 않으면 현재 스레드 그룹의 하위 그룹으로 생성
#### 일괄 interrupt()
    스레드 그룹의 interrupt() 메소드는 포함된 모든 스레드의 interrupt() 메소드를 호출
<table>
    <tr>
        <th>리턴타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>int</td>
        <td>activeCount()</td>
        <td>현재 그룹 및 하위 그룹에서 활동 중인 모든 스레드의 수를 리턴</td>
    </tr>
    <tr>
        <td>int</td>
        <td>activeGroupCount()</td>
        <td>현재 그룹에서 활동 중인 모든 하위 그룹의 수를 리턴</td>
    </tr>
    <tr>
        <td>void</td>
        <td>checkAccess()</td>
        <td>현재 스레드가 스레드 그룹을 변경한 권한이 있는지 체크,
        없다면 SecurityException 발생</td>
    </tr>
    <tr>
        <td>void</td>
        <td>destroy()</td>
        <td>현재 그룹 및 하위 그룹을 삭제, 단 모든 스레드들이 종료 상태가 되어야 함</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isDestroyed()</td>
        <td>현재 그룹이 삭제되었는지 여부</td>
    </tr>
    <tr>
        <td>int</td>
        <td>getMaxPriority()</td>
        <td>현재 그룹에 포함된 스레드가 가질수 있는 최대 우선순위를 리턴</td>
    </tr>
    <tr>
        <td>void</td>
        <td>setMaxPriority(int priority)</td>
        <td>현재 그룹에 포함된 스레드가 가질수 있는 최대 우선순위를 변경</td>
    </tr>
    <tr>
        <td>String</td>
        <td>getName()</td>
        <td>현재 그룹의 이름을 리턴</td>
    </tr>
    <tr>
        <td>ThreadGroup</td>
        <td>getParent()</td>
        <td>현재 그룹의 부모 그룹을 리턴</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>parentOf(ThreadGroup tg)</td>
        <td>매개값의 부모인지 여부 리턴</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isDaemon()</td>
        <td>현재 그룹이 데몬 그룹인지 여부 리턴</td>
    </tr>
    <tr>
        <td>void</td>
        <td>setDaemon(boolean daemon)</td>
        <td>현재 그룹을 데몬 그룹으로 설정</td>
    </tr>
    <tr>
        <td>void</td>
        <td>list()</td>
        <td>현재 그룹에 포함된 스레드와 하위 그룹에 대한 정보를 출력</td>
    </tr>
    <tr>
        <td>void</td>
        <td>interrupt()</td>
        <td>현재 그룹에 포함된 모든 스레드들을 interrupt함</td>
    </tr>    
</table>

```java
public class WorkThread extends Thread {
    public WorkThread(ThreadGroup threadGroup, String threadName) {
        super(threadGroup, threadName);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted");
                break;
            }
        }
        System.out.println(getName() + " 종료됨");
    }
}

class ThreadGroupExample {
    public static void main(String[] args) {
        ThreadGroup myGroup = new ThreadGroup("myGroup");
        WorkThread workThread1 = new WorkThread(myGroup, "workThread1");
        WorkThread workThread2 = new WorkThread(myGroup, "workThread2");

        workThread1.start();
        workThread2.start();

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        mainGroup.list();
        System.out.println();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        myGroup.interrupt();
    }
}
```
### 52. 스레드풀
    스레드가 많이 생성되면 메모리사용량이 늘어나 어플리케이션 성능 저하
    스레드풀은 스레드를 제한된 개수만큼 정해놓고 작업 큐에 들어오는 작업들을
    하나씩 스레드가 맡아 처리, 작업 처리가 끝난 스레드는 다시 작업 큐에서 
    새로운 작업을 가져와 처리
    메인스레드의 데몬스레드가 아니기 때문에 메인스레드 종료에 영향받지 않음. 
    
#### 생성
    JAVA 에서 제공하는 ExecutorService 인터페이스, Executors 클래스 사용
    
    ExecutorService threadPool = ExecutorService.newCachedThreadPool();
    초기스레드 수 0, 코어스레드 수 0, 최대스레드 수 Integer.MAX_VALUE 
    작업이 생길때마다 새로운 스레드를 생성하여 작업 실행( 최대스레드 수 까지만 )
    사용하지 않는 스레드는 풀에서 제거
    
    ExecutorService threadPool = ExecutorService.newFixedThreadPool(nThreads);
    초기스레드 수 0, 코어스레드 수 nThreads, 최대스레드 수 nThreads
    작업이 생길때마다 새로운 스레드를 생성하여 작업 실행( 최대스레드 수 까지만 )
    사용하지 않는 스레드는 제거되지 않음.
    
    ExecutorService threadPool = new ThreadPoolExecutor(
        3,      // 코어 스레드 개수
        100,    // 최대 스레드 개수
        120L,   // 놀고 있는 시간
        TimeUnit.SECONDS,   // 놀고 있는 시간 단위
        new SynchronousQueue<Runnable>() // 작업 큐
    );

#### 종료
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>void</td>
        <td>shutdown()</td>
        <td>현재 처리 중인 작업뿐만 아니라 작업 큐에 대기하고 있는<br>
        모든 작업을 처리한 뒤에 스레드풀을 종료시킨다.</td>
    </tr>
    <tr>
        <td>List&lt;Runnable&gt;</td>
        <td>shutdownNow()</td>
        <td>현재 작업 처리 중인 스레드를 interrupt 해서<br>
        작업 중지를 시도하고 스레드풀을 종료시킨다.<br>
        리턴값은 작업 큐에 있는 미처리된 작업(Runnable)의 목록이다.</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>awaitTermination(long timeout, TimeUnit unit)</td>
        <td>showdown() 메소드 호출 이후, 모든 작업 처리를 timeout 시간 내에<br>
        완료하면 true 를 리턴하고, 완료하지 못하면 작업 처리 중인 스레드를<br>
        interrupt 하고 false 를 리턴한다.</td>
    </tr>
</table>

#### 작업 생성
    Runnable, Callable
    
    Runnable task = new Runnable(){
        public void run(){
            // 작업 내용
        }    
    }

    // 리턴값이 있음.    
    Callable<T> task = new Callable<T>(){
        public T call() throws Exception{
            // 작업 내용
            return T;
        }    
    }
    
#### 작업 처리 요청
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>void</td>
        <td>execute(Runnable command)</td>
        <td>- Runnable 을 작업 큐에 저장<br>
        - 작업 처리 결과를 받지 못함<br>
        - 예외 발생 시 스레드 종료, 스레드풀에서 제거</td>
    </tr>
    <tr>
        <td>Future&lt;?&gt;<br>
        Future&lt;V&gt;<br>
        Future&lt;V&gt;</td>
        <td>submit(Runnable task)<br>
        submit(Runnable task, V result)<br>
        submit(Callable&lt;V&gt; task</td>
        <td>- Runnable 또는 Callable을 작업 큐에 저장<br>
        - 리턴된 Future을 통해 작업 처리 결과를 얻을 수 있음<br>
        - 예외 발생 시 스레드 종료되지 않고 스레드풀에서 재사용</td>
    </tr>
</table>

```java
public class ExecuteExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                    int poolSize = threadPoolExecutor.getPoolSize();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("[총 스레드 개수: " + poolSize + "] 작업 스레드 이름: " + threadName);

                    //예외 발생
                    int value = Integer.parseInt("쓰리");
                }
            };
//            executorService.execute(runnable);
            executorService.submit(runnable);
            Thread.sleep(10);
        }
        executorService.shutdown();
    }
}
```

#### 블로킹 방식의 작업 완료 통보 ( ★ 다시 공부 )
    ExecutorService의 submit() 메소드는 매개값으로 준 Runnable 또는 Callable 작업을 
    스레드 풀의 작업 큐에 저장하고 즉시 Futrue 객체를 리턴
    리턴된 Future 객체는 작업 결과가 아닌 작업이 완료될 때까지 기다렸다가( 지연했다가=블로킹되었다가 )
    최종 결과를 얻는데 사용
    
    Future의 get() 메소드를 호출하면 스레드가 작업을 완료할 때까지 블로킹되었다가 작업을 완료하면
    처리 결과를 리턴한다.

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>V</td>
        <td>get()</td>
        <td>작업이 완료될 때까지 블로킹되었다가 처리 결과 V를 리턴</td>
    </tr>
    <tr>
        <td>V</td>
        <td>get(long timeout, TimeUnit unit)</td>
        <td>timeout 시간 전에 작업이 완료되면 결과 V를 리턴하지만, <br>
        작업이 완료되지 않으면 TimeoutException 을 발생시킴</td>
    </tr>
</table>

    블로킹 방식의 작업 완료 통보에서 주의할 점은 
    작업을 처리하는 스레드가 작업을 완료할 때까지 get() 메소드가 블로킹 되므로
    다른 코드를 실행할 수 없다.
    따라서 get() 메소드를 실행하는 작업을 또 다른 스레드가 처리해야 한다.
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>boolean</td>
        <td>cancel(boolean mayInterruptIfRunning)</td>
        <td>작업 처리가 진행중일 경우 취소시킴</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isCancelled()</td>
        <td>작업이 취소되었는지 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isDone()</td>
        <td>작업 처리가 완료되었는지 여부</td>
    </tr>
</table>

#### 리턴값이 없는 작업 완료 통보
    Runnable 객체 생성 후 submit(Runnable task) 메소드 사용
    여기서 리턴되는 Future 객체는 스레드가 작업 처리를 정상적으로 완료했는지,
    작업 처리 도중 예외가 발생했는지 확인을 위함

```java
public class NoResultExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        System.out.println("[ 작업 처리 요청 ]");
        Runnable runnable = () -> {
            int sum = 0;
            for (int i = 0; i <= 10; i++) {
                sum += i;
            }
            System.out.println("[ 처리 결과 ] " + sum);
        };
        Future future = executorService.submit(runnable);

        try {
            future.get();
            System.out.println("[ 작업 처리 완료 ]");
        } catch (Exception e) {
            System.out.println("[ 실행 예외 발생 ] " + e.getMessage());
        }
        executorService.shutdown();
    }
}
```
#### 리턴값이 있는 작업 완료 통보
    Callable 객체 생성 후 submit(Callable task) 메소드 사용
    Callable 객체 생성 시 return 타입을 명시

```java
public class ResultByCallableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        System.out.println("[ 작업 처리 요청 ]");
        Callable<Integer> task = () -> {
            int sum = 0;
            for (int i = 0; i <= 10; i++) {
                sum += i;
            }
            return sum;
        };
        Future<Integer> future = executorService.submit(task);

        try {
            int sum = future.get();
            System.out.println("[ 작업 처리 완료 ] " + sum);
        } catch (Exception e) {
            System.out.println("[ 실행 예외 발생 ] " + e.getMessage());
        }
        executorService.shutdown();
    }
}
```
#### 작업 처리 결과를 외부 객체에 저장 ( ★ 다시 공부 )
    두 개 이상 스레드가 내놓은 결과값을 한 객체에 저장
    Runnable 객체 생성 후 submit(Runnable task, V result) 메소드 사용
    result 객체가 공유 객체가 되어, 두개 이상의 스레드 작업을 취합 

```java
public class ResultByRunnableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        System.out.println("[ 작업 처리 요청 ]");

        Result result = new Result();
        Runnable task1 = new Task(result);
        Runnable task2 = new Task(result);

        Future<Result> future1 = executorService.submit(task1, result);
        Future<Result> future2 = executorService.submit(task2, result);

        try {
            result = future1.get();
            result = future2.get();
            System.out.println("[ 처리 결과 ] " + result.accumValue);
            System.out.println("[ 작업 처리 완료 ]");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[ 실행 예외 발생 ] " + e.getMessage());
        }
        executorService.shutdown();
    }
}

class Task implements Runnable {
    Result result;

    Task(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i <= 10; i++) {
            sum += i;
        }
        result.addValue(sum);
    }
}

class Result {
    int accumValue;

    synchronized void addValue(int value) {
        accumValue += value;
    }
}
```

#### 작업 완료 순으로 통보 ( ★ 다시 공부 )
    스레드풀에서 작업 처리가 완료된 것만 통보받는 방법
    
    CompletionService를 이용
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Future&lt;V&gt;</td>
        <td>poll()</td>
        <td>완료된 작업의 Future 가져옴<br>
        완료된 작업이 없다면 null 리턴</td>
    </tr>
    <tr>
        <td>Future&lt;V&gt;</td>
        <td>poll(long timeout,<br>
        TimeUnit unit)</td>
        <td>완료된 작업의 Future 가져옴<br>
        완료된 작업이 없다면 timeout 까지 블로킹</td>
    </tr>
    <tr>
        <td>Future&lt;V&gt;</td>
        <td>take()</td>
        <td>완료된 작업의 Future 가져옴<br>
        완료된 작업이 없다면 있을 때까지 블로킹</td>
    </tr>
    <tr>
        <td>Future&lt;V&gt;</td>
        <td>submit(Callable&lt;V&gt; task)</td>
        <td>스레드 풀에 Callable 작업 처리 요청</td>
    </tr>
    <tr>
        <td>Future&lt;V&gt;</td>
        <td>submit(Runnable task,<br>
        V result)</td>
        <td>스레드 풀에 Runnable 작업 처리 요청</td>
    </tr>
</table>

```java
public class CompletionServiceExample extends Thread {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        CompletionService<Integer> completionService =
                new ExecutorCompletionService<>(executorService);

        System.out.println("[ 작업 처리 요청 ]");
        for (int i = 0; i < 3; i++) {
            completionService.submit(() -> {
                int sum = 0;
                for (int i1 = 1; i1 <= 10; i1++) {
                    sum += i1;
                }
                return sum;
            });
        }

        System.out.println("[ 처리 완료된 작업 확인 ]");
        executorService.submit(() -> {
            while (true) {
                try {
                    Future<Integer> future = completionService.take();
                    int value = future.get();
                    System.out.println("[ 처리 결과 ] " + value);
                } catch (Exception e) {
                    break;
                }
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        executorService.shutdown();
    }
}
```

#### 콜백 방식의 작업 완료 통보 ( ★ 다시 공부 )
    콜백이란 스레드에게 작업 처리를 요청한 후, 
    스레드가 작업을 완료하면 특정 메소드를 자동 실행하는 기법
    
    블로킹 방식은 작업 처리 이후 스레드 작업이 완료될때까지 기다려야 하지만
    콜백 방식은 스레드 작업이 완료된 이후 자동으로 콜백 메소드를 실행하기 때문에
    기다릴 필요없음.
    
```java
public class CallbackExample {
    private ExecutorService executorService;

    public CallbackExample() {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    private CompletionHandler<Integer, Void> callback =
            new CompletionHandler<Integer, Void>() {
                @Override
                public void completed(Integer result, Void attachment) {
                    System.out.println("completed() 실행: " + result);
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    System.out.println("failed() 실행: " + exc.toString());
                }
            };

    public void doWork(final String x, final String y) {
        Runnable task = () -> {
            try {
                int intX = Integer.parseInt(x);
                int intY = Integer.parseInt(y);
                int result = intX + intY;
                callback.completed(result, null);
            } catch (Exception e) {
                callback.failed(e, null);
            }
        };

        executorService.submit(task);
    }

    public void finish(){
        executorService.shutdown();
    }

    public static void main(String[] args) {
        CallbackExample example = new CallbackExample();
        example.doWork("3","2");
        example.doWork("삼","이");
        example.finish();
    }
}
``` 

### 53. 제네릭
    컴파일시 강한 타입 체크 가능
    타입 변환(casting)을 제거
    
```java
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
```

### 54. 제네릭 메소드
    접근자 <타입파라미터> 리턴타입 메소드명(파라미터)
    
```java
public class Util {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        boolean keyCompare = p1.getKey().equals(p2.getKey());
        boolean valueCompare = p1.getValue().equals(p2.getValue());
        return keyCompare && valueCompare;
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

class CompareMethodExample {
    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(1, "사과");
        Pair<Integer, String> p2 = new Pair<>(1, "사과");
        boolean result1 = Util.<Integer,String>compare(p1, p2);

        Pair<String, String> p3 = new Pair<>("user1", "홍길동");
        Pair<String, String> p4 = new Pair<>("user2", "홍길동");
        boolean result2 = Util.compare(p3, p4);
    }
}
```

### 55. 제한된 타입 파라미터 ( <T extends 최상위타입> )
    타입 파라미터에 지정되는 구체적인 타입을 제한
    
```java
public class Util2 {
    public static <T extends Number> int compare(T t1, T t2) {
        double v1 = t1.doubleValue();
        double v2 = t2.doubleValue();
        return Double.compare(v1, v2);
    }
}

class BoundedTypeParameterExample {
    public static void main(String[] args) {
//        int result1 = Util2.compare("a","b"); // Number 타입이 아니어서 에러

        int result1 = Util2.compare(10, 20);
        System.out.println(result1);

        int result2 = Util2.compare(4.5, 3);
        System.out.println(result2);
    }
}
```

### 56. 와일드카드 타입 ( <?>, <? extends ...>, <? super ...>)
    코드에서 ?를 일반적으로 와일드 카드라고 부름
    
- 제네릭타입 <?> : Unbounded Wildcards ( 제한 없음 )
    - 타입 파라미터를 대치하는 구체적인 타입으로 모든 클래스나 인터페이스 타입이 올 수 있다.

- 제네릭타입 <? extends 상위타입> : Upper Bounded Wildcards ( 상위 클래스 제한 )
    - 타입 파라미터를 대치하는 구체적인 타입으로 상위 타입이나 하위 타입만 올 수 있다.

- 제네릭타입 <? super 하위타입> : Lower Bounded Wildcards ( 하위 클래스 제한 )
    - 타입 파라미터를 대치하는 구체적인 타입으로 하위 탕비이나 상위 타입이 올 수 있다.
    
```java
public class Course<T> {
    private String name;
    private T[] students;

    public Course(String name, int capacity) {
        this.name = name;
        students = (T[]) new Object[capacity]; // T[] 형태로 생성 못함
    }

    public String getName() {
        return name;
    }

    public T[] getStudents() {
        return students;
    }

    public void add(T t) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = t;
                break;
            }
        }
    }
}

class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }
}

class Worker extends Person {

    public Worker(String name) {
        super(name);
    }
}

class Student extends Person {

    public Student(String name) {
        super(name);
    }
}

class HighStudent extends Student {

    public HighStudent(String name) {
        super(name);
    }
}

class WildCardExample {
    public static void registerCourse(Course<?> course) {
        System.out.println(course.getName() + " 수강생: " + Arrays.toString(course.getStudents()));
    }

    public static void registerCourseStudent(Course<? extends Student> course) {
        System.out.println(course.getName() + " 수강생: " + Arrays.toString(course.getStudents()));
    }

    public static void registerCourseWorker(Course<? super Worker> course) {
        System.out.println(course.getName() + " 수강생: " + Arrays.toString(course.getStudents()));
    }

    public static void main(String[] args) {
        Course<Person> personCourse = new Course<>("일반인과정", 5);
        personCourse.add(new Person("일반인"));
        personCourse.add(new Worker("직장인"));
        personCourse.add(new Student("학생"));
        personCourse.add(new HighStudent("고등학생"));

        Course<Worker> workerCourse = new Course<>("직장인과정",5);
        workerCourse.add(new Worker("직장인"));

        Course<Student> studentCourse = new Course<>("학생과정",5);
        studentCourse.add(new Student("학생"));
        studentCourse.add(new HighStudent("고등학생"));

        Course<HighStudent> highStudentCourse = new Course<>("고등학생과정",5);
        highStudentCourse.add(new HighStudent("고등학생"));

        registerCourse(personCourse);
        registerCourse(workerCourse);
        registerCourse(studentCourse);
        registerCourse(highStudentCourse);
        System.out.println();

//        registerCourseStudent(personCourse); // 불가
//        registerCourseStudent(workerCourse); // 불가
        registerCourseStudent(studentCourse);
        registerCourseStudent(highStudentCourse);

        registerCourseWorker(personCourse);
        registerCourseWorker(workerCourse);
//        registerCourseWorker(studentCourse); // 불가
//        registerCourseWorker(highStudentCourse); // 불가
    }
}
```

### 57. 제네릭 타입의 상속과 구현
#### class
```java
class ParentProduct<T, M> {
    private T kind;
    private M model;

    public T getKind() {
        return kind;
    }

    public M getModel() {
        return model;
    }

    public void setKind(T kind) {
        this.kind = kind;
    }

    public void setModel(M model) {
        this.model = model;
    }
}

class ChildProduct<T, M, C> extends ParentProduct<T, M> {
    private C company;

    public C getCompany() {
        return company;
    }

    public void setCompany(C company) {
        this.company = company;
    }
}
```
#### interface
```java
interface Storage<T> {
    public void add(T item, int index);

    public T get(int index);
}

class StorageImpl<T> implements Storage<T> {
    private T[] array;

    public StorageImpl(int capacity) {
        this.array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T item, int index) {
        array[index] = item;
    }

    @Override
    public T get(int index) {
        return array[index];
    }
} 
```
#### use
```java
public class ChildProductAndStorageExample {
    public static void main(String[] args) {
        ChildProduct<Tv, String, String> product = new ChildProduct<>();
        product.setKind(new Tv());
        product.setModel("SmartTv");
        product.setCompany("SAMSUNG");

        Storage<Tv> storage = new StorageImpl<>(100);
        storage.add(new Tv(), 0);
        Tv tv = storage.get(0);
    }
}
```
### 58. 람다식
    함수형 프로그래밍의 사용
    형태는 매개 변수를 가진 코드 블록이지만, 런타임 시에는 익명 구현 객체를 생성한다.
    람다식은 단순히 메소드를 선언하는 것이 아니라 이 메소드를 가지고 있는 객체를 생성해 낸다.
    인터페이스의 익명 구현 객체를 생성한다.
    람다식은 대입될 인터페이스의 종류에 따라 작성 방법이 달라지기 때문에
    람다식이 대입될 인터페이스를 람다식의 "타겟타입" 이라고 한다.
    
    해석 : 람다식을 사용하면 대입되는 인터페이스 타입을 구현하는 객체를 익명으로 생성하고
          override 되는 메소드를 구현하는 내용을 담는다. ?
    
    따라서 하나의 추상 메소드를 포함하는 인터페이스만 람다식으로 구현할 수 있다.
    매개값이 없는 람다식은 소괄호 포함해야 함
    
```java
public class MyFunctionalInterfaceExample {
    public static void main(String[] args) {
        MyFunctionalInterface fi;
        fi = () ->{
            String str = "method call";
            System.out.println(str);
        };
        fi.method();
        
        fi = ()-> {System.out.println("method call2");};
        fi.method();
        
        fi =() -> System.out.println("method call3");
        fi.method();
    }
}

@FunctionalInterface
interface MyFunctionalInterface{
    public void method();
}
```
```java
public class MyFunctionalInterfaceExample {
    public static void main(String[] args) {
        MyFunctionalInterface fi;
        fi = (x) -> {
            int result = x * 5;
            System.out.println(result);
        };
        fi.method(5);

        fi = (x) -> {
            System.out.println(x * 5);
        };
        fi.method(5);

        fi = x -> System.out.println(x * 5);
        fi.method(5);
    }
}

@FunctionalInterface
interface MyFunctionalInterface {
    public void method(int x);
}
```

#### 람다식 this
    람다식에서 this는 람다식을 실행한 객체 참조

```java
@FunctionalInterface
public interface MyFunctionalInterface2 {
    public void method();
}

class UsingThis {
    public int outterField = 10;

    class Inner {
        int innerField = 20;

        void method() {
            MyFunctionalInterface2 fi = () -> {
                System.out.println("outField: " + outterField);
                System.out.println("outField: " + UsingThis.this.outterField + "\n");

                System.out.println("innerField: " + innerField);
                System.out.println("innerField: " + this.innerField + "\n");
            };
            fi.method();
        }
    }
}

class UsingThisExample{
    public static void main(String[] args) {
        UsingThis usingThis = new UsingThis();
        UsingThis.Inner inner = usingThis.new Inner();
        inner.method();
    }
}
```

#### 람다식 로컬변수 사용
    익명 객체의 로컬 변수 사용시 변수는 final 특성을 가져야 한다.
```java
@FunctionalInterface
public interface MyFunctionInterface3 {
    public void method();
}

class UsingLocalVariable {
    void method(int arg) {
        int localVar = 40;
        arg = 31;
        localVar = 41;
        MyFunctionInterface3 fi = () -> {
            System.out.println("arg: " + arg); // error
            System.out.println("localVar: " + localVar); // error
        };
        fi.method();
    }
}
```
#### 람다식 표준 API
<table>
    <tr>
        <th>종류</th>
        <th>특징</th>
    </tr>
    <tr>
        <td>Consumer</td>
        <td>- 매개값은 있고, 리턴값은 없음</td>
    </tr>
    <tr>
        <td>Supplier</td>
        <td>- 매개값은 없고, 리턴값은 있음</td>
    </tr>
    <tr>
        <td>Function</td>
        <td>- 매개값도 있고, 리턴값도 있음<br>
        - 주로 매개값을 리턴값으로 매핑( 타입 변환 )</td>
    </tr>
    <tr>
        <td>Operator</td>
        <td>- 매개값도 있고, 리턴값도 있음<br>
        - 주로 매개값을 연산하고 결과를 리턴</td>
    </tr>
    <tr>
        <td>Predicate</td>
        <td>- 매개값은 있고, 리턴 타입은 boolean<br>
        - 매개값을 조사해서 true/false를 리턴</td>
    </tr>
</table>

##### Consumer ( accept() )
<table>
    <tr>
        <th>인터페이스명</th>
        <th>추상 메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Consumer&lt;T&gt;</td>
        <td>void accept(T t)</td>
        <td>객체 T를 받아 소비</td>
    </tr>
    <tr>
        <td>BiConsumer&lt;T, U&gt;</td>
        <td>void accept(T t, U u)</td>
        <td>객체 T와 U를 받아 소비</td>
    </tr>
    <tr>
        <td>DoubleConsumer</td>
        <td>void accept(double value)</td>
        <td>double 값을 받아 소비</td>
    </tr>
    <tr>
        <td>IntConsumer</td>
        <td>void accept(int value)</td>
        <td>int 값을 받아 소비</td>
    </tr>
    <tr>
        <td>LongConsumer</td>
        <td>void accept(long value)</td>
        <td>long 값을 받아 소비</td>
    </tr>
    <tr>
        <td>ObjDoubleConsumer&lt;T&gt;</td>
        <td>void accept(T, t, double value)</td>
        <td>객체 T와 double 값을 받아 소비</td>
    </tr>
    <tr>
        <td>ObjIntConsumer&lt;T&gt;</td>
        <td>void accept(T, t, int value)</td>
        <td>객체 T와 int 값을 받아 소비</td>
    </tr>
    <tr>
        <td>ObjLongConsumer&lt;T&gt;</td>
        <td>void accept(T, t, long value)</td>
        <td>객체 T와 long 값을 받아 소비</td>
    </tr>
</table>

```java
public class ConsumerExample {
    public static void main(String[] args) {
        Consumer<String> consumer = t -> System.out.println(t + "8");
        consumer.accept("java");

        BiConsumer<String, String> biConsumer = (t, u) -> System.out.println(t + u);
        biConsumer.accept("Java", "8");

        DoubleConsumer doubleConsumer = (d) -> System.out.println("Java" + d);
        doubleConsumer.accept(8.0);

        ObjIntConsumer<String> objIntConsumer = (t, i) -> System.out.println(t + i);
        objIntConsumer.accept("Java", 8);
    }
}
```
##### Supplier ( get() )
<table>
    <tr>
        <th>인터페이스명</th>
        <th>추상 메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Supplier&lt;T&gt;</td>
        <td>T get()</td>
        <td>T 객체를 리턴</td>
    </tr>
    <tr>
        <td>BooleanSupplier</td>
        <td>boolean getAsBoolean()</td>
        <td>boolean 값을 리턴</td>
    </tr>
    <tr>
        <td>DoubleSupplier</td>
        <td>double getAsDouble()</td>
        <td>double 값을 리턴</td>
    </tr>
    <tr>
        <td>IntSupplier</td>
        <td>int getAsInt()</td>
        <td>int 값을 리턴</td>
    </tr>
    <tr>
        <td>LongSupplier</td>
        <td>long getAsLong()</td>
        <td>long 값을 리턴</td>
    </tr>
</table>

```java
public class SupplierExample {
    public static void main(String[] args) {
        IntSupplier intSupplier = () -> {
            int num = (int) (Math.random() * 6) + 1;
            return num;
        };
        System.out.println(intSupplier.getAsInt());
    }
}
```

##### Function ( apply() )
<table>
    <tr>
        <th>인터페이스명</th>
        <th>추상 메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Function&lt;T, R&gt;</td>
        <td>R apply(T t)</td>
        <td>객체 T를 객체 R로 매핑</td>
    </tr>
    <tr>
        <td>Function&lt;T, U, R&gt;</td>
        <td>R apply(T t, U u)</td>
        <td>객체 T와 U를 객체 R로 매핑</td>
    </tr>
    <tr>
        <td>DoubleFunction&lt;R&gt;</td>
        <td>R apply(double value)</td>
        <td>double 을 객체 R로 매핑</td>
    </tr>
    <tr>
        <td>IntFunction&lt;R&gt;</td>
        <td>R apply(int value)</td>
        <td>int 를 객체 R로 매핑</td>
    </tr>
    <tr>
        <td>IntToLongFunction</td>
        <td>long applyAsLong(int value)</td>
        <td>int 를 long 으로 매핑</td>
    </tr>
    <tr>
        <td>LongToDoubleFunction</td>
        <td>double applyAsDouble(long value)</td>
        <td>long 을 double 로 매핑</td>
    </tr>
    <tr>
        <td>LongToIntFunction</td>
        <td>int applyAsInt(long value)</td>
        <td>long 을 int 로 매핑</td>
    </tr>
    <tr>
        <td>ToDoubleBiFunction</td>
        <td>double applyAsDouble(int value)</td>
        <td>int 를 double 로 매핑</td>
    </tr>
    <tr>
        <td>IntToDoubleFunction</td>
        <td>double applyAsDouble(int value)</td>
        <td>int 를 double 로 매핑</td>
    </tr>
    <tr>
        <td>ToDoubleBiFunction&lt;T, U&gt;</td>
        <td>double applyAsDouble(T t, U u)</td>
        <td>객체 T와 U를 double 로 매핑</td>
    </tr>    
    <tr>
        <td>ToDoubleFunction&lt;T&gt;</td>
        <td>double applyAsDouble(T t)</td>
        <td>객체 T를 double로 매핑</td>
    </tr> 
    <tr>
        <td>ToIntBiFunction&lt;T, U&gt;</td>
        <td>int applyAsInt(T t, U u)</td>
        <td>객체 T와 U를 int 로 매핑</td>
    </tr>    
    <tr>
        <td>ToIntFunction&lt;T&gt;</td>
        <td>int applyAsInt(T t)</td>
        <td>객체 T를 int 로 매핑</td>
    </tr> 
    <tr>
        <td>ToLongBiFunction&lt;T, U&gt;</td>
        <td>long applyAsLong(T t, U u)</td>
        <td>객체 T와 U를 long 으로 매핑</td>
    </tr>    
    <tr>
        <td>ToLongFunction&lt;T&gt;</td>
        <td>long applyAsLong(T t)</td>
        <td>객체 T를 long 으로 매핑</td>
    </tr>
</table>

```java
public class FunctionExample {
    private static List<Student3> list = Arrays.asList(
            new Student3("홍길동", 90, 96),
            new Student3("놀부", 99, 98)
    );

    public static void printString(Function<Student3, String> function) {
        for (Student3 student3 : list) {
            System.out.print(function.apply(student3) + " ");
        }
        System.out.println();
    }

    public static void printInt(ToIntFunction<Student3> function) {
        for (Student3 student3 : list) {
            System.out.print(function.applyAsInt(student3) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("[ 학생 이름 ]");
        printString(t -> t.getName());

        System.out.println("[ 영어 점수 ]");
        printInt(t -> t.getEnglishScore());

        System.out.println("[ 수학 점수 ]");
        printInt(t -> t.getMathScore());
    }
}

class Student3 {
    private String name;
    private int englishScore;
    private int mathScore;

    public Student3(String name, int englishScore, int mathScore) {
        this.name = name;
        this.englishScore = englishScore;
        this.mathScore = mathScore;
    }

    public String getName() {
        return name;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public int getMathScore() {
        return mathScore;
    }
}
```
```java
public class FunctionExample2 {
    private static List<Student3> list = Arrays.asList(
            new Student3("홍길동", 90, 96),
            new Student3("놀부", 99, 98)
    );

    public static double avg(ToIntFunction<Student3> function) {
        int sum = 0;
        for (Student3 student3 : list) {
            sum += function.applyAsInt(student3);
        }
        return sum / list.size();
    }

    public static void main(String[] args) {
        double englishAvg = avg(s -> s.getEnglishScore());
        System.out.println("영어 평균 점수: " + englishAvg);
        double mathAvg = avg(s -> s.getMathScore());
        System.out.println("수학 평균 점수: " + mathAvg);
    }
}
```

##### Operating ( apply() )
<table>
    <tr>
        <th>인터페이스명</th>
        <th>추상 메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>BinaryOperator&lt;T&gt;</td>
        <td>T apply(T t, T t)</td>
        <td>T와 T를 연산한 후 T를 리턴</td>
    </tr>
    <tr>
        <td>UnaryOperator&lt;T&gt;</td>
        <td>T apply(T t)</td>
        <td>T를 연산한 후 T 리턴</td>
    </tr>
    <tr>
        <td>DoubleBinaryOperator</td>
        <td>double applyAsDouble(double, double)</td>
        <td>두 개의 double 연산</td>
    </tr>
    <tr>
        <td>DoubleUnaryOperator</td>
        <td>double applyAsDouble(double)</td>
        <td>한 개의 double 연산</td>
    </tr>
    <tr>
        <td>IntBinaryOperator</td>
        <td>int applyAsInt(int, int)</td>
        <td>두 개의 int 연산</td>
    </tr>
    <tr>
        <td>IntUnaryOperator</td>
        <td>int applyAsInt(int)</td>
        <td>한 개의 int 연산</td>
    </tr>
    <tr>
        <td>LongBinaryOperator</td>
        <td>long applyAsLong(long, long)</td>
        <td>두 개의 long 연산</td>
    </tr>
    <tr>
        <td>LongUnaryOperator</td>
        <td>long applyAsLong(long)</td>
        <td>한 개의 long 연산</td>
    </tr>
</table>

```java
public class OperatorExample {
    private static int[] scores = {92, 95, 87};

    public static int maxOrMin(IntBinaryOperator operator) {
        int result = scores[0];
        for (int score : scores) {
            result = operator.applyAsInt(result, score);
        }
        return result;
    }

    public static void main(String[] args) {
        int max = maxOrMin((a, b) -> {
            if (a >= b) {
                return a;
            } else {
                return b;
            }
        });
        System.out.println("최대값: " + max);

        int min = maxOrMin((a, b) -> {
            if (a <= b) {
                return a;
            } else {
                return b;
            }
        });
        System.out.println("최소값: " + min);
    }
}
```
##### Predicate ( test() )
<table>
    <tr>
        <th>인터페이스명</th>
        <th>추상 메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Predicate&lt;T&gt;</td>
        <td>boolean test(T t)</td>
        <td>객체 T를 조사</td>
    </tr>
    <tr>
        <td>BiPredicate&lt;T, U&gt;</td>
        <td>boolean test(T t, U u)</td>
        <td>객체 T와 객체 U를 조사</td>
    </tr>
    <tr>
        <td>DoublePredicate</td>
        <td>boolean test(double)</td>
        <td>double 값을 조사</td>
    </tr>
    <tr>
        <td>IntPredicate</td>
        <td>boolean test(int)</td>
        <td>int 값을 조사</td>
    </tr>
    <tr>
        <td>LongPredicate</td>
        <td>boolean test(long)</td>
        <td>long 값을 조사</td>
    </tr>
</table>

```java
public class PredicateExample {
    private static List<Student4> list = Arrays.asList(
            new Student4("홍길동", "남자", 90),
            new Student4("김순희", "여자", 90),
            new Student4("감자바", "남자", 95),
            new Student4("박한나", "여자", 92)
    );

    public static double avg(Predicate<Student4> predicate) {
        int count = 0, sum = 0;
        for (Student4 student4 : list) {
            if (predicate.test(student4)) {
                count++;
                sum += student4.getScore();
            }
        }
        return (double) sum / count;
    }

    public static void main(String[] args) {
        double maleAvg = avg(t -> t.getSex().equals("남자"));
        System.out.println("남자 평균 점수: " + maleAvg);

        double femaleAvg = avg(t -> t.getSex().equals("여자"));
        System.out.println("여자 평균 점수: " + femaleAvg);
    }
}

class Student4 {
    private String name;
    private String sex;
    private int score;

    public Student4(String name, String sex, int score) {
        this.name = name;
        this.sex = sex;
        this.score = score;
    }

    public String getSex() {
        return sex;
    }

    public int getScore() {
        return score;
    }
}
```

##### andThen(), compose()
    인터페이스AB = 인터페이스A.andThen(인터페이스B);
    인터페이스 A를 처리하고 결과를 인터페이스 B의 매개값으로 제공 인터페이스 B를 처리하고 결과 리턴
    
    인터페이스AB = 인터페이스A.compose(인터페이스B);
    인터페이스 B를 처리하고 결과를 인터페이스 A의 매개값으로 제공 인터페이스 A를 처리하고 결과 리턴

<table>
    <tr>
        <th>종류</th>
        <th>함수적 인터페이스</th>
        <th>andThen()</th>
        <th>compose()</th>
    </tr>
    <tr>
        <td rowspan="5">Consumer</td>
        <td>Consumer&lt;T&gt;</td>
        <td>O</td>
        <td></td>
    </tr>
    <tr>
        <td>BiConsumer&lt;T, U&gt;</td>
        <td>O</td>
        <td></td>
    </tr>
    <tr>
        <td>DoubleConsumer</td>
        <td>O</td>
        <td></td>
    </tr>
    <tr>
        <td>IntConsumer</td>
        <td>O</td>
        <td></td>
    </tr>
    <tr>
        <td>LongConsumer</td>
        <td>O</td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="2">Function</td>
        <td>Function&lt;T, R&gt;</td>
        <td>O</td>
        <td>O</td>
    </tr>
    <tr>
        <td>BiFunction&lt;T, U, R&gt;</td>
        <td>O</td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="4">Operator</td>
        <td>BinaryOperator&lt;T&gt;</td>
        <td>O</td>
        <td></td>
    </tr>
    <tr>
        <td>DoubleUnaryOperator</td>
        <td>O</td>
        <td>O</td>
    </tr>
    <tr>
        <td>IntUnaryOperator</td>
        <td>O</td>
        <td>O</td>
    </tr>
    <tr>
        <td>LongUnaryOperator</td>
        <td>O</td>
        <td>O</td>
    </tr>
</table>

###### Consumer andThen()
```java
public class ConsumerAndThenExample {
    public static void main(String[] args) {
        Consumer<Member2> consumerA = member2 -> System.out.println("consumerA: " + member2.getName());
        Consumer<Member2> consumerB = member2 -> System.out.println("consumerB: " + member2.getId());
        
        Consumer<Member2> consumerAB = consumerA.andThen(consumerB);
        consumerAB.accept(new Member2("홍길동","hong",null));
    }
    
}

class Member2 {
    private String name;
    private String id;
    private Address address;

    public Member2(String name, String id, Address address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }
}

class Address {
    private String country;
    private String city;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
```
###### Function 의 andThen() compose()
    위의 Consumer는 소비형태로 리턴값이 없으므로 넘겨줄 매개값이 없지만
    Function과 Opertor는 넘겨줄 매개값을 다음 실행할 인터페이스에 넘겨줌
    밑의 예제에서 function A 에서 B로 넘겨줄 매개값은 Address 타입,
    B에서 Address 타입을 넘겨받아 String 으로 리턴하는 함수를 실행
```java
public class FunctionAndThenComposeExample {
    public static void main(String[] args) {
        Function<Member2, Address> functionA;
        Function<Address, String> functionB;
        Function<Member2, String> functionAB;
        String city;
        functionA = (m) -> m.getAddress();
        functionB = (a) -> a.getCity();

        functionAB = functionA.andThen(functionB);
        city = functionAB.apply(new Member2("홍길동", "hong", new Address("한국", "서울")));
        System.out.println("거주 도시: " + city);

        functionAB = functionB.compose(functionA);
        city = functionAB.apply(new Member2("홍길동", "hong", new Address("한국", "서울")));
        System.out.println("거주 도시: " + city);
    }
}
```

###### Predicate and(), or(), negate()
```java
public class PredicateAndOrNegateExample {
    public static void main(String[] args) {

        // 2의 배수
        IntPredicate predicateA = a -> a % 2 == 0;

        // 3의 배수
        IntPredicate predicateB = a -> a % 3 == 0;

        IntPredicate predicateAB;
        boolean result;

        // and()
        predicateAB = predicateA.and(predicateB);
        result = predicateAB.test(9);
        System.out.println("9는 2와 3의 배수입니까? " + result);

        // or()
        predicateAB = predicateA.or(predicateB);
        result = predicateAB.test(9);
        System.out.println("9는 2 또는 3의 배수입니까? " + result);

        // negate()
        predicateAB = predicateA.negate();
        result = predicateAB.test(9);
        System.out.println("9는 는 홀수입니까? " + result);
    }
}
```
###### Predicate isEqual()
    isEqual() 정적 메소드는 Objects.equals(sourceObject, targetObject)의 리턴값을
    얻어 새로운 Predicate<T>를 생성한다.
    이 생성된 Predicate 의 test는 Objecs 클래스의 equals() 메소드가 구현되어있음.? 
    
    구현되어있는 isEqual()메소드
    
    static <T> Predicate<T> isEqual(Object targetRef) {
        return null == targetRef ? Objects::isNull : (object) -> {
            return targetRef.equals(object);
        };
    }
```java
public class PredicateIsEqualExample {
    public static void main(String[] args) {
        Predicate<String> predicate;

        predicate = Predicate.isEqual(null); // predicate 의 test()는 null의 값과 equals 한지 확인하는 Predicate
        System.out.println("null, null: " + predicate.test(null));

        predicate = Predicate.isEqual("Java8");
        System.out.println("null, Java8: " + predicate.test(null));

        predicate = Predicate.isEqual("null");
        System.out.println("Java8, null: " + predicate.test("Java8"));

        predicate = Predicate.isEqual("Java8");
        System.out.println("Java8, Java8: " + predicate.test("Java8"));

        predicate = Predicate.isEqual("Java8");
        System.out.println("Java7, Java8: " + predicate.test("Java7"));

    }
}
```

###### BinaryOperator<T> minBy(), maxBy()
```java
public class OperatorMinByMaxByExample {
    public static void main(String[] args) {
        BinaryOperator<Fruit> binaryOperator;
        Fruit fruit;

        binaryOperator = BinaryOperator.minBy((f1, f2) -> Integer.compare(f1.price, f2.price));
        fruit = binaryOperator.apply(new Fruit("딸기", 6000), new Fruit("수박", 10000));
        System.out.println(fruit.name);

        binaryOperator = BinaryOperator.maxBy((f1, f2) -> Integer.compare(f1.price, f2.price));
        fruit = binaryOperator.apply(new Fruit("딸기", 6000), new Fruit("수박", 10000));
        System.out.println(fruit.name);
    }
}

class Fruit {
    public String name;
    public int price;

    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
```

#### 람다식 메소드 참조
    기존 메소드를 호출만 할 경우 메소드 참조를 이용하여 코드를 줄일 수 있음
    (left, right) -> Math.max(left, right);
    Math :: max;
    
    e.g ) IntBinaryOperator operator = Math :: max;
    
    정적 메소드일 경우
    클래스 :: 메소드
    
    인스턴스 메소드일 경우
    참조변수 :: 메소드
    
```java
public class MethodReferencesExample {
    public static void main(String[] args) {
        IntBinaryOperator operator;

        operator = (x, y) -> Calculator3.staticMethod(x, y);
        System.out.println("결과1: " + operator.applyAsInt(1, 2));

        operator = Calculator3 :: staticMethod;
        System.out.println("결과2: " + operator.applyAsInt(1, 2));

        Calculator3 calculator3 = new Calculator3();

        operator = (x, y) -> calculator3.instanceMethod(x, y);
        System.out.println("결과3: " + operator.applyAsInt(1, 2));

        operator = calculator3::instanceMethod;
        System.out.println("결과4: " + operator.applyAsInt(1, 2));
    }

}

class Calculator3 {
    public static int staticMethod(int x, int y) {
        return x + y;
    }

    public int instanceMethod(int x, int y) {
        return x + y;
    }
}
```
#### 람다식 매개변수의 메소드 참조
    람다식에서 제공되는 a 매개 변수의 메소드(instanceMethod) 에 b 매개 변수를 매개값으로 사용하여 실행
    (a, b) -> { a.instanceMethod(b) }
    클래스 :: instanceMethod
    
```java
public class ArgumentMethodReferenceExample {
    public static void main(String[] args) {
        ToIntBiFunction<String, String> function;

        function = (a, b) -> a.compareToIgnoreCase(b);
        print(function.applyAsInt("Java8", "JAVA8"));

        function = String::compareToIgnoreCase;
        print(function.applyAsInt("Java8", "JAVA8"));
    }

    static void print(int order) {
        if (order < 0) {
            System.out.println("사전순으로 먼저 옵니다.");
            return;
        }
        if (order == 0) {
            System.out.println("동일한 문자열");
            return;
        }
        System.out.println("사전순으로 나중에 옵니다.");
    }
}
```
#### 람다식 생성자 참조
    (a, b) -> { return new 클래스(a, b) }
    클래스 :: new

```java
public class ConstructorReferencesExample {
    public static void main(String[] args) {
        Function<String, Member3> function1 = Member3::new;
        Member3 member1 = function1.apply("angel");

        BiFunction<String, String, Member3> function2 = Member3::new;
        Member3 member2 = function2.apply("신천사", "angel");

        Supplier<Member3> supplier1 = Member3::new;
        Member3 member3 = supplier1.get();
    }
}

class Member3 {
    String name;
    String id;

    public Member3() {
        System.out.println("Member() 실행");
    }

    public Member3(String id) {
        this.id = id;
        System.out.println("Member(String id) 실행");
    }


    public Member3(String name, String id) {
        this.name = name;
        this.id = id;
        System.out.println("Member(String name, String id) 실행");
    }

    public String getId() {
        return id;
    }
}
```
### 59. 컬렉션
    List, Set, Map : 인터페이스
    List - ArrayList 클래스
         - Vector 클래스
         - LinkedList 클래스
    Set - HashSet 클래스
        - TreeSet 클래스
    Map - HashMap 클래스
        - Hashtable 클래스
        - TreeMap 클래스
        - Properties 클래스
        
    Collection 인터페이스는 List, Set 의 객체를 추가 삭제 검색하는 방법의 공통점을 정의

#### List
<table>
    <tr>
        <th>기능</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td rowspan="3">객체 추가</td>
        <td>boolean add(E e)</td>
        <td>주어진 객체를 맨 끝에 추가</td>
    </tr>
    <tr>
        <td>void add(int index, E e)</td>
        <td>주어진 인덱스에 객체를 추가</td>
    </tr>
    <tr>
        <td>E set(E e)</td>
        <td>주어진 인덱스에 저장된 객체를 바꿈</td>
    </tr>
    <tr>
        <td rowspan="4">객체 검색</td>
        <td>boolean contains(Object o)</td>
        <td>주어진 객체가 저장되어 있는지 여부</td>
    </tr>
    <tr>
        <td>E get(int index)</td>
        <td>주어진 인덱스에 저장된 객체를 리턴</td>
    </tr>
    <tr>
        <td>boolean isEmpty()</td>
        <td>컬렉션이 비어 있는지 조사</td>
    </tr>
    <tr>
        <td>int size()</td>
        <td>저장되어 있는 전체 객체 수를 리턴</td>
    </tr>
    <tr>
        <td rowspan="3">객체 삭제</td>
        <td>void clear()</td>
        <td>저장된 모든 객체를 삭제</td>
    </tr>
    <tr>
        <td>E remove(int index)</td>
        <td>주어진 인덱스에 저장된 객체를 삭제</td>
    </tr>
    <tr>
        <td>boolean remove(Object o)</td>
        <td>주어진 객체를 삭제</td>
    </tr>
</table>

###### ArrayList Vector LinkedList
    
    Vector 와 ArrayList 다른 점
    동기화된 메소드로 구성되어 있어서 멀티 스레드가 동시에 실행할 수 없음.
    멀티 스레드 환경에서 안전하게 객체를 추가, 삭제할 수 있음
    
    LinkedList 와 ArrayList 다른 점
    ArrayList는 내부 배열에 객체를 저장해서 인덱스로 관리하지만, 
    LinkedList는 인접 참조를 링크해서 체인처럼 관리
    따라서 빈번한 객체 삭제와 삽입이 일어나는 곳에서는 ArrayList 보다 LinkedList가
    더 좋은 성능을 발휘한다.

<table>
    <tr>
        <th>구분</th>
        <th>순차적으로 추가/삭제</th>
        <th>중간에 추가/삭제</th>
        <th>검색</th>
    </tr>
    <tr>
        <td>ArrayList</td>
        <td>빠르다</td>
        <td>느리다</td>
        <td>빠르다</td>
    </tr>
    <tr>
        <td>LinkedList</td>
        <td>느리다</td>
        <td>빠르다</td>
        <td>느리다</td>
    </tr>
</table>

```java
public class LinkedListExample {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new LinkedList<>();

        long startTime;
        long endTime;

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            // 0인데스에 해당값 넣음
            list1.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();
        System.out.println("ArrayList: "+(endTime-startTime)); // ArrayList: 39861200

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            // 0인데스에 해당값 넣음
            list2.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();
        System.out.println("LinkedList: "+(endTime-startTime)); // LinkedList: 9541800
    }
}
```

#### Set
<table>
    <tr>
        <th>기능</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>객체 추가</td>
        <td>boolean add(E e)</td>
        <td>주어진 객체를 저장, 객체가 성공적으로 저장되면 true<br>
        중복 객체면 false 리턴</td>
    </tr>
    <tr>
        <td rowspan="4">객체 검색</td>
        <td>boolean contains(Object o)</td>
        <td>주어진 객체가 저장되어 있는지 여부</td>
    </tr>
    <tr>
        <td>boolean isEmpty()</td>
        <td>컬렉션이 비어 있는지 조사</td>
    </tr>
    <tr>
        <td>Iterator&lt;E&gt;</td>
        <td>저장된 객체를 한 번씩 가져오는 반복자 리턴</td>
    </tr>
    <tr>
        <td>int size()</td>
        <td>저장되어 있는 전체 객체 수를 리턴</td>
    </tr>
    <tr>
        <td rowspan="2">객체 삭제</td>
        <td>void clear()</td>
        <td>저장된 모든 객체를 삭제</td>
    </tr>
    <tr>
        <td>boolean remove(Object o)</td>
        <td>주어진 객체를 삭제</td>
    </tr>
</table>

    Set 에는 인덱스로 객체를 검색하는 메소드가 없음. 따라서 Iterator를 제공
    반복자는 Iterator 인터페이스를 구현한 객체를 말하는데, iterator() 메소드로 호출
    Set<String> set = ...;
    Iterator<String> iterator = set.iterator();
    
###### Iterator 인터페이스 메소드
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드명</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>boolean</td>
        <td>hasNext()</td>
        <td>가져올 객체가 있으면 true 를 리턴하고 없으면 false 를 리턴한다</td>
    </tr>
    <tr>
        <td>E</td>
        <td>next()</td>
        <td>컬렉션에서 하나의 객체를 가져온다.</td>
    </tr>
    <tr>
        <td>void</td>
        <td>remove()</td>
        <td>Set 컬렉션에서 객체를 제거한다.</td>
    </tr>
</table>

###### HashSet
    HashSet이 판단하는 동일한 객체란 hashCode() 메소드의 값이 같고 equals() 메소드의
    값이 모두 같을 때 같은 객체로 판단하고 중복 저장하지 않는다.
    
    동일한 필드값이면 중복없이 저장하는 HashSet
    
```java
public class HashSetExample2 {
    public static void main(String[] args) {
        Set<Member4> set = new HashSet<>();
        set.add(new Member4("홍길동",30));
        set.add(new Member4("홍길동",30));
        System.out.println("총 객체 수: "+set.size());
    }
}

class Member4 {
    public String name;
    public int age;

    public Member4(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member4) {
            Member4 member4 = (Member4) obj;
            return member4.name.equals(name) && member4.age == age;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode() + age;
    }
}
```

#### Map
    
<table>
    <tr>
        <th>기능</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>객체 추가</td>
        <td>V put(K key, V value)</td>
        <td>주어진 키로 값을 저장, 새로운 키일 경우 null 을 리턴하고<br>
        동일한 키가 있을 경우 값을 대체하고 이전 값을 리턴</td>
    </tr>
    <tr>
        <td rowspan="8">객체 검색</td>
        <td>boolean containsKey(Object key)</td>
        <td>주어진 키가 있는지 여부</td>
    </tr>
    <tr>
        <td>boolean containValue(Object value)</td>
        <td>주어진 값이 있는지 여부</td>
    </tr>
    <tr>
        <td>Set&lt;Map.Entry&lt;K, V&gt;&gt; entrySet()</td>
        <td>저장된 객체를 한 번씩 가져오는 반복자 리턴</td>
    </tr>
    <tr>
        <td>V get(Object key)</td>
        <td>주어진 키가 있는 값을 리턴</td>
    </tr>
    <tr>
        <td>boolean isEmpty()</td>
        <td>컬렉션이 비어 있는지 여부</td>
    </tr>
    <tr>
        <td>Set&lt;K&gt; keySet()</td>
        <td>모든 키를 Set 객체에 담아 리턴</td>
    </tr>
    <tr>
        <td>int size()</td>
        <td>저장된 키의 총 수를 리턴</td>
    </tr>
    <tr>
        <td>Collection&lt;V&gt; values()</td>
        <td>저장된 모든 값을 Collection에 담아서 리턴</td>
    </tr>
    <tr>
        <td rowspan="2">객체 삭제</td>
        <td>void clear()</td>
        <td>모든 Map.Entry( 키와 값 )를 삭제</td>
    </tr>
    <tr>
        <td>V remove(Object key)</td>
        <td>주어진 키와 일치하는 Map.Entry 를 삭제하고 값을 리턴</td>
    </tr>
</table>

###### HashMap
    HashMap 의 키로 사용할 객체는 hashCode() 메소드의 값과
    equals() 메소드의 값이 같을 때 사용 가능하다.

```java
public class HashMapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 85);
        map.put("B", 90);
        map.put("C", 80);
        map.put("A", 95);
        System.out.println("총 Entry 수: " + map.size());

        System.out.println("\tA : " + map.get("A"));
        System.out.println();

        Set<String> keySet = map.keySet();
        Iterator<String> keyIterator = keySet.iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            Integer value = map.get(key);
            System.out.println("\t" + key + ": " + value);
        }
        System.out.println();

        map.remove("A");
        System.out.println("총 Entry 수: " + map.size());

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, Integer>> entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Integer> entry = entryIterator.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("\t" + key + " : " + value);
        }
        System.out.println();

        map.clear();
        System.out.println("Entry 수: " + map.size());
    }
}
```

###### Hashtable
    HashMap 과 다른점은 동기화된 메소드로 구성되어 있기 때문에
    멀티 스레드가 동시에 이 메소드들을 실행할 수 없다.
    
###### Properties
    Hashtable 의 하위 클래스
    Hashtable 과 차이점은 키와 값을 String 으로 제한
    .properties 파일에서 주로 사용되며
    Properties 파일은 애플리케이션의 정보, 데이터베이스 정보, 국제화 정보가 저장된다.
    Property 파일을 읽기 위해서는 Properties 객체를 생성하고 load를 호출하면됨
    Properties properties = new Properties();
    properties.load(new FileReader("C:/~/database.properties"));
    
    주로 클래스와 함께 저장( 같은 패키지 ) 된 프로퍼티 파일의 경로를 알아낼 때는
    Class 의 getResource() 메소드를 통해 알아낸다.
    
    String path = 클래스.class.getResource("database.properties").getPath();
    path = URLDecoder.decode(path, "utf-8");
    Properties properties = new Properties();
    properties.load(new FileReader(path));
    
```java
public class PropertiesExample {
    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        String path = PropertiesExample.class.getResource("database.properties").getPath();
        path = URLDecoder.decode(path,"utf-8");
        properties.load(new FileReader(path));

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        System.out.println(driver);
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
    }
}
```

#### 검색 기능을 강화시킨 컬렉션

###### TreeSet
    이진 트리를 기반으로 한 Set 컬렉션,
    하나의 노드는 노드값인 value 와 왼ㅉ족과 오른쪽 자식 노드를 참조하기 위한 두개의 변수로 구성

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>E</td>
        <td>first()</td>
        <td>제일 낮은 객체를 리턴</td>
    </tr>
    <tr>
        <td>E</td>
        <td>last()</td>
        <td>제일 높은 객체를 리턴</td>
    </tr>
    <tr>
        <td>E</td>
        <td>lower(E e)</td>
        <td>주어진 객체 보다 바로 아래 객체를 리턴</td>
    </tr>
    <tr>
        <td>E</td>
        <td>higher(E e)</td>
        <td>주어진 객체보다 바로 위 객체를 리턴</td>
    </tr>
    <tr>
        <td>E</td>
        <td>floor(E e)</td>
        <td>주어진 객체와 동등한 객체가 있으면 리턴,<br>
        만약 없다면 주어진 객체의 바로 아래의 객체를 리턴</td>
    </tr>
    <tr>
        <td>E</td>
        <td>ceiling(E e)</td>
        <td>주어진 객체와 동등한 객체가 있으면 리턴,<br>
        만약 없다면 주어진 객체의 바로 위의 객체를 리턴</td>
    </tr>
    <tr>
        <td>E</td>
        <td>pollFirst()</td>
        <td>제일 낮은 객체를 꺼내오고 컬렉션에서 제거함</td>
    </tr>
    <tr>
        <td>E</td>
        <td>pollLast()</td>
        <td>제일 높은 객체를 꺼내오고 컬렉션에서 제거함</td>
    </tr>
</table>

```java
public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<Integer> scores = new TreeSet<>();
        scores.add(new Integer(87));
        scores.add(new Integer(98));
        scores.add(new Integer(75));
        scores.add(new Integer(95));
        scores.add(new Integer(80));
        Integer score = null;

        score = scores.first();
        System.out.println("가장 낮은 점수: " + score);

        score = scores.last();
        System.out.println("가장 높은 점수: " + score);

        score = scores.lower(new Integer(95));
        System.out.println("95점 아래 점수: " + score);

        score = scores.higher(new Integer(95));
        System.out.println("95점 위 점수: " + score);

        score = scores.floor(new Integer(95));
        System.out.println("95점 이거나 아래 점수: " + score);

        score = scores.ceiling(new Integer(85));
        System.out.println("85점 이거나 위 점수: " + score);


        while (!scores.isEmpty()) {
            score = scores.pollFirst();
            System.out.println(score + " (남은 객체 수: " + scores.size() + ")");
        }
    }
}
```
<table>
    <tr>
        <th>리턴타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Iterator&lt;E&gt;</td>
        <td>descendingIterator()</td>
        <td>내림차순으로 정렬된 Iterator 를 리턴</td>
    </tr>
    <tr>
        <td>NavigableSet&lt;E&gt;</td>
        <td>descendingSet()</td>
        <td>내림자순으로 정렬된 NavigableSet 을 반환</td>
    </tr>
</table>

```java
public class TreeSetExample2 {
    public static void main(String[] args) {
        TreeSet<Integer> scores = new TreeSet<>();
        scores.add(new Integer(87));
        scores.add(new Integer(98));
        scores.add(new Integer(75));
        scores.add(new Integer(95));
        scores.add(new Integer(80));

        NavigableSet<Integer> descendingSet = scores.descendingSet();
        for (Integer score : descendingSet) {
            System.out.print(score + " ");
        }
        System.out.println();

        NavigableSet<Integer> ascendingSet = descendingSet.descendingSet();
        for (Integer score : ascendingSet) {
            System.out.print(score + " ");
        }
        System.out.println();
    }
}
```
<table>
    <tr>
        <th>리턴타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>NavigableSet&lt;E&gt;</td>
        <td>headSet(E toElement,<br>
         boolean inclusive)</td>
        <td>주어진 객체보다 낮은 객체들을 NavigableSet 으로 리턴,<br>
        주어진 객체 포함 여부는 두 번째 매개값에 따라 달라짐</td>
    </tr>
    <tr>
        <td>NavigableSet&lt;E&gt;</td>
        <td>tailSet(E fromElement,<br>
        boolean inclusive)</td>
        <td>주어진 객체보다 높은 객체들을 NavigableSet 으로 리턴,<br>
        주어진 객체 포함 여부는 두 번째 매개값에 따라 달라짐</td>
    </tr>
    <tr>
        <td>NavigableSet&lt;E&gt;</td>
        <td>subSet(E fromElement,<br>
        boolean fromInclusive,<br>
        E toElement,<br>
        boolean toInclusive)</td>
        <td>시작과 끝으로 주어진 객체 사이의 객체들을 NavigableSet 으로 리턴,<br>
        시작과 끝 객체의 포함 여부는 두 번째, 네 번째 매개값에 따라 달라짐</td>
    </tr>
</table>

```java
public class TreeSetExample3 {
    public static void main(String[] args) {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("apple");
        treeSet.add("forever");
        treeSet.add("description");
        treeSet.add("ever");
        treeSet.add("zoo");
        treeSet.add("base");
        treeSet.add("guess");
        treeSet.add("cherry");

        System.out.println("[ c ~ f 사이의 단어 검색 ]");
        NavigableSet<String> rangeSet = treeSet.subSet("c", true, "f", true);
        for (String word : rangeSet) {
            System.out.println(word);
        }
    }
}
```

###### TreeMap
    이진 트리를 기반으로 한 Map 컬렉션,
    TreeSet 과의 차이점은 value에 Map.Entry 를 저장한다는 점
    정렬은 키값을 기준으로 함
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Map.Entry&lt;K, V&gt;</td>
        <td>firstEntry()</td>
        <td>제일 낮은 Map.Entry 를 리턴</td>
    </tr>
    <tr>
        <td>Map.Entry&lt;K, V&gt;</td>
        <td>lastEntry()</td>
        <td>제일 높은 Map.Entry 를 리턴</td>
    </tr>
    <tr>
        <td>Map.Entry&lt;K, V&gt;</td>
        <td>lowerEntry(K key)</td>
        <td>주어진 키보다 바로 아래 Map.Entry 를 리턴</td>
    </tr>
    <tr>
        <td>Map.Entry&lt;K, V&gt;</td>
        <td>higherEntry(K key)</td>
        <td>주어진 키보다 바로 위 Map.Entry 를 리턴</td>
    </tr>
    <tr>
        <td>Map.Entry&lt;K, V&gt;</td>
        <td>floorEntry(K key)</td>
        <td>주어진 키와 동등한 키가 있으면 해당 Map.Entry 를 리턴,<br>
        만약 없다면 주어진 키의 바로 아래의 Map.Entry 를 리턴</td>
    </tr>
    <tr>
        <td>Map.Entry&lt;K, V&gt;</td>
        <td>ceilingEntry(K key)</td>
        <td>주어진 키와 동등한 키가 있으면 해당 Map.Entry 리턴,<br>
        만약 없다면 주어진 키의 바로 위의 Map.Entry 를 리턴</td>
    </tr>
    <tr>
        <td>E</td>
        <td>pollFirstEntry()</td>
        <td>제일 낮은 Map.Entry 를 꺼내오고 컬렉션에서 제거함</td>
    </tr>
    <tr>
        <td>Map.Entry&lt;K, V&gt;</td>
        <td>pollLastEntry()</td>
        <td>제일 높은 Map.Entry 를 꺼내오고 컬렉션에서 제거함</td>
    </tr>
</table>

```java
public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<Integer, String> scores = new TreeMap<>();
        scores.put(new Integer(87), "홍길동");
        scores.put(new Integer(98), "이동수");
        scores.put(new Integer(75), "박길순");
        scores.put(new Integer(95), "신용권");
        scores.put(new Integer(80), "김자바");

        Map.Entry<Integer, String> entry = null;

        entry = scores.firstEntry();
        System.out.println("가장 낮은 점수: " + entry.getKey() + "-" + entry.getValue());

        entry = scores.lastEntry();
        System.out.println("가장 높은 점수: " + entry.getKey() + "-" + entry.getValue());

        entry = scores.lowerEntry(new Integer(95));
        System.out.println("95점 아래 점수: " + entry.getKey() + "-" + entry.getValue());

        entry = scores.higherEntry(new Integer(95));
        System.out.println("95점 위 점수: " + entry.getKey() + "-" + entry.getValue());

        entry = scores.floorEntry(new Integer(95));
        System.out.println("95점 이거나 아래 점수: " + entry.getKey() + "-" + entry.getValue());

        entry = scores.ceilingEntry(new Integer(85));
        System.out.println("85점 이거나 위 점수: " + entry.getKey() + "-" + entry.getValue());


        while (!scores.isEmpty()) {
            entry = scores.pollFirstEntry();
            System.out.println(entry.getKey() + "-" + entry.getValue() + " (남은 객체 수: " + scores.size() + ")");
        }
    }
}
```
<table>
    <tr>
        <th>리턴타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>NavigableSet&lt;K&gt;</td>
        <td>descendingKeySet()</td>
        <td>내림차순으로 정렬된 키의 NavigableSet 을 리턴</td>
    </tr>
    <tr>
        <td>NavigableMap&lt;K, V&gt;</td>
        <td>descendingMap()</td>
        <td>내림차순으로 정렬된 Map.Entry 의 NavigableMap 을 리턴</td>
    </tr>
</table>

```java
public class TreeMapExample2 {
    public static void main(String[] args) {
        TreeMap<Integer, String> scores = new TreeMap<>();
        scores.put(new Integer(87), "홍길동");
        scores.put(new Integer(98), "이동수");
        scores.put(new Integer(75), "박길순");
        scores.put(new Integer(95), "신용권");
        scores.put(new Integer(80), "김자바");

        NavigableMap<Integer, String> descendingMap = scores.descendingMap();
        Set<Map.Entry<Integer, String>> descendingEntrySet = descendingMap.entrySet();
        for (Map.Entry<Integer, String> entry : descendingEntrySet) {
            System.out.print(entry.getKey() + "-" + entry.getValue() + " ");
        }
        System.out.println();

        NavigableMap<Integer, String> ascendingMap = descendingMap.descendingMap();
        Set<Map.Entry<Integer, String>> ascendingEntryMap = ascendingMap.entrySet();
        for (Map.Entry<Integer, String> entry : ascendingEntryMap) {
            System.out.print(entry.getKey() + "-" + entry.getValue() + " ");
        }
        System.out.println();
    }
}
```
<table>
    <tr>
        <th>리턴타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>NavigableMap&lt;K, V&gt;</td>
        <td>headMap(K toKey,<br>
        boolean inclusive)</td>
        <td>주어진 키보다 낮은 Map.Entry 들을 NavigableMap 으로 리턴,<br>
        주어진 키의 Map.Entry 포함 여부는 두 번째 매개값에 따라 달라짐</td>
    </tr>
    <tr>
        <td>NavigableMap&lt;K, V&gt;</td>
        <td>tailMap(K fromKey,<br>
        boolean inclusive)</td>
        <td>주어진 키보다 높은 Map.Entry 들을 NavigableMap 으로 리턴,<br>
        주어진 키의 Map.Entry 포함 여부는 두 번째 매개값에 따라 달라짐</td>
    </tr>
    <tr>
        <td>NavigableMap&lt;K, V&gt;</td>
        <td>subMap(K fromKey,<br>
        boolean inclusive,<br>
        K toKey,<br>
        boolean inclusive)</td>
        <td>시작과 끝으로 주어진 키 사이의 Map.Entry 들을 NavigableMap 컬렉션으로 리턴,<br>
        시작과 끝 키의 Map.Entry 포함 여부는 두 번째, 네 번째 매개값에 따라 달라짐</td>
    </tr>
</table>

```java
public class TreeMapExample3 {
    public static void main(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("apple", new Integer(10));
        treeMap.put("forever", new Integer(60));
        treeMap.put("description", new Integer(40));
        treeMap.put("ever", new Integer(50));
        treeMap.put("zoo", new Integer(10));
        treeMap.put("base", new Integer(20));
        treeMap.put("guess", new Integer(70));
        treeMap.put("cherry", new Integer(70));

        System.out.println("[ c ~ f 사이의 단어 검색 ]");
        NavigableMap<String, Integer> rangeMap = treeMap.subMap("c", true, "f", true);
        for (Map.Entry<String, Integer> entry : rangeMap.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue() + "페이지");
        }
    }
}
```
###### TreeSet, TreeMap 의 정렬 기준 (Comparable, Comparator)
    TreeSet, TreeMap 은 숫자 타입일 경우 값으로 자동 오름차순, 문자열일 경우 유니코드로 정렬
    사용자 정의 클래스로 정렬할 때 Comparable 을 구현,
    compareTo() 메소드를 오버라이딩하여 원하는 정렬을 구현해 낼 수 있다.

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>int</td>
        <td>compareTo(T o)</td>
        <td>주어진 객체와 같으면 0을 리턴,<br>
        주어진 객체보다 적으면 음수를 리턴,<br>
        주어진 객체보다 크면 양수를 리턴</td>
    </tr>
</table>

```java
public class ComparableExample {
    public static void main(String[] args) {
        TreeSet<Person2> treeSet = new TreeSet<>();
        treeSet.add(new Person2("홍길동", 45));
        treeSet.add(new Person2("김자바", 25));
        treeSet.add(new Person2("박지원", 31));
        Iterator<Person2> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            Person2 person2 = iterator.next();
            System.out.println(person2.name + ":" + person2.age);
        }
    }


}

class Person2 implements Comparable<Person2> {
    String name;
    int age;

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person2 person2) {
        if (age < person2.age) {
            return -1;
        } else if (age == person2.age) {
            return 0;
        } else {
            return 1;
        }
    }
}
```

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>int</td>
        <td>compare(T o1, T o2)</td>
        <td>o1과 o2가 같으면 0을 리턴,<br>
        o1이 o2보다 앞에 오게 하려면 음수를 리턴,<br>
        o1이 o2보다 뒤에 오게 하려면 양수를 리턴</td>
    </tr>
</table>

```java
public class DescendingComparator implements Comparator<Fruit2> {
    @Override
    public int compare(Fruit2 t1, Fruit2 t2) {
        if (t1.price < t2.price) {
            return 1;
        } else if (t1.price == t2.price) {
            return 0;
        } else {
            return -1;
        }
    }
}

class Fruit2 {
    String name;
    int price;

    public Fruit2(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

class ComparatorExample {
    public static void main(String[] args) {
//        TreeSet<Fruit2> treeSet = new TreeSet<>();
//        treeSet.add(new Fruit2("포도", 3000)); // Fruit2 가 Comparable 을 구현하지 않아서 예외 발생
//        treeSet.add(new Fruit2("수박", 10000));
//        treeSet.add(new Fruit2("딸기", 6000));

        TreeSet<Fruit2> treeSet = new TreeSet<>(new DescendingComparator());
        treeSet.add(new Fruit2("포도", 3000));
        treeSet.add(new Fruit2("수박", 10000));
        treeSet.add(new Fruit2("딸기", 6000));

        Iterator<Fruit2> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            Fruit2 fruit2 = iterator.next();
            System.out.println(fruit2.name + ": " + fruit2.price);
        }
    }
}
```
#### Stack
    LIFO ( Last In First Out ) 후입 선출 자료구조를 구현한 클래스

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>E</td>
        <td>push(E item)</td>
        <td>주어진 객체를 스택에 넣는다.</td>
    </tr>
    <tr>
        <td>E</td>
        <td>peek()</td>
        <td>스택의 맨 위 객체를 가져온다.<br>
        객체를 스택에서 제거하지 않는다.</td>
    </tr>
    <tr>
        <td>E</td>
        <td>pop()</td>
        <td>스택의 맨 위 객체를 가져온다.<br>
        객체를 스택에서 제거한다.</td>
    </tr>
</table>

```java
public class StackExample {
    public static void main(String[] args) {
        Stack<Coin> coinBox = new Stack<>();
        coinBox.push(new Coin(100));
        coinBox.push(new Coin(50));
        coinBox.push(new Coin(500));
        coinBox.push(new Coin(10));

        while (!coinBox.isEmpty()) {
            Coin coin = coinBox.pop();
            System.out.println("꺼내온 동전 : " + coin.getValue() + "원");
        }
    }
}

class Coin {
    private int value;

    public Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
```

#### Queue
    FIFO ( First In First Out ) 선입 선출 자료구조를 구현한 인터페이스

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>boolean</td>
        <td>offer(E e)</td>
        <td>주어진 객체를 큐에 넣는다.</td>
    </tr>
    <tr>
        <td>E</td>
        <td>peek()</td>
        <td>큐의 맨 위 객체를 가져온다.<br>
        객체를 큐에서 제거하지 않는다.</td>
    </tr>
    <tr>
        <td>E</td>
        <td>poll()</td>
        <td>큐의 맨 위 객체를 가져온다.<br>
        객체를 큐에서 제거한다.</td>
    </tr>
</table>

```java
public class QueueExample {
    public static void main(String[] args) {
        Queue<Message> messageQueue = new LinkedList<>();
        messageQueue.offer(new Message("sendMail", "홍길동"));
        messageQueue.offer(new Message("sendSMS", "신용권"));
        messageQueue.offer(new Message("sendKakaoTalk", "홍두께"));
        while (!messageQueue.isEmpty()) {
            Message message = messageQueue.poll();
            System.out.println(message.command + " : " + message.to);
        }
    }
}

class Message {
    String command;
    String to;

    public Message(String command, String to) {
        this.command = command;
        this.to = to;
    }
}
```

#### 동기화된 컬렉션
    Vector와 Hashtable을 제외한 컬렉션들은 동기화된 메소드로 구성되어 있지 않아
    멀티 스레드 환경에서 안전하지 않다.
    따라서 ArrayList, HashSet, HashMap 을 멀티 스레드 환경에서 사용할 경우
    비동기화된 메소드를 동기화된 메소드로 래핑하는 Collections의
    synchronizedXXX() 메소드를 제공
    
 <table>
     <tr>
         <th>리턴 타입</th>
         <th>메소드</th>
         <th>설명</th>
     </tr>
     <tr>
         <td>List&lt;T&gt;</td>
         <td>synchronizedList(List&lt;T&gt; list)</td>
         <td>List 를 동기화된 List 로 리턴</td>
     </tr>
     <tr>
         <td>Map&lt;K, V&gt;</td>
         <td>synchronizedMap(Map&lt;K, V&gt; map)</td>
         <td>Map 을 동기화된 Map 으로 리턴</td>
     </tr>
     <tr>
         <td>Set&lt;T&gt;</td>
         <td>synchronizedSet(List&lt;T&gt; set)</td>
         <td>Set 을 동기화된 Set 으로 리턴</td>
     </tr>
 </table>
 
    List<T> list = Collections.synchronizedList(new ArrayList<T>());
    Set<T> set = Collections.synchronizedSet(new HashSet<T>());
    Map<K, V> map = Collections.synchronizedMap(new HashMap<K, V>());
    
#### 병렬 처리를 위한 컬렉션 ( ★ 다시 공부 )
    동기화된 컬렉션은 멀티 스레드 환경에서 하나의 스레드가 요소를 안전하게 처리하도록 도와주지만,
    전체 요소를 빠르게 처리하지는 못한다. 하나의 스레드가 요소를 처리할 때 전체 잠금이 발생하여
    다른 스레드는 대기 상태가 된다. 자바는 멀티 스레드가 컬렉션의 요소를 병렬적으로 처리 할 수
    있도록 특별한 컬렉션을 제공하고 있다.
    
    ConcurrentHashMap, ConcurrentLinkedQueue
     
    ConcurrentHashMap : 부분(Segment) 잠금 사용
                        부분 잠금을 사용하여 처리하는 요소가 포함된 부분만 잠금하고 
                        나머지 부분은 다른 스레드가 변경할 수 있도록 하는 것
    Map<K, V> map = new ConcurrentHashMap<K, V>();
    
    ConcurrentLinkedQueue : 락-프리(Lock-Free) 알고리즘 사용
                            여러개의 스레드가 동시에 접근할 경우, 잠금을 사용하지 않고도 최소한
                            하나의 스레드가 안전하게 요소를 저장하거나 얻도록 해줌.
    Queue<E> queue = new ConcurrentLinkedQueue<E>();

### 60. 스트림 ( ★ 다시 공부 많이 공부 )
    컬렉션의 저장 요소를 하나씩 참조해서 람다식으로 처리할 수 있도록 해주는 반복자이다.

```java
public class IteratorVsStreamExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("홍길동", "신용권", "김자바");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println(name);
        }
        System.out.println();

        Stream<String> stream = list.stream();
        stream.forEach(name -> System.out.println(name));
    }
}

```
    특징
    1. 람다식으로 요소 처리 코드를 제공하는 점
    2. 내부 반복자를 사용하므로 병렬 처리가 쉽다는 점
    3. 중간 처리와 최종 처리 작업을 수행하는 점

###### 람다식으로 요소 처리 코드를 제공하는 점
    Stream 이 제공하는 대부분의 요소 처리 메소드는 함수적 인터페이스 매개 타입을
    갖기 때문에 람다식 또는 메소드 참조를 이용해서 요소 처리 내용을 매개값을로 전달 할 수 있다.

```java
public class LambdaExpressionsExample {
    public static void main(String[] args) {
        List<Student2> list = Arrays.asList(new Student2("홍길동", 90),
                new Student2("신용권", 92));

        Stream<Student2> stream = list.stream();
        stream.forEach(student -> {
            String name = student.name;
            int score = student.score;
            System.out.println(name + "-" + score);
        });
    }
}
class Student2 {
    String name;
    int score;

    public Student2(String name, int score) {
        this.name = name;
        this.score = score;

    }
}
```    

###### 내부 반복자를 사용하므로 병렬 처리가 쉽다는 점
    외부 반복자란 개발자가 코드로 직접 컬렉션의 요소를 반복해서 가져오는 코드 패턴
    index를 이용하는 for문, iterator를 이용하는 while문 등
    
    내부 반복자는 컬렉션 내부에서 요소들을 반복시키고, 
    개발자는 요소당 처리해야 할 코드만 제공하는 코드 패턴
    
    코드도 간결해 지고, 요소의 병렬 처리가 컬렉션 내부에서 처리
    
    병렬 (parallel) 처리
        한 가지 작업을 서브 작업으로 나누고, 서브 작업들을 분리된 스레드에서
        병렬적으로 처리하는 것.
        런타임 시 하나의 작업을 서브 작업으로 자동으로 나누고, 서브 작업의 결과를
        자동으로 결합해서 최종 결과물을 생성
        
        ForkJoinPool 스레드풀 사용

```java
public class ParallelExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                "홍길동", "신용권", "김자바", "람다식", "박병렬"
        );
        Stream<String> stream = list.stream();
        stream.forEach(ParallelExample::print);

        System.out.println();

        Stream<String> parallelStream = list.parallelStream();
        parallelStream.forEach(ParallelExample::print);
    }

    public static void print(String str) {
        System.out.println(str + " " + Thread.currentThread().getName());
    }
}
```

###### 중간 처리와 최종 처리 작업을 수행하는 점
    중간 처리에서는 매핑, 필터링, 정렬을 수행
    최종 처리에서는 반복, 카운팅, 평균, 총합 등의 집계 처리를 수행
    
```java
public class MapAndReduceExample {
    public static void main(String[] args) {
        List<Student5> list = Arrays.asList(
                new Student5("홍길동", 10),
                new Student5("신용권", 20),
                new Student5("유미선", 30)
        );
        double avg = list.stream()
                // 중간 처리( 학생 객체를 점수로 매핑 )
                .mapToInt(Student5::getScore)
                // 최종 처리( 평균 점수 )
                .average()
                .getAsDouble()
                ;
        System.out.println("평균 점수: " + avg);
    }
}

class Student5 {
    String name;
    int score;

    public Student5(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
```

#### 스트림 종류

<table>
     <tr>
         <th>리턴 타입</th>
         <th>메소드</th>
         <th>소스</th>
     </tr>
     <tr>
         <td>Stream&lt;T&gt;</td>
         <td>java.util.Collection.stream()<br>
         java.util.Collection.parallelStream()</td>
         <td>컬렉션</td>
     </tr>
     <tr>
         <td>Stream&lt;T&gt;<br>
         IntStream<br>
         LongStream<br>
         DoubleStream</td>
         <td>Arrays.stream(T[]), Stream.of(T[])<br>
         Arrays.stream(int[]), IntStream.of(int[])<br>
         Arrays.stream(long[]), LongStream.of(long[])<br>
         Arrays.stream(double[]), DoubleStream.of(double[])</td>
         <td>배열</td>
     </tr>
     <tr>
         <td>IntStream</td>
         <td>IntStream.rang(int, int)<br>
         IntStream.rangeClosed(int, int)</td>
         <td>int 범위</td>
     </tr>
     <tr>
         <td>LongStream</td>
         <td>LongStream.range(long, long)<br>
         LongStream.rangeClosed(long, long)</td>
         <td>long 범위</td>
     </tr>
     <tr>
         <td>Stream&lt;Path&gt;</td>
         <td>Files.find(Path, int, BiPredicate, FileVisitOption)<br>
         Files.list(Path)</td>
         <td>디렉토리</td>
     </tr>
     <tr>
         <td>Stream&lt;String&gt;</td>
         <td>Files.lines(Path, Charset)<br>
         BufferedReader.lines()</td>
         <td>파일</td>
     </tr>
     <tr>
         <td>DoubleStream<br>
         IntStream<br>
         LongStream</td>
         <td>Random.doubles(...)<br>
         Random.ints()<br>
         Random.longs()</td>
         <td>랜덤 수</td>
     </tr>
</table>

###### 컬렉션으로부터 스트림 얻기
```java
public class FromCollectionExample {
    public static void main(String[] args) {
        List<Student5> list = Arrays.asList(
                new Student5("홍길동", 10),
                new Student5("신용권", 20),
                new Student5("유미선", 30)
        );
        Stream<Student5> stream = list.stream();
        stream.forEach(s -> System.out.println(s.name));
    }
}
```
###### 배열로부터 스트림 얻기
```java
public class FromArrayExample {
    public static void main(String[] args) {
        String[] strArray = {"홍길동", "신용권", "김미나"};
        Stream<String> stream = Arrays.stream(strArray);
        stream.forEach(s -> System.out.print(s + ","));
        System.out.println();
        
        int[] intArray = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(intArray);
        intStream.forEach(i -> System.out.print(i + ","));
    }
}
```

###### 파일로부터 스트림 얻기
```java
public class FromFileContentExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/examples/test.txt");
        Stream<String> stream;

        // Files.line() 메소드 이용
        stream = Files.lines(path, Charset.defaultCharset());
        stream.forEach(System.out::println);
        System.out.println();

        //BufferedReader 의 lines() 메소드 이용
        File file = path.toFile();
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        stream = br.lines();
        stream.forEach(System.out::println);
    }
}
```

###### 디렉토리로부터 스트림 얻기
```java
public class FromDirectoryExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/examples");
        Stream<Path> stream = Files.list(path);
        stream.forEach(p-> System.out.println(p.getFileName()));
    }
}
```
#### 스트림 파이프라인
    파이프라인 : 여러 개의 스트림이 연결되어 있는 구조
    
    리덕션 : 대량의 데이터를 가공해서 축소하는 것
            합계, 평균값, 카운팅, 최대값, 최소값 등
    컬렉션의 요소를 리덕션의 결과물로 바로 집계할 수 없을 경우
    필터링, 매핑, 정렬, 그룹핑 등의 중간 처리가 필요
    
    중간 스트림이 생성될 때 요소들이 바로 중간 처리 되는 것이 아니라
    최종 처리가 시작되기 전까지 중간 처리는 지연된다.
    최종 처리가 시작되면 비로소 컬렉션의 요소가 하나씩 
    중간 스트림에서 처리되고 최종 처리까지 오게 된다.
    
```java
public class StreamPipelinesExample {
    public static void main(String[] args) {
        List<Member5> list = Arrays.asList(
                new Member5("홍길동", Member5.MALE, 30),
                new Member5("김나리", Member5.FEMALE, 20),
                new Member5("신용권", Member5.MALE, 45),
                new Member5("박수미", Member5.FEMALE, 27)
        );
        double ageAvg = list.stream()
                .filter(member5 -> member5.getSex() == Member5.MALE)
                .mapToInt(member5 -> member5.getAge())
                .average()
                .getAsDouble()
                ;
        System.out.println("남자 평균 나이: " + ageAvg);
    }
}

class Member5 {
    static int MALE = 0;
    static int FEMALE = 1;

    private String name;
    private int sex;
    private int age;

    public Member5(String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}
```
    
<table>
    <tr>
        <th colspan="2">종류</th>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>소속된 인터페이스</th>
    </tr>
    <tr>
        <td rowspan="16">중간<br>처리</td>
        <td rowspan="2">필터링</td>
        <td rowspan="16">Stream<br>
        IntStream<br>
        LongStream<br>
        DoubleStream</td>
        <td>distinct()</td>
        <td>공통</td>
    </tr>
    <tr>
        <td>filter(...)</td>
        <td>공통</td>
    </tr>
    <tr>
        <td rowspan="12">매핑</td>
        <td>flatMap(...)</td>
        <td>공통</td>
    </tr>
    <tr>
        <td>flatMapToDouble(...)</td>
        <td>Stream</td>
    </tr>
    <tr>
        <td>flatMapToInt(...)</td>
        <td>Stream</td>
    </tr>
    <tr>
        <td>flatMapToLong(...)</td>
        <td>Stream</td>
    </tr>
    <tr>
        <td>map(...)</td>
        <td>공통</td>
    </tr>
    <tr>
        <td>mapToDouble(...)</td>
        <td>Stream, IntStream, LongStream</td>
    </tr>
    <tr>
        <td>mapToInt(...)</td>
        <td>Stream, LongStream, DoubleStream</td>
    </tr>
    <tr>
        <td>mapToLong(...)</td>
        <td>Stream, IntStream, DoubleStream</td>
    </tr>
    <tr>
        <td>mapToObj(...)</td>
        <td>IntStream, LongStream, DoubleStream</td>
    </tr>
    <tr>
        <td>asDoubleStream()</td>
        <td>IntStream, LongStream</td>
    </tr>
    <tr>
        <td>asLongStream()</td>
        <td>IntStream</td>
    </tr>
    <tr>
        <td>boxed()</td>
        <td>IntStream, LongStream, DoubleStream</td>
    </tr>
    <tr>
        <td>정렬</td>
        <td>sorted(...)</td>
        <td>공통</td>
    </tr>
    <tr>
        <td>루핑</td>
        <td>peek(...)</td>
        <td>공통</td>
    </tr>
    <tr>
        <td rowspan="12">최종<br>처리</td>
        <td rowspan="3">매칭</td>
        <td>boolean</td>
        <td>allMatch(...)</td>
        <td>공통</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>anyMatch(...)</td>
        <td>공통</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>nonMatch(...)</td>
        <td>공통</td>
    </tr>
    <tr>
        <td rowspan="7">집계</td>
        <td>long</td>
        <td>count()</td>
        <td>공통</td>
    </tr>
    <tr>
        <td>OptionalXXX</td>
        <td>findFirst()</td>
        <td>공통</td>
    </tr>
    <tr>
        <td>OptionalXXX</td>
        <td>max(...)</td>
        <td>공통</td>
    </tr>
    <tr>
        <td>OptionalXXX</td>
        <td>min(...)</td>
        <td>공통</td>
    </tr>
    <tr>
        <td>OptionalDouble</td>
        <td>average()</td>
        <td>IntStream, LongStream, DoubleStream</td>
    </tr>
    <tr>
        <td>OptionalXXX</td>
        <td>reduce(...)</td>
        <td>공통</td>
    </tr>
    <tr>
        <td>int, long, double</td>
        <td>sum()</td>
        <td>IntStream, LongStream, DoubleStream</td>
    </tr>
    <tr>
        <td>루핑</td>
        <td>void</td>
        <td>forEach(...)</td>
        <td>공통</td>
    </tr>
    <tr>
        <td>수집</td>
        <td>R</td>
        <td>collect(...)</td>
        <td>공통</td>
    </tr>
</table>

#### 필터링 (distinct(), filter())
    중간 처리 기능으로 요소를 걸러내는 역할
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td rowspan="5">Stream<br>
        IntStream<br>
        LongStream<br>
        DoubleStream</td>
        <td>distinct()</td>
        <td>중복제거</td>
    </tr>
    <tr>
        <td>filter(Predicate)</td>
        <td rowspan="4">조건 필터링</td>
    </tr>
    <tr>
        <td>filter(IntPredicate)</td>
    </tr>
    <tr>
        <td>filter(LongPredicate)</td>
    </tr>
    <tr>
        <td>filter(DoublePredicate)</td>
    </tr>
</table>

    distinct() 메소드는 중복을 제거하는데,
    Stream 의 경우 Object.equals(Object) true 이면 동일한 객체로 판단
    
    filter() 메소드는 매개값을 ㅗ주어진 Predicate가 true를 리턴하는 요소만 필터링

```java
public class FilteringExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("홍길동", "신용권", "김자바", "신용권", "신민철");
        names.stream()
                .distinct()
                .forEach(System.out::println);
        System.out.println();
        names.stream()
                .filter(n->n.startsWith("신"))
                .forEach(System.out::println);
        System.out.println();
        names.stream()
                .distinct()
                .filter(n->n.startsWith("신"))
                .forEach(System.out::println);
    }
}
```

#### 매핑 (flatMapXXX(), mapXXX(), asXXXStream(), boxed())
    중간 처리 기능으로 스트림의 요소를 다른 요소로 대체하는 작업
    
###### flatMapXXX() 메소드
    요소를 대체하는 복수 개의 요소들로 구성된 새로운 스트림을 리턴한다.
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>요소 -> 대체 요소</th>
    </tr>
    <tr>
        <td>Stream&lt;R&gt;</td>
        <td>flatMap(Function&lt;T, Stream&lt;R&gt;&gt;)</td>
        <td>T -> Stream&lt;R&gt;</td>
    </tr>
    <tr>
        <td>DoubleStream</td>
        <td>flatMap(DoubleFunction&lt;DoubleStream&gt;)</td>
        <td>double -> DoubleFunction</td>
    </tr>
    <tr>
        <td>IntStream</td>
        <td>flatMap(IntFunction&lt;IntStream&gt;)</td>
        <td>int -> IntStream</td>
    </tr>
    <tr>
        <td>LongStream</td>
        <td>flatMap(LongFunction&lt;LongStream&gt;)</td>
        <td>long -> LongStream</td>
    </tr>
    <tr>
        <td>DoubleStream</td>
        <td>flatMapToDouble(Function&lt;T, DoubleStream&lt;R&gt;&gt;)</td>
        <td>T -> DoubleStream&lt;R&gt;</td>
    </tr>
    <tr>
        <td>IntStream</td>
        <td>flatMapToInt(Function&lt;T, IntStream&lt;R&gt;&gt;)</td>
        <td>T -> DoubleStream&lt;R&gt;</td>
    </tr>
    <tr>
        <td>LongStream</td>
        <td>flatMapToLong(Function&lt;T, LongStream&lt;R&gt;&gt;)</td>
        <td>T -> LongStream&lt;R&gt;</td>
    </tr>
</table>

```java
public class FlatMapExample {
    public static void main(String[] args) {
        List<String> inputList1 = Arrays.asList("java8 lambda", "stream mapping");
        inputList1
                .stream()
                .flatMap(data -> Arrays.stream(data.split(" ")))
                .forEach(System.out::println);

        System.out.println();

        List<String> inputList2 = Arrays.asList("10, 20, 30", "40, 50, 60");
        inputList2
                .stream()
                .flatMapToInt(data -> {
                    String[] strArr = data.split(",");
                    int[] intArr = new int[strArr.length];
                    for (int i = 0; i < strArr.length; i++) {
                        intArr[i] = Integer.parseInt(strArr[i].trim());
                    }
                    return Arrays.stream(intArr);
                })
                .forEach(System.out::println);
    }
}
```

###### mapXXX() 메소드
    요소를 대체하는 요소로 구성된 새로운 스트림을 리턴한다.
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>요소 -> 대체 요소</th>
    </tr>
    <tr>
        <td>Stream&lt;R&gt;</td>
        <td>map(Function&lt;T, R&gt;)</td>
        <td>T -> R</td>
    </tr>
    <tr>
        <td>DoubleStream</td>
        <td>mapToDouble(ToDoubleFunction&lt;T&gt;)</td>
        <td>T -> double</td>
    </tr>
    <tr>
        <td>IntStream</td>
        <td>mapToInt(ToIntFunction&lt;T&gt;)</td>
        <td>T -> int</td>
    </tr>
    <tr>
        <td>LongStream</td>
        <td>mapToLong(ToLongFunction&lt;T&gt;)</td>
        <td>T -> long</td>
    </tr>
    <tr>
        <td>DoubleStream</td>
        <td>map(DoubleUnaryOperator)</td>
        <td>double -> double</td>
    </tr>
    <tr>
        <td>IntStream</td>
        <td>mapToInt(DoubleToIntFunction)</td>
        <td>double -> int</td>
    </tr>
    <tr>
        <td>LongStream</td>
        <td>mapToLong(DoubleToLongFunction)</td>
        <td>double -> long</td>
    </tr>
    <tr>
        <td>Stream&lt;U&gt;</td>
        <td>mapToObj(DoubleFunction&lt;U&gt;)</td>
        <td>double -> U</td>
    </tr>
    <tr>
        <td>IntStream</td>
        <td>map(IntUnaryOperator)</td>
        <td>int -> int</td>
    </tr>
    <tr>
        <td>DoubleStream</td>
        <td>mapToDouble(IntToDoubleFunction)</td>
        <td>int -> double</td>
    </tr>
    <tr>
        <td>LongStream</td>
        <td>mapToLong(IntToLongFunction)</td>
        <td>int -> long</td>
    </tr>
    <tr>
        <td>Stream&lt;U&gt;</td>
        <td>mapToObj(IntFunction&lt;U&gt;)</td>
        <td>int -> U</td>
    </tr>
    <tr>
        <td>LongStream</td>
        <td>map(LongUnaryOperator)</td>
        <td>long -> long</td>
    </tr>
    <tr>
        <td>DoubleStream</td>
        <td>mapToDouble(LongToDoubleFunction)</td>
        <td>long -> double</td>
    </tr>
    <tr>
        <td>IntStream</td>
        <td>mapToInt(LongToIntFunction)</td>
        <td>long -> int</td>
    </tr>
    <tr>
        <td>Stream&lt;U&gt;</td>
        <td>mapToObj(LongFunction&lt;U&gt;)</td>
        <td>long -> U</td>
    </tr>
</table>

```java
public class MapExample {
    public static void main(String[] args) {
        List<Student2> studentList = Arrays.asList(
                new Student2("홍길동", 10),
                new Student2("신용권", 20),
                new Student2("유미선", 30)
        );
        studentList
                .stream()
                .mapToInt(Student2::getScore)
                .forEach(System.out::println);
    }
}
```

###### asDoubleStream(), asLongStream(), boxed() 메소드
    asDoubleStream() 메소드는 IntStream의 int 요소 또는 LongStream의 long 요소를
    double 요소로 타입 변환해서 DoubleStream을 생성한다.
    
    asLongStream() 메소드는 IntStream의 int 요소를
    long 요소로 타입 변환해서 LongStream을 생성한다.
    
    boxed() 메소드는 int, long, double 요소를 Integer, Long, Double 요소로 박싱해서
    Stream을 생성한다.
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>DoubleStream</td>
        <td>asDoubleStream()</td>
        <td>int -> double<br>
        long -> double</td>
    </tr>
    <tr>
        <td>LongStream</td>
        <td>asLongStream()</td>
        <td>int -> long</td>
    </tr>
    <tr>
        <td>Stream&lt;Integer&gt;<br>
        Stream&lt;Long&gt;<br>
        Stream&lt;Double&gt;</td>
        <td>boxed()</td>
        <td>int -> Integer<br>
        long -> Long<br>
        double -> Double</td>
    </tr>
</table>

```java
public class AsDoubleStreamAndBoxedExample {
    public static void main(String[] args) {
        int[] intArray = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(intArray);

        intStream
                .asDoubleStream()
                .forEach(System.out::println);

        System.out.println();

        intStream = Arrays.stream(intArray);

        intStream
                .boxed()
                .forEach(obj -> System.out.println(obj.intValue()));
    }
}
```
#### 정렬 (sorted())
    요소가 최종 처리되기 전에 중간 단계에서 요소를 정렬해서 최종 처리 순서를 변경할 수 있다.

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Stream&lt;T&gt;</td>
        <td>sorted()</td>
        <td>객체를 Comparable 구현 방법에 따라 결정</td>
    </tr>
    <tr>
        <td>Stream&lt;T&gt;</td>
        <td>sorted(Comparator&lt;T&gt;)</td>
        <td>객체를 주어진 Comparator 에 따라 정렬</td>
    </tr>
    <tr>
        <td>DoubleStream</td>
        <td>sorted()</td>
        <td>double 요소를 오름차순으로 정렬</td>
    </tr>
    <tr>
        <td>IntStream</td>
        <td>sorted()</td>
        <td>int 요소를 오름차순으로 정렬</td>
    </tr>
    <tr>
        <td>LongStream</td>
        <td>sorted()</td>
        <td>long 요소를 오름차순으로 정렬</td>
    </tr>
</table>

```java
public class SortingExample {
    public static void main(String[] args) {
        IntStream intStream = Arrays.stream(new int[]{5, 3, 2, 1, 4});
        intStream
                .sorted()
                .forEach(System.out::println);
        System.out.println();
        List<Student6> list = Arrays.asList(
                new Student6("홍길동", 30),
                new Student6("신용권", 10),
                new Student6("김자바", 20)
        );
        list
                .stream()
                .sorted()
                .forEach(s -> System.out.println(s.getScore()));
        System.out.println();

        list
                .stream()
                .sorted(Comparator.reverseOrder())
                .forEach(s -> System.out.println(s.getScore()));
        System.out.println();

    }
}
```

#### 루핑(peek(), forEach())
    루핑은 요소 전체를 반복하는 것
    peek() : 중간 처리 단계에서 전체 요소를 루핑하면서 추가적인 작업을 위함
             중간 처리 메소드 이기 때문에 최종 처리 메소드가 호출 되어야만 동작한다.
```java
public class LoopingExample {
    public static void main(String[] args) {
        int[] intArr = {1, 2, 3, 4, 5};
        System.out.println("[ peek()를 마지막에 호출한 경우 ]");

        Arrays.stream(intArr)
                .filter(a -> a % 2 == 0)
                .peek(n -> System.out.println(n)); // 동작안함

        System.out.println("[ 최종 처리 메소드를 마지막에 호출한 경우 ]");
        int total = Arrays.stream(intArr)
                .filter(a -> a % 2 == 0)
                .peek(System.out::println) // 동작함
                .sum();                    // 최종메소드
        System.out.println("총합: " + total);

        System.out.println("[ forEach()를 마지막에 호출한 경우]");
        Arrays.stream(intArr)
                .filter(a -> a % 2 == 0)
                .forEach(System.out::println); // 최종 메소드로 동작
    }
}
```

#### 매칭(allMatch(), anyMatch(), nonMatch())
    최종 처리 단계에서 요소들이 특정 조건에 만족하는지 조사할 수 있도록 세가지 매칭 메소드 제공
    allMatch() : 모든 요소들이 매개값으로 주어진 Predicate의 조건에 만족하는지 조사
    anyMatch() : 최소한 한 개의 요소가 매개값으로 주어진 Predicate의 조건에 만족하는지 조사
    nonMatch() : 모든 요소들이 매개값으로 주어진 Predicate의 조건에 만족하지 않는지 조사

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>제공 인터페이스</th>
    </tr>
    <tr>
        <td>boolean</td>
        <td>allMatch(Predicate&lt;T&gt; predicate)<br>
        anyMatch(Predicate&lt;T&gt; predicate)<br>
        noneMatch(Predicate&lt;T&gt; predicate)</td>
        <td>Stream</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>allMatch(IntPredicate predicate)<br>
        anyMatch(IntPredicate predicate)<br>
        noneMatch(IntPredicate predicate)</td>
        <td>IntStream</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>allMatch(LongPredicate predicate)<br>
        anyMatch(LongPredicate predicate)<br>
        noneMatch(LongPredicate predicate)</td>
        <td>LongStream</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>allMatch(DoublePredicate predicate)<br>
        anyMatch(DoublePredicate predicate)<br>
        noneMatch(DoublePredicate predicate)</td>
        <td>DoubleStream</td>
    </tr>
</table>

```java
public class MatchExample {
    public static void main(String[] args) {
        int[] intArr = {2, 4, 6};
        boolean result = Arrays.stream(intArr)
                .allMatch(a -> a % 2 == 0);
        System.out.println("모두 2의 배수인가? " + result);

        boolean result2 = Arrays.stream(intArr)
                .anyMatch(a -> a % 3 == 0);
        System.out.println("하나라도 3의 배수가 있는가? " + result2);

        boolean result3 = Arrays.stream(intArr)
                .noneMatch(a -> a % 3 == 0);
        System.out.println("3의 배수가 없는가? " + result3);
    }
}
```

#### 기본 집계(sum(), count(), average(), max(), min())
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>long</td>
        <td>count()</td>
        <td>요소 개수</td>
    </tr>
    <tr>
        <td>OptionalXXX</td>
        <td>findFirst()</td>
        <td>첫 번째 요소</td>
    </tr>
    <tr>
        <td>Optional&lt;T&gt;<br>
        OptionalXXX</td>
        <td>max(Comparator&lt;T&gt;)<br>
        max()</td>
        <td>최대 요소</td>
    </tr>
    <tr>
        <td>Optional&lt;T&gt;<br>
        OptionalXXX</td>
        <td>min(Comparator&lt;T&gt;)<br>
        min()</td>
        <td>최소 요소</td>
    </tr>
    <tr>
        <td>OptionalDouble</td>
        <td>average()</td>
        <td>요소 평균</td>
    </tr>
    <tr>
        <td>int, long, double</td>
        <td>sum()</td>
        <td>요소 총합</td>
    </tr>
</table>

```java
public class AggregateExample {
    public static void main(String[] args) {
        long count = Arrays
                .stream(new int[]{1, 2, 3, 4, 5})
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("2의 배수 개수: " + count);

        long sum = Arrays
                .stream(new int[]{1, 2, 3, 4, 5})
                .filter(n -> n % 2 == 0)
                .sum();
        System.out.println("2의 배수 합계: " + sum);

        double avg = Arrays
                .stream(new int[]{1, 2, 3, 4, 5})
                .filter(n -> n % 2 == 0)
                .average()
                .getAsDouble();
        System.out.println("2의 배수 평균: " +  avg);

        int max = Arrays
                .stream(new int[]{1, 2, 3, 4, 5})
                .filter(n -> n % 2 == 0)
                .max()
                .getAsInt();
        System.out.println("2의 배수 최대값: " + max);

        int min = Arrays
                .stream(new int[]{1, 2, 3, 4, 5})
                .filter(n -> n % 2 == 0)
                .min()
                .getAsInt();
        System.out.println("2의 배수 최소값: " + min);

        int first = Arrays
                .stream(new int[]{1, 2, 3, 4, 5})
                .filter(n -> n % 2 == 0)
                .findFirst()
                .getAsInt();
        System.out.println("2의 배수 첫번째: " + first);
    }
}
```

###### Optional 클래스
    Optional 클래스는 단순히 집계 값만 저장하는 것이 아니라,
    집계 값이 없을 경우 디폴트 값을 설정할 수 있고,
    집계 값을 처리하는 Consumer도 등록할 수 있다.

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isPresent()</td>
        <td>값이 저장되어 있는지 여부</td>
    </tr>
    <tr>
        <td>T<br>
        double<br>
        int<br>
        long</td>
        <td>orElse(T)<br>
        orElse(double)<br>
        orElse(int)<br>
        orElse(long)</td>
        <td>값이 저장되어 있지 않을 경우 디폴트 값 지정</td>
    </tr>
    <tr>
        <td>void</td>
        <td>ifPresent(Consumer)<br>
        ifPresent(DoubleConsumer)<br>
        ifPresent(IntConsumer)<br>
        ifPresent(LongConsumer)</td>
        <td>값이 저장되어 있을 경우 Consumer 에서 처리</td>
    </tr>
</table>

    컬렉션의 요소가 없을 경우 ( size == 0 )
    stream 사용시
    1. Optional 객체 사용
    2. orElse() 메소드로 default 값 설정
    3. ifPresent() 메소드로 값이 존재할 경우만 실행
    
```java
public class OptionalExample {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();

//        double avg = list
//                .stream()
//                .mapToInt(Integer::intValue)
//                .average()
//                .getAsDouble()
//                ; // 예외발생 NoSuchElementException
        // 방법 1.
        OptionalDouble optionalDouble = list
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                ;
        if (optionalDouble.isPresent()) {
            System.out.println("평균: " + optionalDouble);
        } else {
            System.out.println("평균: 0.0");
        }

        // 방법 2.
        double avg2 = list
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0)
                ;
        System.out.println("평균: " + avg2);

        // 방법 3.
        list
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .ifPresent(a -> System.out.println("평균: " + a))
        ;
    }
}
```

#### 커스텀 집계 (reduce())

<table>
    <tr>
        <th>인터페이스</th>
        <th>리턴 타입</th>
        <th>메소드</th>
    </tr>
    <tr>
        <td rowspan="2">Stream</td>
        <td>Optional&lt;T&gt;</td>
        <td>reduce(BinaryOperator&lt;T&gt; accumulator)</td>
    </tr>
    <tr>
        <td>T</td>
        <td>reduce(T identity, BinaryOperator&lt;T&gt; accumulator)</td>
    </tr>
    <tr>
        <td rowspan="2">IntStream</td>
        <td>OptionalInt</td>
        <td>reduce(IntBinaryOperator accumulator)</td>
    </tr>
    <tr>
        <td>int</td>
        <td>reduce(int identity, IntBinaryOperator accumulator)</td>
    </tr>
    <tr>
        <td rowspan="2">LongStream</td>
        <td>OptionalLong</td>
        <td>reduce(LongBinaryOperator accumulator)</td>
    </tr>
    <tr>
        <td>long</td>
        <td>reduce(long identity, LongBinaryOperator accumulator)</td>
    </tr>
    <tr>
        <td rowspan="2">DoubleStream</td>
        <td>OptionalDouble</td>
        <td>reduce(DoubleBinaryOperator accumulator)</td>
    </tr>
    <tr>
        <td>double</td>
        <td>reduce(double identity, DoubleBinaryOperator accumulator)</td>
    </tr>
</table>

```java
public class ReductionExample {
    public static void main(String[] args) {
        List<Student6> student6List = Arrays.asList(
                new Student6("홍길동", 92),
                new Student6("김자바", 95),
                new Student6("신용권", 88)
        );

        int sum1 = student6List
                .stream()
                .mapToInt(Student6::getScore)
                .sum()
                ;

        int sum2 = student6List
                .stream()
                .map(Student6::getScore)
                .reduce((a, b) -> a + b)
                .get()
                ;

        int sum3 = student6List
                .stream()
                .map(Student6::getScore)
                .reduce(0, (a, b) -> a + b)
                ;
        System.out.println(sum1);
        System.out.println(sum2);
        System.out.println(sum3);
    }
}
```
#### 수집 (collect())
    요소들을 필터링 또는 매핑한 후 요소들을 수집하는 최종 처리 메소드인 collect()를 제공
    이 메소드를 이용하면 필요한 요소만 컬렉션으로 담을 수 있고, 
    요소들을 그룹핑한 후 집계(리덕선) 할 수 있다.
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>인터페이스</th>
    </tr>
    <tr>
        <td>R</td>
        <td>collect(Collector&lt;T, A, R&gt; collector)</td>
        <td>Stream</td>
    </tr>
</table>
    
    매개값인 Collector 는 어떤 요소를 어떤 컬렉션에 수집할 것인지 결정
    타입 파라미터 T, 누적기 A, 저장될 컬렉션 R
    T를 A누적기가 R에 저장
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>Collectors 의 정적 메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, List&lt;T&gt;&gt;</td>
        <td>toList()</td>
        <td>T 를 List 에 저장</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, Set&lt;T&gt;&gt;</td>
        <td>toSet()</td>
        <td>T 를 Set 에 저장</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, Collection&lt;T&gt;&gt;</td>
        <td>toCollection(<br>Supplier&lt;Collection&lt;T&gt;&gt;<br>)</td>
        <td>T 를 Supplier 가 제공한<br>
        Collection 에 저장</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, Map&lt;T&gt;&gt;</td>
        <td>toMap(<br>Function&lt;T, K&gt; keyMapper,<br>
        Function&lt;T, U&gt; valueMapper<br>)</td>
        <td>T를 K와 U로 매핑시켜 K를 키로, U를 값으로 Map 에 저장</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, <br>
        ConcurrentMap&lt;K, U&gt;&gt;</td>
        <td>toConcurrentMap(<br>Function&lt;T, K&gt; keyMapper,<br>
        Function&lt;T, U&gt; valueMapper<br>)</td>
        <td>T를 K와 U로 매핑시켜 K를 키로, U를 값으로 ConcurrentMap 에 저장</td>
    </tr>
</table>


```java
public class ToListExample {
    public static void main(String[] args) {
        List<Student7> totalList = Arrays.asList(
                new Student7("홍길동", 10, Student7.Sex.MALE),
                new Student7("김수애", 6, Student7.Sex.FEMALE),
                new Student7("신용권", 10, Student7.Sex.MALE),
                new Student7("박수미", 6, Student7.Sex.FEMALE)
        );

        // 남학생 List 생성
        List<Student7> maleList = totalList
                .stream()
                .filter(s -> s.getSex() == Student7.Sex.MALE)
                .collect(Collectors.toList())
                ;
        maleList
                .stream()
                .forEach(s -> System.out.println(s.getName()))
        ;

        System.out.println();

        // 여학생 HashSet 생성
        Set<Student7> femaleSet = totalList
                .stream()
                .filter(s -> s.getSex() == Student7.Sex.FEMALE)
                .collect(Collectors.toCollection(HashSet::new))
                ;
        femaleSet
                .stream()
                .forEach(s -> System.out.println(s.getName()))
        ;
    }
}

class Student7 {
    public enum Sex {
        MALE,
        FEMALE,
        ;
    }

    public enum City {
        SEOUL,
        PUSAN,
        ;
    }

    private String name;
    private int score;
    private Sex sex;
    private City city;

    public Student7(String name, int score, Sex sex) {
        this.name = name;
        this.score = score;
        this.sex = sex;
    }

    public Student7(String name, int score, Sex sex, City city) {
        this(name, score, sex);
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Sex getSex() {
        return sex;
    }

    public City getCity() {
        return city;
    }
}
```

##### 사용자 정의 컨테이너에 수집하기

<table>
    <tr>
        <th>인터페이스</th>
        <th>리턴 타입</th>
        <th>메소드</th>
    </tr>
    <tr>
        <td>Stream</td>
        <td>R</td>
        <td>collect(Supplier&lt;R&gt;, BiConsumer&lt;R, ? super T&gt;, BiConsumer&lt;R, R&gt;)</td>
    </tr>
    <tr>
        <td>IntStream</td>
        <td>R</td>
        <td>collect(Supplier&lt;R&gt;, ObjIntConsumer&lt;R&gt;, BiConsumer&lt;R, R&gt;)</td>
    </tr>
    <tr>
        <td>LongStream</td>
        <td>R</td>
        <td>collect(Supplier&lt;R&gt;, ObjLongConsumer&lt;R&gt;, BiConsumer&lt;R, R&gt;)</td>
    </tr>
    <tr>
        <td>DoubleStream</td>
        <td>R</td>
        <td>collect(Supplier&lt;R&gt;, ObjDoubleConsumer&lt;R&gt;, BiConsumer&lt;R, R&gt;)</td>
    </tr>
</table>

    첫 번째 Supplier 는 요소들이 수집될 컨테이너 객체(R)를 생성하는 역할
    순차 처리(싱글 스레드) 스트림에서는 단 한번 Supplier가 실행되고 하나의 컨테이너 객체 생성
    병렬 처리(멀티 스레드) 스트림에서는 여러 번 Supplier가 실행되고 여러 컨테이너 객체 생성
    하지만 최종적으로 하나의 컨테이너 객체로 결합 (세 번째 항목)
    
    두 번째 XXXConsumer는 컨테이너 객체(R)에 요소(T)를 수집하는 역할
    스트림에서 요소를 컨테이너에 수집할 때마다 XXXConsumer 가 실행된다.
    
    세 번째 BiConsumer 는 컨테이너 객체(R)를 결합하는 역할
    순차 처리 스트림에서는 호출되지 않고,
    병렬 처리 스트림에서만 호출되어 스레드별로 생성된
    컨테이너 객체를 결합해서 최종 컨테이너 객체를 완성
    
```java
public class MaleStudent {
    private List<Student7> list;

    public MaleStudent() {
        list = new ArrayList<>();
        System.out.println("[" + Thread.currentThread().getName() + "] MaleStudent()");
    }

    public void accumulate(Student7 student7) {
        list.add(student7);
        System.out.println("[" + Thread.currentThread().getName() + "] accumulate()");
    }

    public void combine(MaleStudent other) {
        list.addAll(other.getList());
        System.out.println("[" + Thread.currentThread().getName() + "] combine()");
    }

    public List<Student7> getList() {
        return list;
    }
}

class MaleStudentExample {
    public static void main(String[] args) {
        List<Student7> totalList = Arrays.asList(
                new Student7("홍길동", 10, Student7.Sex.MALE),
                new Student7("김수애", 6, Student7.Sex.FEMALE),
                new Student7("신용권", 10, Student7.Sex.MALE),
                new Student7("박수미", 6, Student7.Sex.FEMALE)
        );

        MaleStudent maleStudent = totalList
                .stream()
                .filter(s -> s.getSex() == Student7.Sex.MALE)
                .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine)
                ;

        maleStudent.getList()
                .stream()
                .forEach(s -> System.out.println(s.getName()))
        ;

    }
}
```

###### 요소를 그룹핑해서 수집

<table>
    <tr>
        <th>리턴 타입</th>
        <th>Collectors 의 정적 메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, Map&lt;K, List&lt;T&gt;&gt;&gt;</td>
        <td>groupingBy(Function&lt;T, K&gt; classifier)</td>
        <td rowspan="2">T 를 K 로 매핑하고 K 키에 저장된 List 에 T를 저장한 Map 생성</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?,<br>
        ConcurrentMap&lt;K, List&lt;T&gt;&gt;&gt;</td>
        <td>groupingByConcurrent(<br>
        Function&lt;T, K&gt; classifier)</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, Map&lt;K, D&gt;&gt;</td>
        <td>groupingBy(Function&lt;T, K&gt; classifier,<br>
        Collector&lt;T, A, D&gt; collector)</td>
        <td rowspan="2">T 를 K 로 매핑하고 K 키에 저장된 D 객체에 T를 누적한 Map 생성</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?,<br>
        ConcurrentMap&lt;K, D&gt;&gt;</td>
        <td>groupingByConcurrent(<br>
        Function&lt;T, K&gt; classifier,<br>
        Collector&lt;T, A, D&gt; collector)</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, Map&lt;K, D&gt;&gt;</td>
        <td>groupingBy(<br>
        Function&lt;T, K&gt; classifier,<br>
        Supplier&lt;Map&lt;K, D&gt;&gt; mapFactory,<br>
        Collector&lt;T, A, D&gt; collector)</td>
        <td rowspan="2">T 를 K 로 매핑하고 Supplier 가 제공하는 Map 에서 K 키에 저장된 D 객체에 T를 누적</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?,<br>
        ConcurrentMap&lt;K, D&gt;&gt;</td>
        <td>groupingByConcurrent(<br>
        Function&lt;T, K&gt; classifier,<br>
        Supplier&lt;Map&lt;K, D&gt;&gt; mapFactory,<br>
        Collector&lt;T, A, D&gt; collector)</td>
    </tr>
</table>

```java
public class GroupingByExample {
    public static void main(String[] args) {
        List<Student7> totalList = Arrays.asList(
                new Student7("홍길동", 10, Student7.Sex.MALE, Student7.City.SEOUL),
                new Student7("김수애", 6, Student7.Sex.FEMALE, Student7.City.SEOUL),
                new Student7("신용권", 10, Student7.Sex.MALE, Student7.City.PUSAN),
                new Student7("박수미", 6, Student7.Sex.FEMALE, Student7.City.PUSAN)
        );

        Map<Student7.Sex, List<Student7>> mapBySex = totalList
                .stream()
                .collect(Collectors.groupingBy(Student7::getSex));

        System.out.println("[ 남학생 ]");
        mapBySex
                .get(Student7.Sex.MALE)
                .stream()
                .forEach(s -> System.out.print(s.getName() + " "));

        System.out.println("\n[ 여학생 ]");
        mapBySex
                .get(Student7.Sex.FEMALE)
                .stream()
                .forEach(s -> System.out.print(s.getName() + " "));

        System.out.println();

        Map<Student7.City, List<String>> mapByCity = totalList
                .stream()
                .collect(Collectors.groupingBy(Student7::getCity,
                        Collectors.mapping(Student7::getName, Collectors.toList())));

        System.out.println("\n[ 서울 ]");
        mapByCity
                .get(Student7.City.SEOUL)
                .stream()
                .forEach(n -> System.out.print(n + " "));

        System.out.println("\n[ 부산 ]");
        mapByCity
                .get(Student7.City.PUSAN)
                .stream()
                .forEach(n -> System.out.print(n + " "));
    }
}
```
    성별로 매핑
    1. Stream<Student7> totalStream = totalList.stream();
    2. Function<Student7, Student7.Sex> classifier = Student7::getSex;
    3. Collector<Student7, ?, Map<Student7.Sex, List<Student7>>> collector =
        Collectors.groupingBy(classifier);
    4. Map<Student7.Sex, List<Student7>> mapBySex = totalStrea.collect(collector);
    
    도시로 매핑
    1. Stream<Student7> totalStream = totalList.stream();
    2. Function<Student7, Student7.City> classifier = Student7::getCity;
    3. Function<Student7, String> mapper = Student::getName;
    4. Collector<String, ?, List<String>> collector1 = Collectors.toList();
    5. Collector<Student7, ?, List<String>> collector2 = Collectors.mapping(mapper, collector1);
    6. Collector<Student7, ?, Map<Student7.City, List<String>>> collector3 =
        Collectors.groupingBy(classifier, collector2);
    7. Map<Student7.City, List<String>> mapByCity = totalStream.collect(collector3);

###### 그룹핑 후 매핑 및 집계

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, R&gt;</td>
        <td>mapping(<br>
        Function&lt;T, U&gt; mapper,<br>
        Collector&lt;U, A, R&gt; collector)</td>
        <td>T 를 U 로 매핑한 후, U 를 R 에 수집</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, Double&gt;</td>
        <td>averagingDouble(<br>
        ToDoubleFunction&lt;T&gt; mapper)</td>
        <td>T 를 Double 로 매핑한 후, Double 의 평균값을 산출</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, Long&gt;</td>
        <td>counting()</td>
        <td>T 의 카운팅 수를 산출</td>
    </tr>
    <tr>
        <td>Collector<br>
        &lt;CharSequence, ?, String&gt;</td>
        <td>joining(CharSequence delimiter)</td>
        <td>CharSequence 를 구분자<br>
        (delimiter)로 연결한 String 을 산출</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, Optional&lt;T&gt;&gt;</td>
        <td>maxBy(<br>
        Comparator&lt;T&gt; comparator)</td>
        <td>Comparator 를 이용해서 최대 T 를 산출</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, Optional&lt;T&gt;&gt;</td>
        <td>minBy(<br>
        Comparator&lt;T&gt; comparator)</td>
        <td>Comparator 를 이용해서 최소 T 를 산출</td>
    </tr>
    <tr>
        <td>Collector&lt;T, ?, Integer&gt;</td>
        <td>summingInt(ToIntFunction)<br>
        summingLong(ToLongFunction)<br>
        summingDouble(ToDoubleFunction)</td>
        <td>Int, Long, Double 타입의 합계 산출</td>
    </tr>
</table>

```java
public class GroupingAndReductionExample {
    public static void main(String[] args) {
        List<Student7> totalList = Arrays.asList(
                new Student7("홍길동", 10, Student7.Sex.MALE),
                new Student7("김수애", 6, Student7.Sex.FEMALE),
                new Student7("신용권", 10, Student7.Sex.MALE),
                new Student7("박수미", 6, Student7.Sex.FEMALE)
        );

        // 성별로 평균 점수를 저장하는 Map 얻기
        Map<Student7.Sex, Double> mapBySex = totalList
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Student7::getSex,
                                Collectors.averagingDouble(Student7::getScore)
                        )
                );
        System.out.println("남학생 평균 점수: " + mapBySex.get(Student7.Sex.MALE));
        System.out.println("여학생 평균 점수: " + mapBySex.get(Student7.Sex.FEMALE));

        // 성별을 쉼표로 구분한 이름을 저장하는 Map 얻기
        Map<Student7.Sex, String> mapByName = totalList
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Student7::getSex,
                                Collectors.mapping(
                                        Student7::getName,
                                        Collectors.joining(",")
                                )
                        )
                );

        System.out.println("남학생 전체 이름: " + mapByName.get(Student7.Sex.MALE));
        System.out.println("여학생 전체 이름: " + mapByName.get(Student7.Sex.FEMALE));
    }
}
```
    // 성별로 평균점수 매핑
    1. Stream<Student7> totalStream = totalList.stream();
    2. Function<Student7, Student7.Sex> classifier = Student7::getSex;
    3. ToDoubleFunction<Student7> mapper = Student::getScore;
    4. Collector<Student7, ?, Double> collector1 = Collectors.averagingDouble(mapper);
    5. Collector<Student7, ?, Map<Student7.Sex, Double>> collector2 = 
        Collectors.groupingBy(classifier, collector1);
    6. Map<Student7.Sex, Double> mapBySex = totalStream.collect(collector2);
    
#### 병렬 처리
    데이터 병렬성
        전체 데이터를 쪼개어 서브 데이터들로 만들고
        이 데이터들을 병렬 처리해서 작업을 빨리 끝내는것
    작업 병렬성
        서로 다른 작업을 병렬 처리

###### 포크조인 프레임워크
    병렬 스트림을 이용하면 런타임 시에 포크조인 프레임워크가 동작
    
    포크 단계에서 전체 데이터를 서브 데이터로 분리한다.
    서브 데이터를 병렬로 처리한다.
    조인 단계에서 서브 결과를 결합해서 최종 결과를 만들어 낸다.
    
    포크조인 프레임워크는 포크와 조인 기능 이외에 스데르풀인 ForkJoinPool을 제공
    
###### 병렬 스트림 생성

<table>
    <tr>
        <th>인터페이스</th>
        <th>리턴 타입</th>
        <th>메소드</th>
    </tr>
    <tr>
        <td>java.util.Collection</td>
        <td>Stream</td>
        <td>parallelStream()</td>
    </tr>
    <tr>
        <td>java.util.Stream.Stream<br>
        java.util.Stream.IntStream<br>
        java.util.Stream.LongStream<br>
        java.util.Stream.DoubleStream<br></td>
        <td>Stream<br>
        IntStream<br>
        LongStream<br>
        DoubleStream<br></td>
        <td>parallel()</td>
    </tr>
</table>

```java
public class MaleStudentExample2 {
    public static void main(String[] args) {
        List<Student7> totalList = Arrays.asList(
                new Student7("홍길동", 10, Student7.Sex.MALE),
                new Student7("김수애", 6, Student7.Sex.FEMALE),
                new Student7("신용권", 10, Student7.Sex.MALE),
                new Student7("박수미", 6, Student7.Sex.FEMALE)
        );
        MaleStudent maleStudent = totalList
                .parallelStream()
                .filter(s->s.getSex()== Student7.Sex.MALE)
                .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine)
                ;

        maleStudent.getList()
                .stream()
                .forEach(s-> System.out.println(s.getName()));
    }
}
```

    실행 결과 main 스레드와 ForkJoinPool 에서 3개의 스레드 사용
    각 스레드는 남학생을 누적시킬 MaleStudent 객체를 각각 생성 ( 생성자 4번 )
    남학생은 2명밖에 없으므로 accumulate()는 2번 실행
    누적이 완료된 4개의 MaleStudent는 첫번째 결합에서 2개를 1개씩으로 ( combine() 2번 )
    두번째 결합에서 2개를 1개로 ( combine() 1번 ) 실행 으로 총 3번 실행

###### 병렬 처리 성능
    병렬처리에 영향을 미치는 3가지
    
    1. 요소의 수와 요소당 처리 시간
    컬렉션의 요소의 수가 적고 요소당 처리 시간이 짧으면 
    순차 처리가 병렬 처리보다 빠를 수 있다.
    
    2. 스트림 소스의 종류
    ArrayList, Array( 배열 )은 인덱스로 요소를 관리하기 때문에
    포크 단계에서 요소를 쉽게 분리할 수 있어 병렬 처리 시간이 절약
    그러나 HashSet, TreeSet, LinkedList 는 요소 분리가 쉽지않아
    ArrayList, Array 보다는 상대적으로 병렬 처리가 늦다.
    
    3. 코어 수
    싱글 코어 CPU 일 경우에는 순차 철기가 빠르다.
    
###### 순차적처리와 병렬처리
```java
public class SequencialVsParallelExample {
    public static void work(int value) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    public static long testSequencial(List<Integer> list) {
        long start = System.nanoTime();
        list.stream().forEach(a -> work(a));
        long end = System.nanoTime();
        return end - start;
    }

    public static long testParallel(List<Integer> list) {
        long start = System.nanoTime();
        list.parallelStream().forEach(a -> work(a));
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        long timeSequencial = testSequencial(list);
        long timeParallel = testParallel(list);
        System.out.println(timeSequencial);
        System.out.println(timeParallel);
    }
}
```
###### ArrayList, LinkedList 병렬처리
```java
public class ArrayListVsLinkedListExample {
    public static void work(int value) {
    }

    public static long testParallel(List<Integer> list) {
        long start = System.nanoTime();
        list.parallelStream().forEach(a -> work(a));
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 1000000; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        long arrayListParallel = testParallel(arrayList);
        long linkedListParallel = testParallel(linkedList);

        arrayListParallel = testParallel(arrayList);
        linkedListParallel = testParallel(linkedList);

        System.out.println(arrayListParallel);
        System.out.println(linkedListParallel);
    }
}
```
    
### 61. IO 패키지 입력 스트림과 출력 스트림

<table>
    <tr>
        <th>java.io 패키지의 주요 클래스</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>File</td>
        <td>파일 시스템의 파일 정보를 얻기 위한 클래스</td>
    </tr>
    <tr>
        <td>Console</td>
        <td>콘솔로부터 문자를 입출력하기 위한 클래스</td>
    </tr>
    <tr>
        <td>InputStream / OutputStream</td>
        <td>바이트 단위 입출력을 위한 최상위 입출력 스트림 클래스</td>
    </tr>
    <tr>
        <td>FileInputStream / FileOutputStream<br>
        DataInputStream / DataOutputStream<br>
        ObjectInputStream / ObjectOutputStream<br>
        PrintStream<br>
        BufferedInputStream / BufferedOutputStream</td>
        <td>바이트 단위 입출력을 위한 하위 스트림 클래스</td>
    </tr>
    <tr>
        <td>Reader / Writer</td>
        <td>문자 단위 입출력을 위한 최상위 입출력 스트림 클래스</td>
    </tr>
    <tr>
        <td>FileReader / FileWriter<br>
        InputStreamReader / OutputStreamWriter<br>
        PrintWriter<br>
        BufferedReader / BufferedWriter</td>
        <td>문자 단위 입출력을 위한 하위 스트림 클래스</td>
    </tr>
</table>

    스트림클래스는 크게 바이트 기반 스트림 클래스와 문자 기반 스트림 클래스로 나뉜다.

<table>
    <tr>
        <th rowspan="2">구분</th>
        <th colspan="2">바이트 기반 스트림</th>
        <th colspan="2">문자 기반 스트림</th>
    </tr>
    <tr>
        <th>입력 스트림</th>
        <th>출력 스트림</th>
        <th>입력 스트림</th>
        <th>출력 스트림</th>
    </tr>
    <tr>
        <td>최상위 클래스</td>
        <td>InputStream</td>
        <td>OutputStream</td>
        <td>Reader</td>
        <td>Writer</td>
    </tr>
    <tr>
        <td>하위 클래스</td>
        <td>XXXInputStream</td>
        <td>XXXOutputStream</td>
        <td>XXXReader</td>
        <td>XXXWriter</td>
    </tr>
</table>

###### InputStream

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>int</td>
        <td>read()</td>
        <td>입력 스트림으로부터 1바이트를 읽고 읽은 바이트를 리턴한다.</td>
    </tr>
    <tr>
        <td>int</td>
        <td>read(byte[] b)</td>
        <td>입력 스트림으로부터 읽은 바이트들을 매개값으로 주어진 바이트 배열<br>
        b에 저장하고 실제로 읽은 바이트 수를 리턴한다.</td>
    </tr>
    <tr>
        <td>int</td>
        <td>read(byte[] b, int off, int len)</td>
        <td>입력 스트림으로부터 len 개의 바이트만큼 읽고 매개값으로 주어진<br>
        바이트 배열 b[off] 부터 len 개까지 저장한다. 그리고 실제로 읽은<br>
        바이트 수인 len 개를 리턴한다. 만약 len 개를 모두 읽지 못하면 실제로<br>
        읽은 바이트 수를 리턴한다.</td>
    </tr>
    <tr>
        <td>void</td>
        <td>close()</td>
        <td>사용한 시스템 자원을 반납하고 입력 스트림을 닫는다.</td>
    </tr>
</table>

    read() 메소드는 1바이트를 읽고 4바이트 int 타입으로 리턴함.
    따라서 리턴된 4바이트 끝의 1바이트에만 데이터가 들어있음.
    더 이상 입력 스트림으로부터 바이트를 읽을 수 없다면 -1을 리턴

###### OutputStream

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>void</td>
        <td>write(int b)</td>
        <td>출력 스트림으로 1바이트를 보낸다( b의 끝 1바이트 )</td>
    </tr>
    <tr>
        <td>void</td>
        <td>write(byte[] b)</td>
        <td>출력 스트림으로 주어진 바이트 배열 b의 모든 바이트를 보낸다.</td>
    </tr>
    <tr>
        <td>void</td>
        <td>write(byte[] b, int off, int len)</td>
        <td>출력 스트림으로 주어진 바이트 배열 b[off] 부터 len 개까지의 바이트를 보낸다.</td>
    </tr>
    <tr>
        <td>void</td>
        <td>flush()</td>
        <td>버퍼에 잔류하는 모든 바이트를 출력한다.</td>
    </tr>
    <tr>
        <td>void</td>
        <td>close()</td>
        <td>사용한 시스템 자원을 반납하고 출력 스트림을 닫는다.</td>
    </tr>
</table>

    write(int b) 메소드는 매개 변수로 주어진 int 값에서 끝에 있는 
    1바이트만 출력 스트림으로 보낸다.
    
###### Reader
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>int</td>
        <td>read()</td>
        <td>입력 스트림으로부터 한 개의 문자를 읽고 리턴한다</td>
    </tr>
    <tr>
        <td>int</td>
        <td>read(char[] cbuf)</td>
        <td>입력 스트림으로부터 읽은 문자들을 매개값으로 주어진 문자 배열 cbuf에 저장하고<br>
        실제로 읽은 문자 수를 리턴한다</td>
    </tr>
    <tr>
        <td>int</td>
        <td>read(char[] cbuf, int off, int len)</td>
        <td>입력 스트림으로부터 len 개의 문자를 읽고 매개값으로 주어진<br>
        문자 배열 cbuf[off] 부터 len 개까지 저장한다. 그리고 실제로 읽은<br>
        문자 수인 len 개를 리턴한다.</td>
    </tr>
    <tr>
        <td>void</td>
        <td>close()</td>
        <td>사용한 시스템 자원을 반납하고 입력 스트림을 닫는다.</td>
    </tr>
</table>

    read() 메소드는 입력 스트림으로부터 한 개의 문자( 2바이트 )를 읽고 
    4바이트 int 타입으로 리턴
    따라서 리턴된4바이트 중 끝에 있는 2바이트에 문자 데이터가 들어 있다.
    더이상 입력 스트림으로부터 문자를 읽을 수 없다면 -1을 리턴
    
###### Writer

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>void</td>
        <td>write(int c)</td>
        <td>출력 스트림으로 1바이트를 보낸다( c의 끝 2바이트 )</td>
    </tr>
    <tr>
        <td>void</td>
        <td>write(char[] cbuf)</td>
        <td>출력 스트림으로 주어진 바이트 배열 cbuf의 모든 바이트를 보낸다.</td>
    </tr>
    <tr>
        <td>void</td>
        <td>write(char[] cbuf, int off, int len)</td>
        <td>출력 스트림으로 주어진 바이트 배열 cbuf[off] 부터 len 개까지의 바이트를 보낸다.</td>
    </tr>
    <tr>
        <td>void</td>
        <td>write(String str)</td>
        <td>출력 스트림으로 주어진 문자열을 전부 보낸다.</td>
    </tr>
    <tr>
        <td>void</td>
        <td>write(String str, int off, int len)</td>
        <td>출력 스트림으로 주어진 문자열 off 순번부터 len 개까지의 문자를 보낸다.</td>
    </tr>
    <tr>
        <td>void</td>
        <td>flush()</td>
        <td>버퍼에 잔류하는 모든 문자열을 출력한다.</td>
    </tr>
    <tr>
        <td>void</td>
        <td>close()</td>
        <td>사용한 시스템 자원을 반납하고 출력 스트림을 닫는다.</td>
    </tr>
</table>

        write(int c) 메소드는 매개 변수로 주어진 int 값에서 끝에 있는 
        2바이트만 출력 스트림으로 보낸다.

### 62. 콘솔 입출력
    콘솔로부터 데이터를 입력 받을 때 System.in
    콘솔로부터 데이터를 출력 할 때 System.out
    콘솔에 에러를 출력할 때 System.err
    
```java
public class SystemInExample2 {
    public static void main(String[] args) throws Exception {
        InputStream in = System.in;
        byte[] datas = new byte[100];
        System.out.print("이름: ");
        int nameBytes = in.read(datas);
        // length 에서 -2 는 enter 키에 해당하는 캐리지 리턴(13)과 라인 피드(10)을 제외하기 위함
        // 자동으로 제외?
        String name = new String(datas, 0, nameBytes);
        System.out.print("하고싶은말: ");
        int commentBytes = in.read(datas);
        String comment = new String(datas, 0, commentBytes);

        System.out.println("입력한 이름: " + name);
        System.out.println("입력한 하고싶은말:  " + comment);
    }
}
```

```java
public class SystemOutExample {
    public static void main(String[] args) throws Exception {
        OutputStream os = System.out;
        for (byte b = 48; b < 58; b++) {
            os.write(b);
        }
        os.write(10);
        for (byte b = 97; b < 123; b++) {
            os.write(b);
        }
        os.write(10);

        String korean = "가나다라마바사아자차";
        byte[] koreanBytes = korean.getBytes();
        os.write(koreanBytes);

        os.flush();
    }
}
```


#### Console 클래스
    콘솔에서 입력받은 문자열을 쉽게 읽을 수 있도록 java.io.Console 클래스를 제공
    Console console = System.console();
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>String</td>
        <td>readLine()</td>
        <td>Enter 키를 입력하기 전의 모든 문자열을 읽음</td>
    </tr>
    <tr>
        <td>char[]</td>
        <td>readPassword()</td>
        <td>키보드 입력 문자를 콘솔에 보여주지 않고 문자열을 읽음</td>
    </tr>
</table>

```java
public class ConsoleExample {
    public static void main(String[] args) {
        Console console = System.console();
        System.out.print("아이디: ");
        String id = console.readLine();
        System.out.print("패스워드: ");
        char[] pwd = console.readPassword();
        String strPwd = new String(pwd);

        System.out.println("---------");
        System.out.println(id);
        System.out.println(strPwd);
    }
}
```
    ★ ConsoleExample.java Test
        System.console() 메소드는 IDE (intellij) 에서 실행하면
        NullPointerException 반환
        windows command 창에서 실행시 출력가능
        그러나 한글이 깨짐 ( java 파일에서 작성해놓은 )
        chcp 949 한글 환경
        명령어 : java -Dfile.encoding=UTF-8 [FileName].java
        로 명령하면 한글깨짐없이 실행됨

#### Scanner 클래스
    Console 클래스는 콘솔로부터 문자열을 읽을 순 있지만 기본 타입은
    바로 읽을 수 없다.( 형변환 필요함 )
    Scanner scanner = new Scanner(System.in)
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>boolean</td>
        <td>nextBoolean()</td>
        <td>boolean 값을 읽는다</td>
    </tr>
    <tr>
        <td>byte</td>
        <td>nextByte()</td>
        <td>byte 값을 읽는다</td>
    </tr>
    <tr>
        <td>short</td>
        <td>nextShort()</td>
        <td>short 값을 읽는다</td>
    </tr>
    <tr>
        <td>int</td>
        <td>nextInt()</td>
        <td>int 값을 읽는다</td>
    </tr>
    <tr>
        <td>long</td>
        <td>nextLong()</td>
        <td>long 값을 읽는다</td>
    </tr>
    <tr>
        <td>float</td>
        <td>nextFloat()</td>
        <td>float 값을 읽는다</td>
    </tr>
    <tr>
        <td>double</td>
        <td>nextDouble()</td>
        <td>double 값을 읽는다</td>
    </tr>
    <tr>
        <td>String</td>
        <td>nextLine()</td>
        <td>String 값을 읽는다</td>
    </tr>    
</table>

```java
public class ScannerExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("문자열 입력: ");
        String inputString = scanner.nextLine();
        System.out.println(inputString);
        System.out.println();

        System.out.print("정수 입력: ");
        int inputInt = scanner.nextInt();
        System.out.println(inputInt);
        System.out.println();

        System.out.print("실수 입력: ");
        double inputDouble = scanner.nextDouble();
        System.out.println(inputDouble);
        System.out.println();
    }
}
```

#### File 클래스
    File 클래스는 파일 크기, 파일 속성, 파일 이름 등의 정보를 얻어내는 기능과
    파일 생성 및 삭제 기능을 제공, 
    디렉토리를 생성하고 디렉토리에 존재하는 파일 리스트를 얻어내는 기능
    File file = new File("C:\\Temp\\file.txt");
    boolean exist = file.exists();
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>boolean</td>
        <td>createNeweFile()</td>
        <td>새로운 파일을 생성</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>mkdir()</td>
        <td>새로운 디렉토리를 생성</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>mkdirs()</td>
        <td>경로상에 없는 모든 디렉토리를 생성</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>delete()</td>
        <td>파일 또는 디렉토리 삭제</td>
    </tr>
</table>

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>boolean</td>
        <td>canExecute()</td>
        <td>실행할 수 있는 파일인지 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>canRead()</td>
        <td>읽을 수 있는 파일인지 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>canWrite()</td>
        <td>수정 및 저장할 수 있는 파일인지 여부</td>
    </tr>
    <tr>
        <td>String</td>
        <td>getName()</td>
        <td>파일의 이름을 리턴</td>
    </tr>
    <tr>
        <td>String</td>
        <td>getParent()</td>
        <td>부모 디렉토리를 리턴</td>
    </tr>
    <tr>
        <td>File</td>
        <td>getParentFile()</td>
        <td>부모 디렉토리를 File 객체로 생성 후 리턴</td>
    </tr>
    <tr>
        <td>String</td>
        <td>getPath()</td>
        <td>전체 경로를 리턴</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isDirectory()</td>
        <td>디렉토리인지 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isFile()</td>
        <td>파일인지 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isHidden()</td>
        <td>숨김 파일인지 여부</td>
    </tr>
    <tr>
        <td>long</td>
        <td>lastModified()</td>
        <td>마지막 수정 날짜 및 시간을 리턴</td>
    </tr>
    <tr>
        <td>long</td>
        <td>length()</td>
        <td>파일 크기를 리턴</td>
    </tr>
    <tr>
        <td>String[]</td>
        <td>list()</td>
        <td>디렉토리에 포함된 파일 및 서브디렉토리 목록 전부를 String 배열로 리턴</td>
    </tr>
    <tr>
        <td>String[]</td>
        <td>list(FilenameFilter filter)</td>
        <td>디렉토리에 포함된 파일 및 서브디렉토리 목록 중에<br>
        FilenameFilter 에 맞는 것만 String 배열로 리턴</td>
    </tr>
    <tr>
        <td>File[]</td>
        <td>listFiles()</td>
        <td>디렉토리에 포함된 파일 및 서브디렉토리 목록 전부를 File 배열로 리턴</td>
    </tr>
    <tr>
        <td>File[]</td>
        <td>listFiles(FilenameFilter filter)</td>
        <td>디렉토리에 포함된 파일 및 서브디렉토리 목록 중에<br>
        FilenameFilter 에 맞는 것만 File 배열로 리턴</td>
    </tr>
</table>

```java
public class FileExample {
    public static void main(String[] args) throws Exception {
        File dir = new File("C:/Temp/Dir");
        File file1 = new File("C:/Temp/file1.txt");
        File file2 = new File("C:/Temp/file2.txt");

        // 파일 경로를 URI 객체로 생성하서 매개값으로 제공
        File file3 = new File(new URI("file:///C:/Temp/file3.txt"));

        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file1.exists()) {
            file1.createNewFile();
        }
        if (!file2.exists()) {
            file2.createNewFile();
        }
        if (!file3.exists()) {
            file3.createNewFile();
        }

        File temp = new File("C:/Temp");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
        File[] contents = temp.listFiles();
        System.out.println("\t날짜\t\t시간\t\t형태\t\t크기\t\t이름");
        for (File file : contents) {
            System.out.print(sdf.format(file.lastModified()));
            if (file.isDirectory()) {
                System.out.print("\t<DIR>\t\t\t" + file.getName());
            } else {
                System.out.print("\t\t\t" + file.length() + "\t" + file.getName());
            }
            System.out.println();
        }
    }
}
```

#### FileInputStream
    FileInputStream 클래스는 파일로부터 바이트 단위로 읽어들이 때 사용하는 바이트 기반 입력 스트림이다.
    
    FileInputStream fis = new FileInputStream("C:/Temp/image.gif");
    
    File file = new File("C:/Temp/image.gif"); 
    FileInputStream fis = new FileInputStream(file);
    
    FileInputStream 객체가 생성 될때 파일과 직접 연결이 되는데,
    만약 파일이 존재하지 않으면 FileNotFoundException 을 발생

```java
public class FileInputStreamExample {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream(
                "D:\\_Project\\thisisjava\\src\\examples/FileInputStreamExample.java"
        );
        int data;
        while((data=fis.read())!=-1){
            System.out.write(data);
        }
        fis.close();
    }
}
```

#### FileOutputStream
    FileOutputStream 클래스는 바이트 단위로 데이터를 파일에 저장할 때 사용하는 바이트 기반 출력 스트림이다.
    
    FileOutputStream fis = new FileOutputStream("C:/Temp/image.gif");
    
    File file = new File("C:/Temp/image.gif"); 
    FileOutputStream fis = new FileOutputStream(file);
    
    주의할 점, 파일이 이미 존재할 경우 데이터를 출력하면 파일을 덮어쓰게 되므로
    기존 파일 끝에 데이터를 추가 할 경우 생성자 두 번째 매개값으로 true를 주면 된다.
    
    FileOutputStream fis = new FileOutputStream("C:/Temp/image.gif", true);
    FileOutputStream fis = new FileOutputStream(file, true);
    
    FileOutputStream 객체가 생성 될때 파일과 직접 연결이 되는데,
    만약 파일이 존재하지 않으면 FileNotFoundException 을 발생

```java
public class FileOutputStreamExample {
    public static void main(String[] args) throws Exception{
        String originalFileName =
                "D:\\_Project\\thisisjava\\src\\examples/goodPo.jpg";
        String targetFileName = "C:/Temp/goodPosition.jpg";

        File originalFile = new File(originalFileName);
        System.out.println(originalFile.exists());
        FileInputStream fis = new FileInputStream(originalFileName);
        FileOutputStream fos = new FileOutputStream(targetFileName);
        int readByteNo;
        byte[] readBytes= new byte[100];
        while((readByteNo=fis.read(readBytes))!=-1){
            fos.write(readBytes,0,readByteNo);
        }
        fos.flush();
        fos.close();
        fis.close();

        System.out.println("복사 완료");
    }
}
```

#### FileReader
    FileReader 클래스는 텍스트 파일을 프로그램으로 읽어들일 때 사용하는 문자 기반 스트림이다.
    텍스트가 아닌 파일은 읽을 수 없다.
    
    FileReader fr = new FileReader("C:/Temp/file.txt");
    
    File file = new File("C:/Temp/file.txt");
    FileReader fr = new FileReader(file);
    
    FileReader 객체가 생성 될때 파일과 직접 연결이 되는데,
    만약 파일이 존재하지 않으면 FileNotFoundException 을 발생
    
```java
public class FileReaderExample {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader(
                "D:\\_Project\\thisisjava\\src\\examples/FileReaderExample.java"
        );
        int readCharNo;
        char[] cbuf = new char[100];
        while ((readCharNo = fr.read(cbuf)) != -1) {
            String data = new String(cbuf, 0, readCharNo);
            System.out.print(data);
        }
        fr.close();
    }
}
```

#### FileWriter
    FileWriter 클래스는 데이터를 파일에 저장할 때 사용하는 문자 기반 스트림이다.
    텍스트가 아닌 파일은 저장할 수 없다.
    
    FileWriter fw = new FileWriter("C:/Temp/file.txt");
    
    File file = new File("C:/Temp/file.txt");
    FileWriter fr = new FileWriter(file);
    
    주의할 점, 파일이 이미 존재할 경우 데이터를 출력하면 파일을 덮어쓰게 되므로
    기존 파일 끝에 데이터를 추가 할 경우 생성자 두 번째 매개값으로 true를 주면 된다.
    
    FileWriter fr = new FileWriter("C:/Temp/file.txt", true);
    FileWriter fr = new FileWriter(file, true);
    
    FileWriter 객체가 생성 될때 파일과 직접 연결이 되는데,
    만약 파일이 존재하지 않으면 FileNotFoundException 을 발생
    
```java
public class FileWriterExample {
    public static void main(String[] args) throws Exception {
        File file = new File("C:/Temp/file.txt");
        FileWriter fw = new FileWriter(file, true);
        fw.write("FileWriter는 한글로 된 " + "\r\n");
        fw.write("문자열을 바로 출력할 수 있다." + "\r\n");
        fw.flush();
        fw.close();
    }
}
```

### 63. 보조 스트림
    다른 스트림과 연결되어 여러 가지 편리한 기능을 제공해주는 스트림
    필터(filter) 스트림이라고도 함
    InputStream, FileInputStream, Reader, FileReader
    OutputStream, FileOutputStream, Writer, FileWriter 등에 연결해서
    입출력을 수행한다.
    보조 스트림은 문자 변환, 입출력 성능 향상, 기본 데이터 타입 입출력, 객체 입출력
    등의 기능을 제공한다.
    
    보조스트림 변수 = new 보조스트림( 연결스트림 );
    e.g ) InputStream is = System.in;
          InputStreamReader reader = new InputStreamReader(is);
          BufferedReader br = new BufferedReader(reader);

#### 문자 변환 보조 스트림
    소스 스트림이 바이트 기반 스트림 (Input, Output, FileInput, FileOutput) 이면서
    입출력 데이터가 문자라면 Reader 와 Writer로 변환해서 사용하는 것이 좋다.
    문자단위로 입출력되며, 문자셋의 종류를 지정할 수 있기 때문에
    
###### InputStreamReader
    바이트 입력 스트림에 연결되어 Reader로 변환시켜주는 보조스트림
    
```java
public class InputStreamReaderExample {
    public static void main(String[] args) throws Exception {
        InputStream is = System.in;
        Reader reader = new InputStreamReader(is);

        int readCharNo;
        char[] cbuf = new char[100];
        while ((readCharNo = reader.read(cbuf)) != -1) {
            String data = new String(cbuf, 0, readCharNo);
            System.out.println(data);
        }
        reader.close();
    }
}
```

###### OutputStreamWriter
    바이트 출력 스트림에 연결되어 Writer로 변환시켜주는 보조스트림
    
```java
public class OutputWriterExample {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("C:/Temp/file.txt");
        Writer writer = new OutputStreamWriter(fos);
        String data = "바이트 출력 스트림을 문자 출력 스트림을 변환";
        writer.write(data);

        writer.flush();
        writer.close();
        System.out.println("파일 저장이 끝났습니다.");
    }
}
```

#### 성능 향상 보조 스트림
    프로그램이 입출력 소스와 직접 작업하지 않고 중간에 메모리 버퍼(buffer)와
    작업함으로써 실행 성능을 향상시킬 수 있다.
    버퍼는 데이터가 쌓이기를 기다렸다가 꽉차게 되면 데이터를 한꺼번에 보낸다.
    
    바이트 기반 스트림 BufferedInputStream, BufferedOutputStream
    문자 기반 스트림 BufferedReader, BufferedWriter
    
###### BufferedInputStream, BufferedReader
    BufferedInputStream 바이트 입력 스트림에 연결되어 버퍼를 제공해주는 보조 스트림
    BufferedReader 문자 입력 스트림에 연결되어 버퍼를 제공해주는 보조 스트림
    
    BufferdInputStream bis = new BufferedInputStream( 바이트입력스트림 );
    BufferedReader br = new BufferedReader( 문자입력스트림 );
    
```java
public class BufferedInputStreamExample {
    public static void main(String[] args) throws Exception {
        long start = 0;
        long end = 0;
        FileInputStream fis1 = new FileInputStream(
                "D:\\_Project\\thisisjava\\src\\examples/goodPo.jpg"
        );
        start = System.currentTimeMillis();
        while (fis1.read() != -1) {

        }
        end = System.currentTimeMillis();
        System.out.println("buffered 사용 전: " + (end - start) + "ms");
        fis1.close();

        FileInputStream fis2 = new FileInputStream(
                "D:\\_Project\\thisisjava\\src\\examples/goodPo.jpg"
        );

        BufferedInputStream bis = new BufferedInputStream(fis2);
        start = System.currentTimeMillis();
        while (bis.read() != -1) {

        }
        end = System.currentTimeMillis();
        System.out.println("buffered 사용 후: " + (end - start) + "ms");
        bis.close();
        fis2.close();
    }
}
```

```java
public class BufferedReaderExample {
    public static void main(String[] args) throws Exception {
        InputStream is = System.in;
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);
        System.out.print("입력 : ");
        String lineString = br.readLine();
        System.out.println("출력 : " + lineString);
    }
}
```

###### BufferedOutputStream, BufferedWriter
    BufferedOutputStream 바이트 출력 스트림에 연결되어 버퍼를 제공해주는 보조 스트림
    BufferedWriter 문자 출력 스트림에 연결되어 버퍼를 제공해주는 보조 스트림
    
    BufferedOutputStream bos = new BufferedOutputStream( 바이트입력스트림 );
    BufferedWriter bw = new BufferedWriter( 문자입력스트림 );
    
```java
public class BufferedOutputStreamExample {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        int data = -1;
        long start = 0;
        long end = 0;
        fis = new FileInputStream(
                "D:\\_Project\\thisisjava\\src\\examples/goodPo.jpg"
        );
        bis = new BufferedInputStream(fis);
        fos = new FileOutputStream(
                "C:/Temp/goodPosition.jpg"
        );
        start = System.currentTimeMillis();
        while ((data = bis.read()) != -1) {
            fos.write(data);
        }
        fos.flush();
        end = System.currentTimeMillis();
        fos.close();
        bis.close();
        fis.close();
        System.out.println("사용하지 않았을 때 : " + (end - start) + "ms");

        fis = new FileInputStream(
                "D:\\_Project\\thisisjava\\src\\examples/goodPo.jpg"
        );
        bis = new BufferedInputStream(fis);
        fos = new FileOutputStream(
                "C:/Temp/goodPosition.jpg"
        );
        bos = new BufferedOutputStream(fos);
        start = System.currentTimeMillis();
        while ((data = bis.read()) != -1) {
            bos.write(data);
        }
        bos.flush();
        end = System.currentTimeMillis();
        bos.close();
        fos.close();
        bis.close();
        fis.close();
        System.out.println("사용 했을 때 : " + (end - start) + "ms");
    }
}
```

#### 기본 타입 입출력 보조 스트림
    바이트 스트림은 바이트 단위로 입출력하기 때문에 자바의 기본 데이터 타입인
    boolean, char, short, int, long, float, double 단위로 입출력할 수 없다.
    그러나 DataInputStream과 DataOutputStream 보조 스트림을 연결하면 
    기본 데이터 타입으로 입출력이 가능하다.
    
    DataInputStream dis = new DataInputStream( 바이트입력스트림 );
    DataOutputStream dos = new DataOutputStream( 바이트출력스트림 );
    
<table>
    <tr>
        <th colspan="2">DataInputStream</th>
        <th colspan="2">DataOutputStream</th>
    </tr>
    <tr>
        <td>boolean</td>
        <td>readBoolean()</td>
        <td>void</td>
        <td>writeBoolean(boolean v)</td>
    </tr>
    <tr>
        <td>byte</td>
        <td>readByte()</td>
        <td>void</td>
        <td>writeByte(int v)</td>
    </tr>
    <tr>
        <td>char</td>
        <td>readChar()</td>
        <td>void</td>
        <td>writeChar(int v)</td>
    </tr>
    <tr>
        <td>double</td>
        <td>readDouble()</td>
        <td>void</td>
        <td>writeDouble(double v)</td>
    </tr>
    <tr>
        <td>float</td>
        <td>readFloat()</td>
        <td>void</td>
        <td>writeFloat(float v)</td>
    </tr>
    <tr>
        <td>int</td>
        <td>readInt()</td>
        <td>void</td>
        <td>writeInt(int v)</td>
    </tr>
    <tr>
        <td>long</td>
        <td>readLong()</td>
        <td>void</td>
        <td>writeLong(long v)</td>
    </tr>
    <tr>
        <td>short</td>
        <td>readShort()</td>
        <td>void</td>
        <td>writeShort(int v)</td>
    </tr>
    <tr>
        <td>String</td>
        <td>readUTF()</td>
        <td>void</td>
        <td>writeUTF(String v)</td>
    </tr>                
</table>    
    
    주의 점, 데이터 타입의 크기가 모두 다르므로 DataOutputStream으로 출력한
    데이터를 다시 DataInputStream으로 읽어올 때는 출력한 순서와 동일한 순서로
    읽어야 한다.
    아니면 EOFException 에러
    
```java
public class DataInputOutputStreamExample {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("C:/Temp/primitive.dat");
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeUTF("홍길동");
        dos.writeDouble(95.5);
        dos.writeInt(1);

        dos.writeUTF("감자바");
        dos.writeDouble(90.3);
        dos.writeInt(2);

        dos.flush();
        dos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("C:/Temp/primitive.dat");
        DataInputStream dis = new DataInputStream(fis);

        for (int i = 0; i < 2; i++) {
            String name = dis.readUTF();
            double score = dis.readDouble();
            int order = dis.readInt();
            System.out.println(name + " " + score + " " + order);
        }
        dis.close();
        fis.close();
    }
}
```

#### 프린터 보조 스트림
    PrintStream 과 PrintWriter 프린터와 유사하게 출력하는 보조스트림
    
<table>
    <tr>
        <th colspan="4">PrintStream / PrintWriter</th>
    </tr>
    <tr>
        <td>void</td>
        <td>print(boolean b)</td>
        <td>void</td>
        <td>println(boolean b)</td>
    </tr>
    <tr>
        <td>void</td>
        <td>print(char c)</td>
        <td>void</td>
        <td>println(char c)</td>
    </tr>
    <tr>
        <td>void</td>
        <td>print(double d)</td>
        <td>void</td>
        <td>println(double d)</td>
    </tr>
    <tr>
        <td>void</td>
        <td>print(float f)</td>
        <td>void</td>
        <td>println(float f)</td>
    </tr>
    <tr>
        <td>void</td>
        <td>print(int i)</td>
        <td>void</td>
        <td>println(int i)</td>
    </tr>
    <tr>
        <td>void</td>
        <td>print(long l)</td>
        <td>void</td>
        <td>println(long l)</td>
    </tr>
    <tr>
        <td>void</td>
        <td>print(Object obj)</td>
        <td>void</td>
        <td>println(Object obj)</td>
    </tr>
    <tr>
        <td>void</td>
        <td>print(String s)</td>
        <td>void</td>
        <td>println(String s)</td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td>void</td>
        <td>println()</td>
    </tr>
</table>
    
```java
public class PrintStreamExample {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("C:/Temp/file.txt");
        PrintStream ps = new PrintStream(fos);

        ps.println("[ 프린터 보조 스트림 ]");
        ps.print("마치 ");
        ps.println("프린터가 출력하는 것처럼  ");
        ps.println("데이터를 출력합니다.");

        ps.flush();
        ps.close();
        fos.close();
    }
}
```

###### printf()
    형식화된 문자열을 추렭할 수 있도록 하기 위해 자바 5부터 추가된 메소드
    첫번째 매개값으로 형식화된 문자열을 지정, 
    두번째 매개값부터는 형식화된 문자열에 들어갈 값을 나열
    
    printf(String format, Object... args)
    format : %[argument_index$] [flags] [width] [.precision] conversion
    format 에서 % 와 conversion 은 필수적으로 작성
    argument_index$ : 적용할 매개값의 순번 1$는 첫 번째, 2$는 두 번째
    flags : 빈공간을 채우는 방법
    width : 전체 자릿수
    .precision : 소수 자릿수
    conversion : 변환문자로 정수(d), 실수(f), 문자열(s), 신간관련 문자 등 사용
    
<table>
    <tr>
        <th colspan="2">형식화된 문자</th>
        <th>설명</th>
        <th>출력 형태</th>
    </tr>
    <tr>
        <td rowspan="4">정수</td>
        <td>%d</td>
        <td>정수</td>
        <td>123</td>
    </tr>
    <tr>
        <td>%6d</td>
        <td>6자리 정수, 왼쪽 빈자리 공백</td>
        <td>___123</td>
    </tr>
    <tr>
        <td>%-6d</td>
        <td>6자리 정수, 오른쪽 빈자리 공백</td>
        <td>123___</td>
    </tr>
    <tr>
        <td>%06d</td>
        <td>6자리 정수, 왼쪽 빈자리 0 채움</td>
        <td>000123</td>
    </tr>
    <tr>
        <td rowspan="3">실수</td>
        <td>%10.2f</td>
        <td>소수점 이상 7자리, 소수점 이하 2자리. 왼쪽 빈자리 공백</td>
        <td>___123.45</td>
    </tr>
    <tr>
        <td>%-10.2f</td>
        <td>소수점 이상 7자리, 소수점 이하 2자리. 오른쪽 빈자리 공백</td>
        <td>123.45___</td>
    </tr>
    <tr>
        <td>%010.2f</td>
        <td>소수점 이상 7자리, 소수점 이하 2자리. 왼쪽 빈자리 0 채움</td>
        <td>0000123.45</td>
    </tr>
    <tr>
        <td rowspan="3">문자열</td>
        <td>%s</td>
        <td>문자열</td>
        <td>abc</td>
    </tr>
    <tr>
        <td>%6s</td>
        <td>6자리 문자열. 왼쪽 빈자리 공백</td>
        <td>___abc</td>
    </tr>
    <tr>
        <td>%-6s</td>
        <td>6자리 문자열. 오른쪽 빈자리 공백</td>
        <td>abc___</td>
    </tr>
    <tr>
        <td rowspan="9">날짜</td>
        <td>%tF</td>
        <td>%tY-%tm-%td</td>
        <td>2010-01-06</td>
    </tr>
    <tr>
        <td>%tY</td>
        <td>4자리 년</td>
        <td>2010</td>
    </tr>
    <tr>
        <td>%ty</td>
        <td>2자리 년</td>
        <td>10</td>
    </tr>
    <tr>
        <td>%tm</td>
        <td>2자리 월</td>
        <td>01</td>
    </tr>
    <tr>
        <td>%td</td>
        <td>2자리 일</td>
        <td>06</td>
    </tr>
    <tr>
        <td>%tH</td>
        <td>2자리 시(0~23)</td>
        <td>08</td>
    </tr>
    <tr>
        <td>%tl</td>
        <td>시(0~12)</td>
        <td>8</td>
    </tr>
    <tr>
        <td>%tM</td>
        <td>2자리 분</td>
        <td>06</td>
    </tr>
    <tr>
        <td>%tS</td>
        <td>2자리 초</td>
        <td>24</td>
    </tr>
    <tr>
        <td rowspan="3">특수 문자</td>
        <td>/t</td>
        <td>탭(tab)</td>
        <td>    </td>
    </tr>
    <tr>
        <td>/n</td>
        <td>줄바꿈</td>
        <td></td>
    </tr>
    <tr>
        <td>%%</td>
        <td>%</td>
        <td>%</td>
    </tr>
</table>

```java
public class PrintfExample {
    public static void main(String[] args) {
        System.out.printf("상품의 가격:%d원\n", 123);
        System.out.printf("상품의 가격:%6d원\n", 123);
        System.out.printf("상품의 가격:%-6d원\n", 123);
        System.out.printf("상품의 가격:%06d원\n", 123);

        System.out.printf("반지름이 %d인 원의 넓이:%10.2f\n", 10, Math.PI * 10 * 10);

        System.out.printf("%6d | %-10s | %10s\n", 1, "홍길동", "도적");

        Date now = new Date();
        System.out.printf("오늘은 %tY년 %tm월 %td일 입니다.\n", now, now, now);
        System.out.printf("오늘은 %1$tY년 %1$tm월 %1$td일 입니다.\n", now);
        System.out.printf("현재 %1$tH시 %1$tM분 %1$tS초 입니다.\n", now);
    }
}
```

#### 객체 입출력 보조 스트림
    자바는 메모리에 생성된 객체를 파일 또는 네트워크로 출력할 수 있다.
    객체는 문자가 아니기 때문에 바이트 기반 스트림으로 출력
    객체를 출력하기 위해서는 객체의 데이터를 일렬로 늘어선 연속적인 바이트로 
    변경 해야 하는데 이것을 객체 직렬화(serialization) 라고 한다. 

###### ObjectInputStream, ObjectOutputStream

    ObjectOutputStream 은 바이트 출력 스트림과 연결되어 객체를 직렬화 하는 역할
    ObjectInputStream 은 바이트 입력 스트림과 연결되어 객체를 역직렬화 하는 역할
    
    ObjectOutputStream oos = new ObjectOutputStream( 바이트출력스트림 );
    ObjectInputStream ois = new ObjectInputStream( 바이트입력스트림 );
    
    ObjectOutputStream 으로 객체를 직렬화 하기 위해서는 writeObject() 메소드 사용
    ObjectInputStream 으로 객체를 역직렬화 하기 위해서는 readObject() 메소드 사용 
    
```java
public class ObjectInputOutputStreamExample {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("C:Temp/Object.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(new Integer(10));
        oos.writeObject(new Double(3.14));
        oos.writeObject(new int[]{1, 2, 3});
        oos.writeObject(new String("홍길동"));
        oos.flush();
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("C:Temp/Object.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Integer obj1 = (Integer) ois.readObject();
        Double obj2 = (Double) ois.readObject();
        int[] obj3 = (int[]) ois.readObject();
        String obj4 = (String) ois.readObject();

        ois.close();
        fis.close();

        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj3[0] + " " + obj3[1] + " " + obj3[2]);
        System.out.println(obj4);
    }
}
```
    
###### 직렬화가 가능한 클래스 (Serializable)
    자바는 Serializable 인터페이스를 구현한 클래스만 직렬화 할 수 있도록
    제한하고 있다.
    Serializable 인터페이스는 빈 인터페이스 지만, 객체를 직렬화할 때
    private 필드를 포함한 모든 필드를 바이트로 변환해도 좋다는 표시 역할을 함
    
    public class XXX implements Serializable {}
    
    객체를 직렬화 하면 바이트로 변환되는 것은 필드들이고,
    생성자 및 메소드는 직렬화에 포함되지 않는다.
    따라서 역직렬화할 때에는 필드의 값만 복원된다.
    또 필드 선언에 static, transient 가 붙어 있을 경우 직렬화 되지 않는다.
    
```java
public class ClassA implements Serializable {
    int filed1;
    ClassB field2 = new ClassB();
    static int field3;
    transient int field4;
}

class ClassB implements Serializable {
    int field1;
}

class SerializableWriter {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("C:/Temp/Object.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        ClassA classA = new ClassA();

        classA.filed1 = 1;
        classA.field2.field1 = 2;
        classA.field3 = 3;
        classA.field4 = 4;

        oos.writeObject(classA);
        oos.flush();
        oos.close();
        fos.close();
    }
}

class SerializableReader {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("C:/Temp/Object.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        ClassA v = (ClassA) ois.readObject();
        System.out.println(v.filed1);
        System.out.println(v.field2.field1);
        System.out.println(v.field3);
        System.out.println(v.field4);
    }
}
```

###### serialVersionUID 필드
    직렬화된 객체를 역직렬화 할때는 직렬화했을 때와 같은 클래스를 사용해야 한다.
    다른 클래스를 사용하면 InvalidClassException 을 보내는데
    serialVersionUID가 다르다는 내용을 출력한다.
    
    serialVersionUID 는 같은 클래스임을 알려주는 식별자 역할을 하는데
    Serializable 인터페이스를 구현한 클래스를 컴파일하면 
    자동적으로 serialVersionUID 정적 필드로 추가 된다.
    
    문제는 재컴파일 시 seiralVersionUID 값이 달라진다는 것 

#####
    사용했던 클래스를 재컴파일한 후 역직렬화 했을때 오류 발생
    Exception in thread "main" java.io.InvalidClassException: examples.ClassC; 
    local class incompatible: stream classdesc serialVersionUID = -1281483431667898299, local class serialVersionUID = -6980895423757183431
```java
public class ClassC implements Serializable {
    int field1;
    int field2; // 나중에 추가
}

class SerialVersionUIDExample1 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("C:/Temp/Object.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        ClassC classC = new ClassC();
        classC.field1 = 1;
        oos.writeObject(classC);
        oos.flush();
        oos.close();
        fos.close();
    }
}

class SerialVersionUIDExample2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("C:/Temp/Object.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        ClassC classC = (ClassC) ois.readObject();
        System.out.println(classC.field1);
    }
}
```
    만약 불가피하게 클래스의 수정이필요하다면 클래스 작성 시 
    serialVersionUID 를 명시적으로 선언
    static final long serialVerrsionUID = 정수값;
    
    자바는 jdk \bin 폴더에 serialVersionUID 값을 자동으로 생성시켜주는
    serialver.exe 명령어를 제공
    
###### writeObject(), readObject() 메소드
    상속관계에 있는 두 클래스 중 부모가 Serializable 인터페이스를 구현하고 있다면
    자식 클래스는 자동으로 Serializable 성질을 갖는다 ( 직렬화 가능 )
    그러나 자식 클래스만 Serializable 인터페이스를 구현하고 있을 경우
    부모의 필드는 직렬화에서 제외된다.
    이때 방법은
    1. 부모 클래스가 Serializable 인터페이스를 구현하도록 한다.
    2. 자식 클래스에서 writeObject(), readObject() 를 선언해서
       부모 객체의 필드를 직접 출력 시킨다.
       
```java
public class Parent {
    public String field1;
}

class Child extends Parent implements Serializable {
    public String field2;

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeUTF(field1);
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        field1 = ois.readUTF();
        ois.defaultReadObject();
    }
}

class NonSerializableParentExample {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("C:/Temp/Object.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Child child = new Child();
        child.field1 = "홍길동";
        child.field2 = "홍삼원";
        oos.writeObject(child);
        oos.flush();
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("C:/Temp/Object.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Child v = (Child) ois.readObject();

        System.out.println(v.field1);
        System.out.println(v.field2);
        ois.close();
        fis.close();
    }
}
```
    writeObject() 메소드는 직렬화될 때 자동으로 호출되고, 
    readObject() 메소드는 역직렬화될 때 자동으로 호출된다.
    
    주의할 점은 접근 제한자가 private가 아니면 자동으로 호출되지 않는다.
    
    defaultWriteObject() 와 defaultReadObject() 는 자식클래스에
    정의된 필드들을 모두 직렬화하고 역직렬화한다.
    
### 64. 네트워크 기초
    IP 주소는 네트워크 어댑터 ( 랜(LAN) 카드 ) 마다 할당 됨
    
#### InetAddress
    자바에서는 IP 주소를 InetAddress 객체로 표현
    
    InetAddress ia = InetAddress.getLocalHost();
    
    도메인 이름으로 IP 주소 가져오기
    InetAddress ia = InetAddress.getByName(String host);
    InetAddress[] iaArr = InetAddress.getAllByName(String host);
    
    String ip = InetAddress.getHostAddress();
    
```java
public class InetAddressExample {
    public static void main(String[] args) {
        try {
            InetAddress local = InetAddress.getLocalHost();
            System.out.println(local.getHostAddress());

            InetAddress[] iaArr = InetAddress.getAllByName("www.naver.com");
            for(InetAddress ia : iaArr){
                System.out.println(ia.getHostAddress());
            }
        } catch (UnknownHostException e) {
        }
    }
}
```

### 65. TCP 네트워킹
    TCP (Transmission Control Protocol) 연결 지향적 프로토콜
    클라이언트가 연결 요청을 하고, 서버가 연결을 수락하면 통신 선로가 고정
    그 선로를 통해서 데이터가 순차적으로 전달
    그래서 TCP 는 데이터를 정확하고 안정적으로 전달
    그러나 TCP는 데이터 전송 전 반드시 연결, 고정된 통신선로가 최단선이 아닐경우
    상대적으로 UDP (User Datagram Protocol) 보다 데이터 전송속도가 느릴수 있다.
    
#### ServerSocket, Socket
    ServerSocket : 클라이언트의 연결 요청을 기다리면서 연결 수락
    Socket : 연결된 클라이언트와 통신
    
    ServerSocket serverSocket = new ServerSocket();
    serverSocket.bind(new InetSocketAddress(5001));
    
    serverSocket.bind(new InetSocketAddress(ip, 5001));

    클라이언트가 연결 요청을 하면 Socket을 만들어 리턴 ( 연결 수락 )    
    Socket socket = serverSocket.accept();
    InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
    
    Exception
        BindException : 해당 포트가 이미 사용중
        SocketException : accept()에서 블로킹 되어있을때
        IOException : close()

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드명</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>String</td>
        <td>getHostName()</td>
        <td>클라이언트 IP 리턴</td>
    </tr>
    <tr>
        <td>int</td>
        <td>getPort()</td>
        <td>클라이언트 포트 번호 리턴</td>
    </tr>
    <tr>
        <td>String</td>
        <td>toString()</td>
        <td>"IP:포트번호" 형태의 문자열 리턴</td>
    </tr>
</table>

    Socket socket = new Socket(ip, port);
    
    Socket socket = new Socket(new InetSocketAddress(ip, port));
    
    Socket socket = new Socket();
    socket.connect(new InetSocketAddress(ip, port));
    
    Exception
        UnknownHostException : 잘못 된 IP주소를 입력
        IOException : 주어진 포트로 접속할 수 없을 때 발생, close()

```java
public class ServerExample {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 5001));
            while (true) {
                System.out.println("연결 기다림");
                Socket socket = serverSocket.accept();
                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("연결 수락함 : " + isa.getHostName());
            }
        } catch (Exception e) {
        }

        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {

            }
        }
    }
}

class ClientExample {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket();
            System.out.println("연결 요청");
            socket.connect(new InetSocketAddress("localhost", 5001));
            System.out.println("연결 성공");
        } catch (Exception e) {
        }

        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
```

#### Socket 데이터 통신
    connect() 와 accept() 로 얻은 Socket 객체로부터
    각각 InputStream, OutputStream 을 얻어낼 수 있음.
    
    보낼 데이터를 byte[] 배열로 생성하고 OutputStream 
    write() 메소드를 통해 전송한다.
    
    받을 데이터는 InputStream read() 메소드를 통해 받는다.
    
```java
public class ServerExample {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 5001));
            while (true) {
                System.out.println("연결 기다림");
                Socket socket = serverSocket.accept();
                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("연결 수락함 : " + isa.getHostName());

                byte[] bytes = null;
                String message = null;

                InputStream is = socket.getInputStream();
                bytes = new byte[100];
                int readByteCount = is.read(bytes);
                message = new String(bytes, 0, readByteCount, "UTF-8");
                System.out.println("데이터 받기 성공 : " + message);

                OutputStream os = socket.getOutputStream();
                message = "Hello Client";
                bytes = message.getBytes("UTF-8");
                os.write(bytes);
                os.flush();

                is.close();
                os.close();
                socket.close();

            }
        } catch (Exception e) {
        }

        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {

            }
        }
    }
}

class ClientExample {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket();
            System.out.println("연결 요청");
            socket.connect(new InetSocketAddress("localhost", 5001));
            System.out.println("연결 성공");

            byte[] bytes = null;
            String message = null;

            OutputStream os = socket.getOutputStream();
            message = "Hello Server";
            bytes = message.getBytes("UTF-8");
            os.write(bytes);
            os.flush();
            System.out.println("데이터 보내기 성공");

            InputStream is = socket.getInputStream();

            bytes = new byte[100];
            int readByteCount = is.read(bytes);

            message = new String(bytes, 0, readByteCount, "UTF-8");
            System.out.println("데이터 받기 성공 : " + message);

            os.close();
            is.close();

        } catch (Exception e) {
        }

        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
```
    
    InputStream 의 read() 메소드가 호출되면 상대방이 데이터를 보내기 전까지
    블로킹(blocking) 되는데, read() 메소드가 블로킹 해제되고 리턴되는 경우는
    세 가지이다.

<table>
    <tr>
        <th>블로킹이 해제되는 경우</th>
        <th>리턴값</th>
    </tr>
    <tr>
        <td>상대방이 데이터를 보냄</td>
        <td>읽은 바이트 수</td>
    </tr>
    <tr>
        <td>상대방이 정상적으로 Socket 의 close()를 호출</td>
        <td>-1</td>
    </tr>
    <tr>
        <td>상대방이 비정상적으로 종료</td>
        <td>IOException 발생</td>
    </tr>
</table>

#### 스레드 병렬 처리
    ServerSocket 의 accept() 를 실행하거나
    Socket 생성자 또는 connect() 를 실행할 경우에
    해당 작업이 완료되기 전까지 블로킹(blocking) 된다.
    한 스레드가 직접 입출력 작업을 담당하게 되면 한 작업이 완료될때까지
    다음 작업을 진행할 수 없다.
    따라서 별도의 작업 스레드를 생성해서 병렬적으로 처리하는 것이 좋다.
    accept(), connect(), read(), write()

### 66. UDP 네트워킹
    UDP (User Datagram Protocol) 비연결 지향적 프로토콜
    데이터를 주고 받을 때 연결 절차를 거치지 않고, 발신자가 일방적으로 데이터를 발신하는 방식
    연결 과정이 없기 때문에 TCP 보다는 빠른 전송을 할 수 있지만 데이터 전달의 신뢰성은 떨어짐
    UDP 는 발신자가 데이터 패킷을 순차적으로 보내더라도 
    이 패킷들은 서로 다른 통신 선로를 통해 전달될 수 있다.
    먼저 보낸 패킷이 느린 선로를 통해 전송될 경우 나중에 보낸 패킷보다 늦게 도착할 수 있다.
    일부 패킷은 잘못된 선로로 전송되어 잃어버릴 수도 있다.

#### 발신자 구현
    DatagramSocket, DatagramPacket
    
    DatagramSocket datagramSocket = new DatagramSocket();
    
    첫번째 매개값은 보낼 데이터, 두번째 매개값은 데이터 항목수, 세번째 매개값은 수신자 InetSocketAddress
    DatagramPacket packet = new DatagramPacket(
        byteArr, byteArr.length,
        new InetSocketAddress("localhost",5001)
    )
    
    datagramSocket.send(packet); 으로 데이터를 보낸다.

#### 수신자 구현
    DatagramSocket datagramSocket = new DatagramSocket(5001);

    첫번째 매개값은 읽은 패킷 데이터를 저장한 바이트 배열, 두번째 매개값은 읽을 수 있는 최대 바이트 수
    DatagramPacket packet = new DatagramPacket(new byte[100], 100)
    
    receiver를 호출했다면 패킷을 받을때까지 블로킹됨
    datagramSocket.receive(packet);
    
    String data = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
    
    받은 패킷에서 발신자의 IP와 포트 알아냄
    SocketAddress socketAddress = packet.getSocketAddress();
    
```java
public class UdpSendExample {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket();
        for (int i = 1; i < 3; i++) {
            String data = "메세지" + i;
            byte[] byteArr = data.getBytes("UTF-8");
            DatagramPacket datagramPacket = new DatagramPacket(
                    byteArr, byteArr.length,
                    new InetSocketAddress("localhost", 5001)
            );
            datagramSocket.send(datagramPacket);
            System.out.println(" 보낸 바이트 수 : " + byteArr.length);
        }
        System.out.println("발신 종료");
        datagramSocket.close();
    }
}

class UdpReceiveExample {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket(5001);
        Thread thread = new Thread(() -> {
            System.out.println("수신 시작");
            try {
                while (true) {
                    DatagramPacket packet = new DatagramPacket(new byte[100], 100);
                    datagramSocket.receive(packet);
                    String data = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
                    System.out.println(packet.getSocketAddress() + " " + data);
                }
            } catch (Exception e) {
                System.out.println("수신 종료");
            }
        });

        thread.start();
        Thread.sleep(10000);
        datagramSocket.close();
    }
}
```

### 67. NIO (New Input / Output)
    
<table>
    <tr>
        <th>NIO 패키지</th>
        <th>포함되어 있는 내용</th>
    </tr>
    <tr>
        <td>java.nio</td>
        <td>다양한 버퍼 클래스</td>
    </tr>
    <tr>
        <td>java.nio.channels</td>
        <td>파일 채널, TCP 채널, UDP 채널 등의 클래스</td>
    </tr>
    <tr>
        <td>java.nio.channels.spi</td>
        <td>java.nio.channels 패키지를 위한 서비스 제공자 클래스</td>
    </tr>
    <tr>
        <td>java.nio.charset</td>
        <td>문자셋, 인코더, 디코더 API</td>
    </tr>
    <tr>
        <td>java.nio.charset.spi</td>
        <td>java.nio.charset 패키지를 위한 서비스 제공자 클래스</td>
    </tr>
    <tr>
        <td>java.nio.file</td>
        <td>파일 및 파일 시스템에 접근하기 위한 클래스</td>
    </tr>
    <tr>
        <td>java.nio.file.attribute</td>
        <td>파일 및 파일 시스템의 속성에 접근하기 위한 클래스</td>
    </tr>
    <tr>
        <td>java.nio.file.spi</td>
        <td>java.nio.file 패키지를 위한 서비스 제공자 클래스</td>
    </tr>    
</table>

#### IO와 NIO의 차이점
<table>
    <tr>
        <th>구분</th>
        <th>IO</th>
        <th>NIO</th>
    </tr>
    <tr>
        <td>입출력 방식</td>
        <td>스트림 방식</td>
        <td>채널 방식</td>
    </tr>
    <tr>
        <td>버퍼 방식</td>
        <td>넌버퍼(non-buffer)</td>
        <td>버퍼(buffer)</td>
    </tr>
    <tr>
        <td>비동기 방식</td>
        <td>지원안함</td>
        <td>지원</td>
    </tr>
    <tr>
        <td>블로킹 / 넌블로킹 방식</td>
        <td>블로킹 방식만 지원</td>
        <td>블로킹 / 넌블로킹 방식 모두 지원</td>
    </tr>
</table>

###### 스트림 vs 채널
    IO는 스트림 기반이다. 스트림은 입력, 출력 스트림으로 구분되어 있기 때문에
    데이터를 읽고 저장하려면 InputStream, OutputStream 별도로 생성해야 한다.
    
    NIO는 채널 기반이다. 채널은 스트림과 달리 양방향으로 입력과 출력이 가능하다.
    데이터를 읽고 저장하려면 Channel 하나만 생성하면 된다.
    
###### 넌버퍼 vs 버퍼
    IO에서는 출력 스트림이 1바이트를 쓰면 입력 스트림이 1바이트를 읽는다.
    그래서 빠른 시스템을 위해 Bufferd Input / Output Stream 을 보조 스트림으로 연결해 사용한다.
    NIO에서는 기본적으로 버퍼를 사용해 입출력을 한다.
    
###### 블로킹 vs 넌블로킹
    IO는 블로킹 된다. 입력 스트림의 read() 메소드를 호출하면 데이터가 입력되기 전까지
    스레드는 블로킹( 대기 상태 ) 된다.
    NIO는 블로킹과 넌블로킹 특징을 모두 가지고 있다.
    IO 블로킹과의 차이점은 NIO 블로킹은 스레드를 인터럽트함으로써 빠져나올 수가 있다는 것이다.
    NIO의 넌블로킹은 입출력 작업 준비가 완료된 채널만 선택해서 작업 스레드가 처리한다.
    
#### IO와 NIO의 선택
    NIO는 불특정 다수의 클라이언트 연결, 멀티파일들을 넌블로킹이나 비동기로 처리할 수 있기 때문에 
    과도한 스레드 생성을 피하고 스레드를 효과적으로 재사용할 수 있음.
    스레드에서 입출력 처리가 오래 걸린다면 대기하는 작업의 수가 늘어나기 때문에
    오히려 제한된 스레드로 처리하는 것이 불리할 수 있다.
    
    대용량 데이터를 처리할 경우에는 IO가 유리하다.
    모든 입출력 작업에 버퍼를 무조건 사용해야 하므로 받은 즉시 처리하는 IO보다 NIO가 더 복잡하다.
    
    연결 클라이언트 수가 적고, 전송되는 데이터가 대용량이면서 순차적으로 처리될 필요성이 있을 경우 IO가 더 좋다
    
### 68. 파일과 디렉토리

#### 경로 정의
    Path는 IO 의 java.io.File 클래스에 대응되는 NIO 인터페이스이다.
    
    Path path = Paths.get(String first, String... more)
    Path path = Paths.get(URI uri);
    
    절대경로
    Path path = Paths.get("C:/Temp/dir/file.txt");
    Path path = Paths.get("C:/Temp/dir", "file.txt");
    Path path = Paths.get("C:", "Temp", "dir", "file.txt");
    
    상대경로
    Path path = Paths.get("dir/file.txt");
    Path path = Paths.get("./dir/file.txt");
    Path path = Paths.get("../dir2/file.txt");
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>int</td>
        <td>compareTo(Path other)</td>
        <td>파일 경로가 동일하면 0을 리턴,<br>
        상위 경로면 음수,<br>
        하위 경로면 양수를 리턴,<br>
        음수와 양수 값의 차이나는 문자열의 수</td>
    </tr>
    <tr>
        <td>Path</td>
        <td>getFileName()</td>
        <td>부모 경로를 제외한 파일 또는 디렉토리 이름만 가진 Path 리턴</td>
    </tr>
    <tr>
        <td>FileSystem</td>
        <td>getFileSystem()</td>
        <td>FileSystem 객체 리턴</td>
    </tr>
    <tr>
        <td>Path</td>
        <td>getName(int index)</td>
        <td>C:\Temp\dir\file.txt 일 경우<br>
        index 가 0이면 "Temp"의 Path 객체 리턴<br>
        index 가 1이면 "dir"의 Path 객체 리턴<br>
        index 가 2이면 "file.txt"의 Path 객체 리턴<br></td>
    </tr>
    <tr>
        <td>int</td>
        <td>getNameCount()</td>
        <td>중첩 경로 수, C"\Temp\dir\file.txt 일 경우 3을 리턴</td>
    </tr>
    <tr>
        <td>Path</td>
        <td>getParent()</td>
        <td>바로 위 부모 폴더의 Path 리턴</td>
    </tr>
    <tr>
        <td>Path</td>
        <td>getRoot()</td>
        <td>루트 디렉토리의 Path 리턴</td>
    </tr>
    <tr>
        <td>Iterator&lt;Path&gt;</td>
        <td>iterator()</td>
        <td>경로에 있는 모든 디렉토리와 파일을 Path 객체로 생성하고 반복자를 리턴</td>
    </tr>
    <tr>
        <td>Path</td>
        <td>normalize()</td>
        <td>상대 경로로 표기할 때 불필요한 요소를 제거<br>
        C:\Temp\dir1\..\dir2\file.txt -> C:\Temp\dir2\file.txt</td>
    </tr>
    <tr>
        <td>WatchKey</td>
        <td>register(...)</td>
        <td>WatchService를 등록</td>
    </tr>
    <tr>
        <td>File</td>
        <td>toFile()</td>
        <td>java.io.File 객체로 리턴</td>
    </tr>
    <tr>
        <td>String</td>
        <td>toString()</td>
        <td>파일 경로를 문자열로 리턴</td>
    </tr>
    <tr>
        <td>URI</td>
        <td>toUri()</td>
        <td>파일 경로를 URI 객체로 리턴</td>
    </tr>
</table>

```java
public class PathExample {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("src/examples/PathExample.java");
        System.out.println("파일명 : " + path.getFileName());
        System.out.println("부모 디렉토리명 : " + path.getParent());
        System.out.println("중첩 경로 수 : " + path.getNameCount());

        System.out.println();

        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.println(path.getName(i));
        }

        System.out.println();

        Iterator<Path> iterator = path.iterator();
        while (iterator.hasNext()) {
            Path temp = iterator.next();
            System.out.println(temp.getFileName());
        }

    }
}
```

#### 파일 시스템 정보(FileSystem)

    운영체제의 파일 시스템은 FileSystem 인터페이스를 통하여 접근할 수 있다.
    FileSystem fileSystem = FileSystems.getDefault();
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Iterable&lt;FileStore&gt;</td>
        <td>getFileStores()</td>
        <td>드라이버 정보를 가진 FileStore 객체들을 리턴</td>
    </tr>
    <tr>
        <td>Iterable&lt;Path&gt;</td>
        <td>getRootDirectoriees()</td>
        <td>루트 디렉토리 정보를 가진 Path 객체들을 리턴</td>
    </tr>
    <tr>
        <td>String</td>
        <td>getSeparator()</td>
        <td>디렉토리 구분자 리턴</td>
    </tr>
</table>

    FileStore Method
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>long</td>
        <td>getTotalSpace()</td>
        <td>드라이버 전체 공간 크기( 단위: 바이트 ) 리턴</td>
    </tr>
    <tr>
        <td>long</td>
        <td>getUnallocatedSpace()</td>
        <td>할당되지 않은 공간 크기( 단위: 바이트 ) 리턴</td>
    </tr>
    <tr>
        <td>long</td>
        <td>getUsableSpace()</td>
        <td>사용 가능한 공간 크기, getUnallocatedSpace()와 동일한 값</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isReadOnly()</td>
        <td>읽기 전용 여부</td>
    </tr>
    <tr>
        <td>String</td>
        <td>name()</td>
        <td>드라이버명 리턴</td>
    </tr>
    <tr>
        <td>String</td>
        <td>type()</td>
        <td>파일 시스템 종류</td>
    </tr>
</table>

```java
public class FileSystemExample {
    public static void main(String[] args) throws Exception {
        FileSystem fileSystem = FileSystems.getDefault();

        for (FileStore store : fileSystem.getFileStores()) {
            System.out.println("드라이버명: " + store.name());
            System.out.println("파일시스템: " + store.type());
            System.out.println("전체 공간: " + store.getTotalSpace() + "바이트");
            System.out.println("사용 중인 공간: " + (store.getTotalSpace() - store.getUnallocatedSpace()) + "바이트");
            System.out.println("사용 가능한 공간: " + store.getUsableSpace() + "바이트");
            System.out.println();
        }
        System.out.println("파일 구분자: " + fileSystem.getSeparator());
        System.out.println();

        for (Path path : fileSystem.getRootDirectories()) {
            System.out.println(path.toString());
        }
    }
}
```
#### 파일 속성 읽기 및 파일, 디렉토리 생성 / 삭제
    java.nio.file.Files 클래스 메소드

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>long 또는 Path</td>
        <td>copy(...)</td>
        <td>복사</td>
    </tr>
    <tr>
        <td>Path</td>
        <td>createDirectories(...)</td>
        <td>모든 부모 디렉토리생성</td>
    </tr>
    <tr>
        <td>Path</td>
        <td>createDirectory()</td>
        <td>경로의 마지막 디렉토리만 생성</td>
    </tr>
    <tr>
        <td>Path</td>
        <td>createFile(...)</td>
        <td>파일 생성</td>
    </tr>
    <tr>
        <td>void</td>
        <td>delete(...)</td>
        <td>삭제</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>deleteIfExists(...)</td>
        <td>존재한다면 삭제</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>exists(...)</td>
        <td>존재 여부</td>
    </tr>
    <tr>
        <td>FileStore</td>
        <td>getFileStore(...)</td>
        <td>파일이 위치한 FileStore( 드라이브 ) 리턴</td>
    </tr>
    <tr>
        <td>FileTime</td>
        <td>getLastModifiedTime(...)</td>
        <td>마지막 수정 시간을 리턴</td>
    </tr>
    <tr>
        <td>UserPrincipal</td>
        <td>getOwner(...)</td>
        <td>소유자 정보를 리턴</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isDirectory(...)</td>
        <td>디렉토리인지 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isExecutable(...)</td>
        <td>실행 가능 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isHidden(...)</td>
        <td>숨김 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isReadable(...)</td>
        <td>읽기 가능 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isRegularFile(...)</td>
        <td>일반 파일인지 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isSameFile(...)</td>
        <td>같은 파일인지 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isWritable(...)</td>
        <td>쓰기 가능 여부</td>
    </tr>
    <tr>
        <td>Path</td>
        <td>move(...)</td>
        <td>파일 이동</td>
    </tr>
    <tr>
        <td>BufferedReader</td>
        <td>newBufferedReader(...)</td>
        <td>텍스트 파일을 읽는 BufferedReader 리턴</td>
    </tr>
    <tr>
        <td>BufferedWriter</td>
        <td>newBufferedWriter(...)</td>
        <td>텍스트 파일을 쓰는 BufferedWriter 리턴</td>
    </tr>
    <tr>
        <td>SeekableByteChannel</td>
        <td>newByteChannel(...)</td>
        <td>파일에 읽고 쓰는 바이트 채널을 리턴</td>
    </tr>
    <tr>
        <td>DirectoryStream&lt;Path&gt;</td>
        <td>newDirectoryStream(...)</td>
        <td>디렉토리의 모든 내용을 스트림으로 리턴</td>
    </tr>
    <tr>
        <td>InputStream</td>
        <td>newInputStream(...)</td>
        <td>파일의 InputStream 리턴</td>
    </tr>
    <tr>
        <td>OutputStream</td>
        <td>newOutputStream(...)</td>
        <td>파일의 OutputStream 리턴</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>notExists(...)</td>
        <td>존재하지 않는지 여부</td>
    </tr>
    <tr>
        <td>String</td>
        <td>probeContentType(...)</td>
        <td>파일의 MIME 타입을 리턴</td>
    </tr>
    <tr>
        <td>byte[]</td>
        <td>readAllBytes(...)</td>
        <td>파일의 모든 바이트를 읽고 배열로 리턴</td>
    </tr>
    <tr>
        <td>List&lt;String&gt;</td>
        <td>readAllLines(...)</td>
        <td>텍스트 파일의 모든 라인을 읽고 리턴</td>
    </tr>
    <tr>
        <td>long</td>
        <td>size(...)</td>
        <td>파일의 크기 리턴</td>
    </tr>
    <tr>
        <td>Path</td>
        <td>write(...)</td>
        <td>파일에 바이트나 문자열을 저장</td>
    </tr>
</table>

```java
public class FileExample2 {
    public static void main(String[] args) throws  Exception{
        Path path = Paths.get("src/examples/FileExample2.java");
        System.out.println("디렉토리 여부 : "+ Files.isDirectory(path));
        System.out.println("파일 여부 : "+ Files.isRegularFile(path));
        System.out.println("마지막 수정시간 : "+ Files.getLastModifiedTime(path));
        System.out.println("파일 크기 "+ Files.size(path));
        System.out.println("소유자 "+ Files.getOwner(path).getName());
        System.out.println("숨김 파일 여부 "+ Files.isHidden(path));
        System.out.println("읽기 가능 여부 "+ Files.isReadable(path));
        System.out.println("쓰기 가능 여부 "+ Files.isWritable(path));
    }
}
```
```java
public class DirectoryExample {
    public static void main(String[] args) throws Exception {
        Path path1 = Paths.get("C:/Temp/dir/subdir");
        Path path2 = Paths.get("C:/Temp/file.txt");

        if (Files.notExists(path1)) {
            Files.createDirectories(path1);
        }
        if (Files.notExists(path2)) {
            Files.createFile(path2);
        }
        Path path3 = Paths.get("C:/Temp");
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path3);

        for (Path path : directoryStream) {
            if (Files.isDirectory(path)) {
                System.out.println("디렉토리 " + path.getFileName());
            } else {
                System.out.println("파일 " + path.getFileName());
            }
        }
    }
}
```

#### 와치 서비스(WatchService) ( ★ 다시 공부 )
    디렉토리 내부에서 파일 생성, 삭제 수정 등의 내용 변화를 감시하는데 사용된다.
    WatchService watchService = FileSystems.getDefault().newWatchService();
    
    감시가 필요한 디렉토리의 Path 객체에서 register() 메소드로 WatchService를 등록
    path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                                StandardWatchEventKinds.ENTRY_MODIFY,
                                StandardWatchEventKinds.ENTRY_DELETE);
                                
    디렉토리 내부에서 변경이 발생되면 와치 이벤트(Watch Event) 가 발생하고
    WatchService 는 해당 이벤트 정보를 가진 와치키(WatchKey)를 생성하여 큐에 넣어준다.
    프로그램은 무한 루프를 돌면서 WatchService 의 take() 메소드를 호출하여 WatchKey가
    큐에 들어올 때까지 대기하고 있다가 WatchKey가 큐에 들어오면 WatchKey를 얻어 처리
    while(true) {
        WatchKey watchKey = watchService.take(); // 큐에 WatchKey가 들어올 때까지 대기
    }
    
    WatchKey를 얻고 나선 pollEvents() 메소드를 호출하여 WatchEvent 리스트를 얻는다.
    List<WatchEvent<?>> list = watchKey.pollEvents();
    
    한번 사용된 WatchKey는 reset() 메소드로 초기화
    boolean valid = watchKey.reset();
    
### 69. 버퍼
    NIO에서는 데이터를 입출력하기 위해 항상 버퍼를 사용해야 한다.

#### Buffer 종류
    버퍼는 저장되는 데이터 타입에 따라 분류될 수 있고, 
    어떤 메모리를 사용하느냐에 따라 다이렉트와 넌다이렉트로 분류할 수도 있다.
    
###### 데이터 타입에 따른 버퍼
    Buffer - ByteBuffer - MappedByteBuffer
           - CharBuffer
           - ShortBuffer
           - IntBuffer
           - LongBuffer
           - FloatBuffer
           - DoubleBuffer  
    MappedByteBuffer 는 ByteBuffer 의 하위 클래스로 
    파일의 내용에 랜덤하게 접근하기 위해 파일의 내용을 메모리와 매핑 시킨 버퍼
###### 넌다이렉트와 다이렉트 버퍼
    넌다이렉트(non-direct)는 JVM이 관리하는 힙 메모리 공간을 이용하는 버퍼이고,
    다이렉트(direct)는 운영체제가 관리하는 메모리 공간을 이용하는 버퍼이다. 
<table>
    <tr>
        <th>구분</th>
        <th>넌다이렉트 버퍼</th>
        <th>다이렉트 버퍼</th>
    </tr>
    <tr>
        <td>사용하는 메모리 공간</td>
        <td>JVM의 힙 메모리</td>
        <td>운영체제의 메모리</td>
    </tr>
    <tr>
        <td>버퍼 생성 시간</td>
        <td>버퍼 생성이 빠르다.</td>
        <td>버퍼 생성이 느리다.</td>
    </tr>
    <tr>
        <td>버퍼의 크기</td>
        <td>작다.</td>
        <td>크다.( 큰 데이터를 처리할 때 유리 )</td>
    </tr>
    <tr>
        <td>입출력 성능</td>
        <td>낮다.</td>
        <td>높다.( 입출력이 빈번할 때 유리 )</td>
    </tr>
</table>    

```java
public class PerformanceExample {
    public static void main(String[] args) throws Exception {
        Path from = Paths.get("src/examples/testImage.jpg");
        Path to1 = Paths.get("src/examples/testImage2.jpg");
        Path to2 = Paths.get("src/examples/testImage3.jpg");

        long size = Files.size(from);

        FileChannel fileChannel_from = FileChannel.open(from);
        FileChannel fileChannel_to1 = FileChannel.open(to1, EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE));
        FileChannel fileChannel_to2 = FileChannel.open(to2, EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE));

        ByteBuffer nonDirectBuffer = ByteBuffer.allocate((int) size);
        ByteBuffer directBuffer = ByteBuffer.allocateDirect((int) size);

        long start, end;

        start = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            fileChannel_from.read(nonDirectBuffer);
            nonDirectBuffer.flip();
            fileChannel_to1.write(nonDirectBuffer);
            nonDirectBuffer.clear();
        }
        end = System.nanoTime();
        System.out.println("넌다이렉트 :\t" + (end - start) + "ns");

        fileChannel_from.position(0);
        start = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            fileChannel_from.read(directBuffer);
            directBuffer.flip();
            fileChannel_to2.write(directBuffer);
            directBuffer.clear();
        }
        end = System.nanoTime();
        System.out.println("다이렉트 :\t" + (end - start) + "ns");

        fileChannel_from.close();
        fileChannel_to1.close();
        fileChannel_to2.close();
    }
}
```
    다이렉트 버퍼는 채널을 사용해서 버퍼의 데이터를 읽고 
    저장할 경우에만 운영체제의 native I/O를 수행한다.
    만약 채널을 사용하지 않고 ByteBuffer의 get() / put() 메소드를 사용했다면
    이 작업은 내부적으로 JNI(Java Native Interface) 호출해서 native I/O를 
    수행하기 때문에 JNI 호출이라는 오버헤더가 추가된다.
    그렇기 때문에 오히려 넌다이렉트 버퍼의 get() / put() 메소드 성능이 
    더 좋게 나올수도 있다.
    
###### allocate() 메소드
    JVM 힙 메모리에 넌다이렉트 버퍼를 생성
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>ByteBuffer</td>
        <td>ByteBuffer.allocate(int capacity)</td>
        <td>capacity 개 만큼의 byte 값을 저장</td>
    </tr>
    <tr>
        <td>CharBuffer</td>
        <td>CharBuffer.allocate(int capacity)</td>
        <td>capacity 개 만큼의 char 값을 저장</td>
    </tr>
    <tr>
        <td>DoubleBuffer</td>
        <td>DoubleBuffer.allocate(int capacity)</td>
        <td>capacity 개 만큼의 double 값을 저장</td>
    </tr>
    <tr>
        <td>FloatBuffer</td>
        <td>FloatBuffer.allocate(int capacity)</td>
        <td>capacity 개 만큼의 float 값을 저장</td>
    </tr>
    <tr>
        <td>IntBuffer</td>
        <td>IntBuffer.allocate(int capacity)</td>
        <td>capacity 개 만큼의 int 값을 저장</td>
    </tr>
    <tr>
        <td>LongBuffer</td>
        <td>LongBuffer.allocate(int capacity)</td>
        <td>capacity 개 만큼의 long 값을 저장</td>
    </tr>
    <tr>
        <td>ShortBuffer</td>
        <td>ShortBuffer.allocate(int capacity)</td>
        <td>capacity 개 만큼의 short 값을 저장</td>
    </tr>            
</table>

###### wrap() 메소드
    이미 생성되어 있는 자바 배열을 래핑해서 Buffer 객체를 생성한다.
    자바 배열은 JVM 힙 메모리에 생성되므로 wrap()은 넌다이렉트 버퍼를 생성한다.
    
    byte[] byteArray = new byte[100];
    ByteBuffer bytebuffer = ByteBuffer.wrap(byteArray);
    
    byte[] byteArray = new byte[100];
    ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray, 0, 50);
    
###### allocateDirect() 메소드
    JVM 힙 메모리 바깥쪽, 즉 운영체제가 관리하는 메모리에 다이렉트 버퍼를 생성한다.
    이 메소드는 각 타입별 Buffer 클래스에는 없고, ByteBuffer 에서만 제공된다.
    그래서 asCharBuffer(), asShortBuffer(), asIntBuffer(), asLongBuffer(),
    asFloatBuffer(), asDoublebuffer() 메소드를 이용해서 해당 타입별 Buffer를 얻으면 된다.

```java
public class DirectBufferCapacityExample {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
        System.out.println("저장용량: " + byteBuffer.capacity() + " 바이트");

        CharBuffer charBuffer = ByteBuffer.allocateDirect(100).asCharBuffer();
        System.out.println("저장용량: " + charBuffer.capacity() + " 문자");

        IntBuffer intBuffer = ByteBuffer.allocateDirect(100).asIntBuffer();
        System.out.println("저장용량: " + intBuffer.capacity() + " 정수");
    }
}
```

###### byte 해석 순서
    데이터를 처리할 때 바이트 처리 순서는 운영체제마다 차이가 있다.
    앞쪽 바이트부터 먼저 처리하는 것을 Big endian 이라고 하고,
    뒤쪽 바이트부터 먼저 처리하는 것을 Little endian 이라고 한다.
    
    JRE가 설치된 어떤 환경이든 JVM은 무조건 Big endian으로 동작하도록 되어 있다.

```java
public class ComputerByteOrderExample {
    public static void main(String[] args) {
        System.out.println("운영 체제 종류: " + System.getProperty("os.name"));
        System.out.println("네이티브의 바이트 해석 순서: " + ByteOrder.nativeOrder());
    }
}
```
    운영체제와 JVM의 바이트 해석 순서가 다를 경우에는 
    JVM이 운영체제와 데이터를 교환할 때 자동적으로 처리해주기 때문에 문제는 없다.
    하지만 다이렉트 버퍼일 경우 운영체제의 native I/O를 사용하므로
    운영체제의 기본 해석 순서로 JVM의 해석 순서를 맞추는 것이 성능에 도움이 된다.
    
    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100).order(ByteOrder.nativeOrder());
    
#### Buffer 의 위치 속성

<table>
    <tr>
        <th>속성</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>position</td>
        <td>현재 읽거나 쓰는 위치값이다. 인덱스 값이기 때문에 0부터 시작하며, limit 보다 큰 값을 가질 수 없다.<br>
        만약 position 과 limit 의 값이 같아진다면 더 이상 데이터를 쓰거나 읽을 수 없다는 뜻이 된다.</td>
    </tr>
    <tr>
        <td>limit</td>
        <td>버퍼에서 읽거나 쓸수 있는 위치의 한계를 나타낸다. 이 값은 capacity 보다 작거나 같은 값을 가진다.<br>
        최초에 버퍼를 만들었을 때는 capacity 와 같은 값을 가진다.</td>
    </tr>
    <tr>
        <td>capacity</td>
        <td>버퍼의 최대 데이터 개수( 메모리 크기 )를 나타낸다. 인덱스 값이 아니라 수량임을 주의하자.</td>
    </tr>
    <tr>
        <td>mark</td>
        <td>reset() 메소드를 실행했을 때에 돌아오는 위치를 지정하는 인덱스로서 mark() 메소드로 지정할 수 있다.<br>
        주의할 점은 반드시 position 이하의 값으로 지정해주어야 한다. position 이나 limit 의 값이<br>
        mark 값보다 작은 경우, mark 는 자동 제거된다. mark 가 없는 상태에서 reset() 메소드를 호출하면<br>
        InvalidMarkException 이 발생한다.</td>
    </tr>
</table>

    0 <= mark => position <= limit <= capacity

#### Buffer 메소드

###### 각 타입별 공통 메소드

<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>Object</td>
        <td>array()</td>
        <td>버퍼가 래핑(wrap) 한 배열을 리턴</td>
    </tr>
    <tr>
        <td>int</td>
        <td>arrayOffset()</td>
        <td>버퍼의 첫 번째 요소가 있는 내부 배열의 인덱스를 리턴</td>
    </tr>
    <tr>
        <td>int</td>
        <td>capacity()</td>
        <td>버퍼의 전체 크기를 리턴</td>
    </tr>
    <tr>
        <td>Buffer</td>
        <td>clear()</td>
        <td>버퍼의 위치 속성을 초기화( position=0, limit=capacity )</td>
    </tr>
    <tr>
        <td>Buffer</td>
        <td>flip()</td>
        <td>limit 을 position 으로, position 을 0 인덱스로 이동</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>hasArray()</td>
        <td>버퍼가 래핑(wrap)한 배열을 가지고 있는지 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>hasRemaining()</td>
        <td>position 과 limit 사이에 요소가 있는지 여부( position&lt;limit )</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isDirect()</td>
        <td>운영체제의 버퍼를 사용하지는 여부</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>isReadOnly()</td>
        <td>버퍼가 읽기 전용인지 여부</td>
    </tr>
    <tr>
        <td>int</td>
        <td>limit()</td>
        <td>limit 위치를 리턴</td>
    </tr>
    <tr>
        <td>Buffer</td>
        <td>limit(int newLimit)</td>
        <td>newLimit 으로 limit 위치를 변경</td>
    </tr>
    <tr>
        <td>Buffer</td>
        <td>mark()</td>
        <td>현재 위치를 mark 로 표시</td>
    </tr>
    <tr>
        <td>int</td>
        <td>position()</td>
        <td>position 위치를 리턴</td>
    </tr>
    <tr>
        <td>Buffer</td>
        <td>position(int newPosition)</td>
        <td>newPosition 으로 position 위치를 변경</td>
    </tr>
    <tr>
        <td>int</td>
        <td>remaining()</td>
        <td>position 과 limit 사이의 요소의 개수</td>
    </tr>
    <tr>
        <td>Buffer</td>
        <td>reset()</td>
        <td>position 을 mark 위치로 이동</td>
    </tr>
    <tr>
        <td>Buffer</td>
        <td>rewind()</td>
        <td>position 을 0 인덱스로 이동</td>
    </tr>
</table>
    
###### 데이터를 읽고 저장하는 메소드
    버퍼에 데이터를 저장하는 메소드는 put(), 데이터를 읽는 메소드는 get() 이다.
    이 메소드들은 Buffer 추상 클래스에는 없고, 각 타입별 하위 Buffer 클래스가 가지고 있다.
    
    get(), put() 메소드는 상대적과 절대적으로 구분된다.
    버퍼 내의 현재 위치 속성인 position 에서 데이터를 읽고 저장할 경우에는 상대적
    position 과 상관없이 주어진 인덱스에서 데이터를 읽고 저장할 경우에는 절대적이다.
    
    상대적 get() 과 put() 을 호출하면 position 의 값은 증가하지만
    절대적 get() 과 put() 을 호출하면 position 의 값은 증가하지 않는다.
    만약 position 값이 limit 값까지 증가했을때 
    상대적 get() 을 사용하면 BufferUnderflowException
    상대적 put() 을 사용하면 BufferOverflowException 예외 발생
    
<table>
    <tr>
        <th colspan="2">구분</th>
        <th>ByteBuffer</th>
        <th>CharBuffer</th>
    </tr>
    <tr>
        <td rowspan="2">get()</td>
        <td>상대적()</td>
        <td>get()<br>
        get(byte[] dst)<br>
        get(byte[] dst, int offset, int length)<br>
        getChar()<br>
        getDouble()<br>
        getFloat()<br>
        getInt()<br>
        getLong()<br>
        getShort()</td>
        <td>get()<br>
        get(char[] dst)<br>
        get(char[] dst, int offset, int length)</td>
    </tr>
    <tr>
        <td>절대적</td>
        <td>get(int index)<br>
        getChar(int index)<br>
        getDouble(int index)<br>
        getFloat(int index)<br>
        getInt(int index)<br>
        getLong(int index)<br>
        getShort(int indxt)</td>
        <td>get(int index)</td>
    </tr>
    <tr>
        <td rowspan="2">put()</td>
        <td>상대적</td>
        <td>put(byte b)<br>
        put(byte[] src)<br>
        put(byte[] src, int offset, int length)<br>
        put(ByteBuffer src)<br>
        putChar(char value)<br>
        putDouble(double value)<br>
        putFloat(float value)<br>
        putInt(int value)<br>
        putLong(long value)<br>
        putShort(short value)</td>
        <td>put(char c)<br>
        put(char[] src)<br>
        put(char[] src, int offset, int length)<br>
        put(CharBuffer src)<br>
        put(String src)<br>
        put(String src, int start, int end)</td>
    </tr>
    <tr>
        <td>절대적</td>
        <td>put(int index, byte b)<br>
        putChar(int index, char value)<br>
        putDouble(int index, char value)<br>
        putFloat(int index, float value)<br>
        putInt(int index, int value)<br>
        putLong(int index, long value)<br>
        putShort(int index, short value)</td>
        <td>put(int index, char c)</td>
    </tr>
</table>

###### 버퍼 예외의 종류
    
<table>
    <tr>
        <th>예외</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>BufferOverflowException</td>
        <td>position 이 limit 에 도달했을 때 put() 을 호출하면 발생</td>
    </tr>
    <tr>
        <td>BufferUnderflowException</td>
        <td>position 이 limit 에 도달했을 때 get() 을 호출하면 발생</td>
    </tr>
    <tr>
        <td>InvalidMarkException</td>
        <td>mark 가 없는 상태에서 reset() 메소드를 호출하면 발생</td>
    </tr>
    <tr>
        <td>ReadOnlyBufferException</td>
        <td>읽기 전용 버퍼에서 put() 또는 compact() 메소드를 호출하면 발생</td>
    </tr>
</table>

```java
public class BufferExample {
    public static void main(String[] args) {
        System.out.println("[ 7바이트 크기로 버퍼 생성 ]");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(7);
        printState(byteBuffer);

        // 상대적 저장
        byteBuffer.put((byte) 10);
        byteBuffer.put((byte) 11);
        System.out.println("[ 2바이트 저장 후 ]");
        printState(byteBuffer);

        // 상대적 저장
        byteBuffer.put((byte) 12);
        byteBuffer.put((byte) 13);
        byteBuffer.put((byte) 14);
        System.out.println("[ 3바이트 저장 후 ]");
        printState(byteBuffer);

        byteBuffer.flip(); // 데이터를 읽기 위해 위치 속성값 변경
        System.out.println("[ flip() 실행 후 ]");
        printState(byteBuffer);

        // 상대적 읽기
        byteBuffer.get(new byte[3]);
        System.out.println("[ 3바이트 읽은 후 ]");
        printState(byteBuffer);

        byteBuffer.mark(); // 마킹
        System.out.println("------[ 현재 위치를 마크 해놓음 ]");

        // 상대적 읽기
        byteBuffer.get(new byte[2]);
        System.out.println("[ 2바이트 읽은 후 ]");
        printState(byteBuffer);

        byteBuffer.reset();
        System.out.println("------[ position 을 마크 위치로 옮김 ]");
        printState(byteBuffer);

        byteBuffer.rewind(); // position 을 0 으로 이동
        System.out.println("[ rewind() 실행 후 ]");
        printState(byteBuffer);

        byteBuffer.clear(); // 모든 위치 속성값을 초기화
        System.out.println("[ clear() 실행 후 ]");
        printState(byteBuffer);

    }

    public static void printState(Buffer buffer) {
        System.out.print("\tposition: " + buffer.position() + ", ");
        System.out.print("\tlimit: " + buffer.limit() + ", ");
        System.out.println("\tcapacity: " + buffer.capacity());
    }
}
```
    실행 결과
    
    [ 7바이트 크기로 버퍼 생성 ]
    	position: 0, 	limit: 7, 	capacity: 7
    [ 2바이트 저장 후 ]
    	position: 2, 	limit: 7, 	capacity: 7
    [ 3바이트 저장 후 ]
    	position: 5, 	limit: 7, 	capacity: 7
    [ flip() 실행 후 ]
    	position: 0, 	limit: 5, 	capacity: 7
    [ 3바이트 읽은 후 ]
    	position: 3, 	limit: 5, 	capacity: 7
    ------[ 현재 위치를 마크 해놓음 ]
    [ 2바이트 읽은 후 ]
    	position: 5, 	limit: 5, 	capacity: 7
    ------[ position 을 마크 위치로 옮김 ]
    	position: 3, 	limit: 5, 	capacity: 7
    [ rewind() 실행 후 ]
    	position: 0, 	limit: 5, 	capacity: 7
    [ clear() 실행 후 ]
    	position: 0, 	limit: 7, 	capacity: 7
    	
######
    compact() 메소드
    
```java
public class CompactExample {
    public static void main(String[] args) {
        System.out.println("[ 7바이트 크기로 버퍼 생성 ]");
        ByteBuffer buffer = ByteBuffer.allocateDirect(7);
        buffer.put((byte) 10);
        buffer.put((byte) 11);
        buffer.put((byte) 12);
        buffer.put((byte) 13);
        buffer.put((byte) 14);
        buffer.flip();
        printState(buffer);

        buffer.get(new byte[3]);
        System.out.println("[ 3바이트 읽음 ]");

        buffer.compact();
        System.out.println("[ compact() 실행 후 ]");
        printState(buffer);
    }

    public static void printState(ByteBuffer buffer) {
        System.out.print(buffer.get(0)+", ");
        System.out.print(buffer.get(1)+", ");
        System.out.print(buffer.get(2)+", ");
        System.out.print(buffer.get(3)+", ");
        System.out.println(buffer.get(4));

        System.out.print("position: " + buffer.position() + ", ");
        System.out.print("limit: " + buffer.limit() + ", ");
        System.out.println("capacity: " + buffer.capacity());
    }
}
```
    실행결과
    
    [ 7바이트 크기로 버퍼 생성 ]
    10, 11, 12, 13, 14
    position: 0, limit: 5, capacity: 7
    [ 3바이트 읽음 ]
    [ compact() 실행 후 ]
    13, 14, 12, 13, 14
    position: 2, limit: 7, capacity: 7
    
#### Buffer 변환

###### ByteBuffer <-> String
    채널을 통해 문자열을 파일이나 네트워크로 전송하려면 특정 문자셋(UTF-8, EUC-KR) 으로
    인코딩해서 ByteBuffer로 변환해야 한다. 
    문자셋을 표현하는 java.nio.charset.Charset 객체가 필요하다
    
    Charset charset = Charset.forName("UTF-8");
    Charset charset = Charset.defaultCharset(); // 운영체제가 사용하는 디폴트 문자셋

    // 전송 전 encoding    
    String data = ...;
    ByteBuffer byteBuffer = charset.encode(data);
    
    // 읽을 때 decoding
    ByteBuffer byteBuffer = ...;
    Stirng data = charset.decode(byteBuffer).toString();
    
```java
public class ByteBufferToStringExample {
    public static void main(String[] args) {
        Charset charset = Charset.forName("UTF-8");

        // 문자열 -> 인코딩 -> ByteBuffer
        String data = "하이!";
        ByteBuffer byteBuffer = charset.encode(data);

        // ByteBuffer -> 디코딩 -> 문자열
        data = charset.decode(byteBuffer).toString();
        System.out.println("문자열 복원 "+data);
    }
}
```

###### ByteBuffer <-> IntBuffer
    int[] 을 생성하고 이것을 파일이나 네트워크로 출력하려면 int[] 또는 IntBuffer 로부터
    ByteBuffer 를 생성해야함. int 타입은 4Byte 크기를 가지므로 int[] 크기 또는 
    IntBuffer 의 capacity 보다 4배 큰 ByteBuffer를 생성해야 함.

    int[] data = new int[] {10, 20};
    IntBuffer intBuffer = IntBuffer.wrap(data);
    ByteBuffer byteBuffer = ByteBuffer.allocate(intBuffer.capacity()*4);
    for(int i=0; i<intBuffer.capacity(); i++){
        byteBuffer.putInt(intBuffer.get(i));
    }
    byteBuffer.flip();
    
    주의, putInt() 메소드는 position 을 이동 시키기 때문에 모두 저장한 후에는
    position 을 0으로 되돌려 놓는 flip() 메소드를 호출해야 함
    
    반대로 ByteBuffer에 4바이트씩 연속된 int 데이터가 저장되어 있을 경우,
    ByteBuffer 의 asIntBuffer() 메소드를 통해 IntBuffer 를 얻고
    get() 메소드를 통해 int 값들을 얻을 수 있다.
    
    ByteBuffer byteBuffer = ...;
    IntBuffer intBuffer = byteBuffer.asIntBuffer();
    int[] data = new int[intBuffer.capacity()];
    intBuffer.get(data);
    
```java
public class ByteBufferToIntBuffer {
    public static void main(String[] args) throws Exception {
        
        // int[] -> IntBuffer -> ByteBuffer
        int[] writeData = {10, 20};
        IntBuffer writeIntBuffer = IntBuffer.wrap(writeData);
        ByteBuffer writeByteBuffer = ByteBuffer.allocate(writeIntBuffer.capacity() * 4);
        for (int i = 0; i < writeIntBuffer.capacity(); i++) {
            writeByteBuffer.putInt(writeIntBuffer.get(i));
        }
        writeByteBuffer.flip();

        // ByteBuffer -> IntBuffer -> int[]
        ByteBuffer readByteBuffer = writeByteBuffer;
        IntBuffer readIntBuffer = readByteBuffer.asIntBuffer();
        int[] readData = new int[readIntBuffer.capacity()];
        readIntBuffer.get(readData);
        System.out.println("배열 복원 " + Arrays.toString(readData));
    }
}
```

<table>
    <tr>
        <th>리턴 타입</th>
        <th>변환 메소드</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>ShortBuffer</td>
        <td>asShortBuffer()</td>
        <td>2바이트씩 연속된 short 데이터를 가진 ByteBuffer일 경우</td>
    </tr>
    <tr>
        <td>IntBuffer</td>
        <td>asIntBuffer()</td>
        <td>4바이트씩 연속된 int 데이터를 가진 ByteBuffer일 경우</td>
    </tr>
    <tr>
        <td>LongBuffer</td>
        <td>asLongBuffer()</td>
        <td>8바이트씩 연속된 long 데이터를 가진 ByteBuffer일 경우</td>
    </tr>
    <tr>
        <td>FloatBuffer</td>
        <td>asFloatBuffer()</td>
        <td>4바이트씩 연속된 float 데이터를 가진 ByteBuffer일 경우</td>
    </tr>
    <tr>
        <td>DoubleBuffer</td>
        <td>asDoubleBuffer()</td>
        <td>8바이트씩 연속된 double 데이터를 가진 ByteBuffer일 경우</td>
    </tr>
</table>

### 70. 파일 채널

    java.nio.channels.FileChannel 을 이용하면 파일 읽기와 쓰기를 할 수 있음.
    동기화 처리가 되어 있기 때문에 멀티 스레드 환경에서도 안전하다
    
#### FileChannel 생성과 닫기
    FileChannel fileChannel = FileChannel.open(Path path, OpenOption... options);
    
    FileChannel fileChannel = FileChannel.open(
        Paths.get("C:/Temp/file.txt"),
        StandardOpenOption.CREATE_NEW,
        StandardOpenOption.WRITE
    )
###### StandardOpenOption
<table>
    <tr>
        <th>열거 상수</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>READ</td>
        <td>읽기용으로 파일을 연다.</td>
    </tr>
    <tr>
        <td>WRITE</td>
        <td>쓰기용으로 파일을 연다.</td>
    </tr>
    <tr>
        <td>CREATE</td>
        <td>파일이 없다면 새 파일을 생성한다.</td>
    </tr>
    <tr>
        <td>CREATE_NEW</td>
        <td>새 파일을 만든다. 이미 파일이 있으면 예외와 함께 실패한다.</td>
    </tr>
    <tr>
        <td>APPEND</td>
        <td>파일 끝에 데이터를 추가한다( WRITE 나 CREATE 와 함께 사용됨 )</td>
    </tr>
    <tr>
        <td>DELETE_ON_CLOSE</td>
        <td>채널를 닫을 때 파일을 삭제한다( 임시 파일을 삭제할 때 사용 )</td>
    </tr>
    <tr>
        <td>TRUNCATE_EXISTING</td>
        <td>파일을 0바이트로 잘라낸다( WRITE 옵션과 함께 사용됨 )</td>
    </tr>
</table>

#### 파일 쓰기와 읽기

###### 파일 쓰기
    파일에 바이트를 쓰려면 FileChannel 의 write() 메소드를 호출 하면 됨
    파일에 쓰여지는 바이트는 매개값으로 주어지는 ByteBuffer 의 
    position 부터 limit 까지이다.
    
    int bytesCount = fileChannel.write(ByteBuffer src);
    
```java
public class FileChannelWriteExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:/Temp/file.txt");
        Files.createDirectories(path.getParent());
        FileChannel fileChannel = FileChannel.open(
                path, StandardOpenOption.CREATE, StandardOpenOption.WRITE
        );
        String data = "안녕하세요";
        Charset charset = Charset.defaultCharset();
        ByteBuffer byteBuffer = charset.encode(data);

        int byteCount = fileChannel.write(byteBuffer);
        System.out.println("file.txt : " + byteCount + " bytes written");

        fileChannel.close();

    }
}
```

###### 파일 읽기
    파일로부터 바이트를 읽으려면 FileChannel 의 read() 메소드를 호출 하면 됨
    파일로부터 읽혀지는 바이트는 매개값으로 주어지는 ByteBuffer 에
    읽혀지는 ByteBuffer의 position 부터 저장된다.
    
    int bytesCount = fileChannel.read(ByteBuffer dst);
    
```java
public class FileChannelReadExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:/Temp/file.txt");
        FileChannel fileChannel = FileChannel.open(
                path, StandardOpenOption.READ
        );

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        Charset charset = Charset.defaultCharset();
        String data = "";
        int byteCount;

        while (true) {
            byteCount = fileChannel.read(byteBuffer);
            if (byteCount == -1) {
                break;
            }
            byteBuffer.flip();
            data += charset.decode(byteBuffer).toString();
            byteBuffer.clear();
        }
        fileChannel.close();
        System.out.println(data);
    }
}
```

###### 파일 복사
    하나의 ByteBuffer 를 사이에 두고, 파일 읽기용 FileChannel 과
    파일 쓰기용 FileChannel 이 읽기와 쓰기를 교대로 번갈아 수행하도록 하면 됨

```java
public class FileCopyExample {
    public static void main(String[] args) throws IOException {
        Path from = Paths.get("src/examples/goodPo.jpg");
        Path to = Paths.get("src/examples/goodPo2.jpg");

        FileChannel fileChannel_from = FileChannel.open(
                from, StandardOpenOption.READ
        );

        FileChannel fileChannel_to = FileChannel.open(
                to, StandardOpenOption.CREATE, StandardOpenOption.WRITE
        );

        ByteBuffer buffer = ByteBuffer.allocateDirect(100);
        int byteCount;

        while (true) {
            buffer.clear();
            byteCount = fileChannel_from.read(buffer);
            if (byteCount == -1) {
                break;
            }
            buffer.flip();
            fileChannel_to.write(buffer);
        }
        fileChannel_from.close();
        fileChannel_to.close();
        System.out.println("파일 복사 성공");
    }
}
```

    단순히 파일을 복사할 목적이라면 NIO의 Files 클래스의 copy() 메소드를 사용하는 것이 더 편리
    
    Path targetPath = Files.copy(Path source, Path target, CopyOption... options);

<table>
    <tr>
        <th>열거 상수</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>REPLACE_EXISTING</td>
        <td>타겟 파일이 존재하면 대체한다.</td>
    </tr>
    <tr>
        <td>COPY_ATTRIBUTES</td>
        <td>파일의 속성까지도 복사한다.</td>
    </tr>
    <tr>
        <td>NOFOLLOW_LINKS</td>
        <td>링크 파일일 경우 링크 파일만 복사하고 링크된 파일은 복사하지 않는다.</td>
    </tr>
</table>

```java
public class FilesCopyMethodExample {
    public static void main(String[] args) throws IOException {
        Path from = Paths.get("src/examples/goodPo.jpg");
        Path to = Paths.get("src/examples/goodPo3.jpg");
        Files.copy(from,to, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("파일 복사 성공");
    }
}
```

### 71. 파일 비동기 채널
    FileChannel 의 read() 와 write() 메소드는 파일 입출력 작업 동안 블로킹된다.
    따라서 별도의 작업 스레드를 생성해서 호출하기 때문에 스레드수가 증가한다.
    자바 NIO는 불특정 다수의 파일 및 대용량 파일의 입출력 작업을 위해
    비동기 파일 채널(AsynchronousFileChannel) 을 별도로 제공
    
    // 내부에서 생성되는 스레드풀 사용
    AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
        Path path,
        OpenOption... options
    )
    
    // 개발자가 지정하는 스레드풀 사용
    AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
        Path path,
        Set<? extends OpenOption> options,
        ExecutorSErvice executor,
        FileAttribute<?>... attrs
    )
    
    ExecutorService executorService = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors()
    );
    
    AsynchronousFileChannel fileChannel = Asynchronous FileChannel.open(
        Paths.get("C:/Temp/file.txt"),
        EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE),
        executorService
    );
    
#### 파일 읽기와 쓰기
    read(ByteBuffer dst, long position, A attachment, CompletionHandler<Integer, A> handler);
    write(ByteBuffer dst, long position, A attachment, CompletionHandler<Integer, A> handler);
    
    attachment 매개값은 콜백 메소드로 전달할 첨부 객체이다.
    첨부 객체는 콜백 메소드에서 결과값 외에 추가적인 정보를 얻고자 할 때 사용하는 객체를 말함.
    
    handler 매개값은 CompletionHandler<Integer, A> 구현 객체를 지정
    Integer 는 입출력 작업의 결과 타입으로 read() 와 write() 가 읽거나 쓴 바이트 수이다.
    A는 첨부 객체 타입으로 CompletionHandler 구현 객체를 작성할 때
    임의로 지정이 가능하다.
    CompletionHandler 구현 객체는 비동기 작업이 정상적으로 완료된 경우와 예외 발생으로 실패된 경우
    자동 콜백되는 다음 두가지 메소드를 가지고 있어야 한다.
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드명</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>void</td>
        <td>completed(Integer result, A attachment)</td>
        <td>작업이 정상적으로 완료된 경우 콜백</td>
    </tr>
    <tr>
        <td>void</td>
        <td>failed(Throwable exc, A attachment)</td>
        <td>예외 때문에 작업이 실패된 경우 콜백</td>
    </tr>
</table>

###### 비동기로 파일 쓰기

```java
public class AsynchronousFileChannelWriteExample {
    public static void main(String[] args) throws Exception {
        // 스레드풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        for (int i = 0; i < 10; i++) {
            Path path = Paths.get("C:/Temp/file" + i + ".txt");
            Files.createDirectories(path.getParent());

            // 비동기 파일 채널 생성
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
                    path,
                    EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE),
                    executorService
            );

            // 파일에 저장할 데이터를 ByteBuffer 에 저장
            Charset charset = Charset.defaultCharset();
            ByteBuffer byteBuffer = charset.encode("하이!");

            // 첨부 객체 생성
            class Attachment {
                Path path;
                AsynchronousFileChannel fileChannel;

                Attachment(Path path, AsynchronousFileChannel fileChannel) {
                    this.path = path;
                    this.fileChannel = fileChannel;
                }
            }
            Attachment attachment = new Attachment(path, fileChannel);

            // CompletionHandler 객체 생성
            CompletionHandler<Integer, Attachment> completionHandler =
                    new CompletionHandler<Integer, Attachment>() {
                        @Override
                        public void completed(Integer result, Attachment attachment) {
                            System.out.println(attachment.path.getFileName() + " : " + result + " bytes written : " + Thread.currentThread().getName());
                            try {
                                attachment.fileChannel.close();
                            } catch (IOException e) {
                            }
                        }

                        @Override
                        public void failed(Throwable exc, Attachment attachment) {
                            exc.printStackTrace();
                            try {
                                attachment.fileChannel.close();
                            } catch (IOException e) {
                            }
                        }
                    };
            fileChannel.write(byteBuffer, 0, attachment, completionHandler);
        }
        Thread.sleep(1000);
        executorService.shutdown();
    }
}
```

###### 비동기로 파일 읽기

```java
public class AsynchronousFileChannelReadExample {
    public static void main(String[] args) throws Exception {
        // 스레드풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        for (int i = 0; i < 10; i++) {
            Path path = Paths.get("C:/Temp/file" + i + ".txt");
            Files.createDirectories(path.getParent());

            // 비동기 파일 채널 생성
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
                    path,
                    EnumSet.of(StandardOpenOption.READ),
                    executorService
            );

            // 파일의 크기와 동일한 capacity 를 갖는 버퍼 생성
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());

            // 첨부 객체 생성
            class Attachment {
                Path path;
                AsynchronousFileChannel fileChannel;
                ByteBuffer byteBuffer;

                Attachment(Path path, AsynchronousFileChannel fileChannel, ByteBuffer byteBuffer) {
                    this.path = path;
                    this.fileChannel = fileChannel;
                    this.byteBuffer = byteBuffer;
                }
            }
            Attachment attachment = new Attachment(path, fileChannel, byteBuffer);

            // CompletionHandler 객체 생성
            CompletionHandler<Integer, Attachment> completionHandler =
                    new CompletionHandler<Integer, Attachment>() {
                        @Override
                        public void completed(Integer result, Attachment attachment) {
                            attachment.byteBuffer.flip();
                            // 버퍼에 저장된 데이터를 문자열로 복원
                            Charset charset = Charset.defaultCharset();
                            String data = charset.decode(attachment.byteBuffer).toString();
                            System.out.println(attachment.path.getFileName() + " : " + data + " : " + Thread.currentThread().getName());
                            try {
                                fileChannel.close();
                            } catch (IOException e) {
                            }
                        }

                        @Override
                        public void failed(Throwable exc, Attachment attachment) {
                            exc.printStackTrace();
                            try {
                                attachment.fileChannel.close();
                            } catch (IOException e) {
                            }
                        }
                    };
            // 파일 읽기
            fileChannel.read(byteBuffer, 0, attachment, completionHandler);
        }
        // 스레드풀 종료
        executorService.shutdown();
    }
}
```
### 72. TCP 블로킹 채널
    NIO 를 이용해서 TCP 서버 / 클라이언트 애플리케이션을 개발하려면
    블로킹, 넌블로킹, 비동기 구현방식 중에서 하나를 결정해야 한다.
    
#### ServerSocketChannel, SocketChannel
    ServerSocketChannel 은 IO의 ServerSocket, 
    SocketChannel 은 IO의 Socket에 대응되는 클래스이다.
    두 클래스 모두 버퍼를 이용하며 블로킹과 넌블로킹 방식을 지원한다.

    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.configureBlocking(true);
    serverSocketChannel.bind(new InetSocketAddress(5001));
    
    SocketChannel socketChannel = serverSocketChannel.accept();
    
    InetSocketAddress socketAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
    
<table>
    <tr>
        <th>리턴 타입</th>
        <th>메소드명</th>
        <th>설명</th>
    </tr>
    <tr>
        <td>String</td>
        <td>getHostName()</td>
        <td>클라이언트 IP 리턴</td>
    </tr>
    <tr>
        <td>int</td>
        <td>getPort()</td>
        <td>클라이언트 포트 번호 리턴</td>
    </tr>
    <tr>
        <td>String</td>
        <td>toString()</td>
        <td>"IP:포트번호" 형태의 문자열 리턴</td>
    </tr>
</table>

```java
public class ChannelServerExample {
    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(true);
            serverSocketChannel.bind(new InetSocketAddress(5001));
            while (true) {
                System.out.println("[ 연결 기다림 ]");
                SocketChannel socketChannel = serverSocketChannel.accept();
                InetSocketAddress isa = (InetSocketAddress) socketChannel.getRemoteAddress();
                System.out.println("[ 연결 수락함 ] " + isa.getHostName());
            }
        } catch (Exception e) {
        }
        if (serverSocketChannel.isOpen()) {
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
            }

        }
    }
}
```

    connect() 메소드는 서버와 연결이 될 때 까지 블로킹 된다.
    
```java
public class ChannelClientExample {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(true);
            System.out.println("[ 연결 요청 ]");
            socketChannel.connect(new InetSocketAddress("localhost", 5001));
            System.out.println("[ 연결 성공 ]");

            if (socketChannel.isOpen()) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                }
            }
        } catch (Exception e) {
        }
    }
}
```

#### 소켓 채널 데이터 통신
    서버와 클라이언트의 연결 후 SocketChannel 객체의 read(), write() 메소드를 호출하여
    데이터 통신을 할 수 있다. 이 메소드들은 모두 버퍼를 이용한다. 
    
```java
public class ChannelServerExample {
    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(true);
            serverSocketChannel.bind(new InetSocketAddress(5001));
            while (true) {
                System.out.println("[ 연결 기다림 ]");
                SocketChannel socketChannel = serverSocketChannel.accept();
                InetSocketAddress isa = (InetSocketAddress) socketChannel.getRemoteAddress();
                System.out.println("[ 연결 수락함 ] " + isa.getHostName());

                ByteBuffer byteBuffer = null;
                Charset charset = Charset.forName("UTF-8");

                byteBuffer = ByteBuffer.allocate(100);
                int byteCount = socketChannel.read(byteBuffer);
                byteBuffer.flip();
                String message = charset.decode(byteBuffer).toString();
                System.out.println("[ 데이터 받기 성공 ] "+message);

                byteBuffer = charset.encode("안녕?");
                socketChannel.write(byteBuffer);
                System.out.println("[ 데이터 보내기 성공 ]");
            }
        } catch (Exception e) {
        }
        if (serverSocketChannel.isOpen()) {
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
            }

        }
    }
}
```
```java
public class ChannelClientExample {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(true);
            System.out.println("[ 연결 요청 ]");
            socketChannel.connect(new InetSocketAddress("localhost", 5001));
            System.out.println("[ 연결 성공 ]");

            ByteBuffer byteBuffer = null;
            Charset charset = Charset.forName("UTF-8");

            byteBuffer = charset.encode("하이! 서버!");
            socketChannel.write(byteBuffer);
            System.out.println("[ 데이터 보내기 성공 ]");

            byteBuffer = ByteBuffer.allocate(100);
            int byteCount = socketChannel.read(byteBuffer);
            byteBuffer.flip();
            String message = charset.decode(byteBuffer).toString();
            System.out.println("[ 데이터 받기 성공 ] " + message);

            if (socketChannel.isOpen()) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                }
            }
        } catch (Exception e) {
        }
    }
}
```
    read() 메소드 호출 시 상대방이 데이터를 보내기 전까지 블로킹
    블로킹이 해제 되는 경우

<table>
    <tr>
        <th>블로킹이 해제되는 경우</th>
        <th>리턴값</th>
    </tr>
    <tr>
        <td>상대방이 데이터 보냄</td>
        <td>읽은 바이트 수</td>
    </tr>
    <tr>
        <td>상대방이 정상적으로 SocketChannel 의 close() 를 호출</td>
        <td>-1</td>
    </tr>
    <tr>
        <td>상대방이 비정상적으로 종료</td>
        <td>IOException 발생</td>
    </tr>
</table>

