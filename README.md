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
        <td>원본 배열의 0번 인덱스에서 복사할 길이 만큼 복사한 배열 리턴,
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
        <td>interrupt()</td>
        <td>일시 정지 상태의 스레드에서 InterruptedException 예외를 발생시켜,
        예외처리 코드(catch)에서 실행 대기 상태로 가거나종료 상태로 갈 수 있도록 한다.</td>
    </tr>
    <tr>
        <td>notify()
        notifyAll()</td>
        <td>동기화 블록 내에서 wait() 메소드에 의해 일시 정지 상태에 있는
        스레드를 실행 대기 상태로 만든다.</td>
    </tr>
    <tr>
        <td>resume()</td>
        <td>suspend() 메소드에 의해 일시 정지 상태에 있는 스레드를 실행 대기 상태로 만든다.
        -Deprecated ( 대신 notify(), notifyAll() 사용 )</td>
    </tr>
    <tr>
        <td>sleep(long millis)
        sleep(long millis, int nanos)</td>
        <td>주어진 시간 동안 스레드를 일시 정지 상태로 만든다. 
        주어진 시간이 지나면 자동적으로 실행 대기 상태가 된다.</td>
    </tr>
    <tr>
        <td>join()
        join(long millis)
        join(long millis, int nanos)</td>
        <td>join() 메소드를 호출한 스레드는 일시 정지 상태가 된다.
        실행 대기 상태로 가려면, join() 메소드를 멤버로 가지는 스레드가 종료되거나,
        매개값으로 주어진 시간이 지나야 한다.</td>
    </tr>
    <tr>
        <td>wait()
        wait(long millis)
        wait(long millis, int nanos)</td>
        <td>동기화(synchronized) 블록 내에서 스레드를 일시 정지 상태로 만든다. 
        매개값으로 주어진 시간이 지나면 자동적으로 실행 대기 상태가 된다.
        시간이 주어지지 않으면 notify(), notifyAll() 메소드에 의해
        실행 대기 상태로 갈 수 있다.</td>
    </tr>
    <tr>
        <td>suspend()</td>
        <td>스레드를 일시 정지 상태로 만든다. 
        resume() 메소드를 호출하면 다시 실행 대기 상태가 된다.
        -Deprecated ( 대신 wait() 사용 )</td>
    </tr>
    <tr>
        <td>yield()</td>
        <td>실행 중에 우선순위가 동일한 다른 스레드에게 실행을 양보하고 
        실행 대기 상태가 된다.</td>
    </tr>
    <tr>
        <td>stop()</td>
        <td>스레드를 즉시 종료시킨다. -Deprecated</td>
    </tr>    
</table>