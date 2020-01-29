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