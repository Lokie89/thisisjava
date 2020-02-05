# 몰랐던거 정리
#### 1. JVM (운영체제 위에서(종속되어) 실행되는 자바 Java 가상 Virtual 머신 Machine)

자바 언어를 컴파일하여 바이트 코드를 번역한 후 JVM에서 실행하면<br>
다른 운영체제에서도 같은 결과를 얻을 수 있다

따라서 java 언어는 운영체제와 별개로움직이지만 jvm 은 종속적이다

java -> byte code -> JVM(번역) -> OS

JVM 만 해당 OS 타입에 맞게 번역 해주면 됨

<<<<<<< HEAD
#### 2. 타입
int(4byte) 보다 작은 타입의 정수 계산은 int 타입을 기본으로 한다.<br>
연산자의 크기가 4byte 기 때문

```java
byte b = 4;
byte result = b + b; // 컴파일 에러
```

#### 3. 쓰레기값 생성
가수를 사용하는 부동소수점 타입 (float, double) 은 0.1을 정확하게 표현할 수 없어 근사치로 처리함<br>
만약 정확하게 표현하고 싶으면 int 형으로 변경후 계산 
```java
int apple = 1;
double pieceUnit = 0.1;
int number = 7;
double result = apple - number * pieceUnit; // 쓰레기값 생성
System.out.println(result); // 0.29999999999999993
```

```java
double v4 = 0.1;
float v5 = 0.1f;
System.out.println(v4 == v5); // false
```

#### 4. 비트 논리 연산자
2진법으로 바꾼 후 같은 자리수를 연산하여 나타냄
```java
System.out.println("45 & 25 = " + (45 & 25)); // 45 & 25 = 9
System.out.println("45 | 25 = " + (45 | 25)); // 45 | 25 = 61
System.out.println("45 ^ 25 = " + (45 ^ 25)); // 45 ^ 25 = 52
System.out.println("~45 = " + (~45)); // ~45 = -46
```

#### 5. 비트 이동 연산자
2진법으로 바꾼 후 옆으로 밈 빈 공간은 0으로 채움<br>
a << b  : 정수 a를 b만큼 왼쪽으로 이동 (빈칸은 0으로 채움)<br>
a >> b  : 정수 a를 b만큼 오른쪽으로 이동 (빈칸은 부호비트로 채움 -일때는 1 +일때는 0)<br>
a >>> b : 정수 a를 b만큼 오른쪽으로 이동 (빈칸은 0으로 채움)
```java
System.out.println("1 << 3 = " + (1 << 3));     // 1 << 3 = 8
System.out.println("-8 >> 3 = " + (-8 >> 3));   // -8 >> 3 = -1
System.out.println("-8 >>> 3 = " + (-8 >>> 3)); // -8 >>> 3 = 536870911
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
public static void main(String[] args){
    if(args.length != 2){
        
    }     
}
```