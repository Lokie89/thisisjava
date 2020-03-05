# 몰랐던거 정리
#### 1. JVM (운영체제 위에서(종속되어) 실행되는 자바 Java 가상 Virtual 머신 Machine)

자바 언어를 컴파일하여 바이트 코드를 번역한 후 JVM에서 실행하면<br>
다른 운영체제에서도 같은 결과를 얻을 수 있다

따라서 java 언어는 운영체제와 별개로움직이지만 jvm 은 종속적이다

java -> byte code -> JVM(번역) -> OS

JVM 만 해당 OS 타입에 맞게 번역 해주면 됨

#### 2. 타입
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

#### 3. 쓰레기값 생성
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

#### 4. 비트 논리 연산자
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

#### 5. 비트 이동 연산자
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

#### 6. 메모리 사용 영역
JVM이 실행되면 운영체제에서 메모리 영역을 할당 받는다
###### 메소드 영역
    클래스( ~.class )들을 클래스 로더로 읽어 클래스별로
    런타임 상수풀, 필드데이터, 메소드 데이터, 메소드 코드, 생성자 코드 등을
    분류하여 저장, JVM이 시작할 때 생성되고 모든 스레드가 공유
###### 힙 영역
    객체와 배열이 생성되는 영역
    스택영역에서 변수에 할당하는 주소값이 이 힙 영역의 주소값이다.
    참조하는 변수나 필드가 없다면 JVM은 Garbage Collector 를 실행시켜 제거함
###### 스택 영역
    스택 영역은 각 스레드마다 하나씩 존재하며 스레드가 시작될 때 할당됨
    스레드를 생성하지 않으면 main 스레드 한개임
    메소드를 호출할 때마다 프레임을 추가(push) 메소드가 종료되면 해당 프레임을 제거(pop)
    프레임 안에는 로컬 변수 스택이 있는데, 기본타입, 참조 타입 변수가 추가(push) 제거(pop) 된다
    정리 : 1스레드 안 1JVMStack 안 n개프레임 안 n개변수스택
    
#### 7. main() 메소드
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
        
#### 8. 다차원 배열
배열 안의 배열, 맨 안쪽 배열 힙 영역에 생성, 안쪽배열의 주소값 '도' 힙영역에 생성
가장 바깥쪽 배열의 주소값만 스택영역에 저장 

#### 9. ENUM
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

###### ENUM method
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

#### 10. 다른생성자 호출 this()
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

#### 11. 메소드 선언 시 매개변수의 수를 모를 경우
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

#### 12. 정적 멤버 ( 메소드 ) 선언
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

#### 13. 정적 블록
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

#### 14. 어노테이션 타입 정의와 적용 ( ★ 다시 공부 )
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
#### 15. 어노테이션 적용 대상 ( ★ 다시 공부 )
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
#### 16. 어노테이션 유지 정책 ( ★ 다시 공부 )
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

#### 17. 런타임 시 어노테이션 정보 사용하기 ( ★ 다시 공부 )
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

#### 18. 상속 ( 오버라이딩 )
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
### 19. final
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
    
