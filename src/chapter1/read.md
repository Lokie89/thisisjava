<h1>정리</h1>
<h5>1. JVM (운영체제 위에서(종속되어) 실행되는 자바 Java 가상 Virtual 머신 Machine)</h5>

자바 언어를 컴파일하여 바이트 코드를 번역한 후 JVM에서 실행하면<br>
다른 운영체제에서도 같은 결과를 얻을 수 있다

따라서 java 언어는 운영체제와 별개로움직이지만 jvm 은 종속적이다

java -> byte code -> JVM(번역) -> OS

JVM 만 해당 OS 타입에 맞게 번역 해주면 됨

```java
public class test{
  public void test(){
    return 0;
  }
}

```
