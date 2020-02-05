package examples;

public class Examples {
    public static void main(String[] args) {
        char c1 = 'A';
        char c2 = 65;
        char c3 = '\u0041';

        char c4 = '가';
        char c5 = 44032;
        char c6 = '\uac00';

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
    }
}

class FromIntToFloat {
    public static void main(String[] args) {
        int num1 = 123456780;
        int num2 = 123456780;

        float num3 = num2;
        System.out.println(num1 + " " + num2 + " " + num3);
        num2 = (int) num3;
        int result = num1 - num2;
        System.out.println(result);

        byte b = 10;
        float f = 2.5F;
        double d = 2.5;
    }
}

class AccuracyExample1 {
    public static void main(String[] args) {
        int apple = 1;
        double pieceUnit = 0.1;
        int number = 7;
        double result = apple - number * pieceUnit; // 쓰레기값 생성
        System.out.println(result);
    }
}

class compareOperator2 {
    public static void main(String[] args) {
        double v4 = 0.1;
        float v5 = 0.1f;
        System.out.println(v4 == v5);
    }
}

class StringEqualsExample {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str1.equals(str2));
        System.out.println(str1.equals(str3));
    }
}

class BitLogicExample {
    public static void main(String[] args) {
        System.out.println("45 & 25 = " + (45 & 25));
        System.out.println("45 | 25 = " + (45 | 25));
        System.out.println("45 ^ 25 = " + (45 ^ 25));
        System.out.println("~45 = " + (~45));
    }
}

class BitShiftExample {
    public static void main(String[] args) {
        System.out.println("1 << 3 = " + (1 << 3));
        System.out.println("-8 >> 3 = " + (-8 >> 3));
        System.out.println("-8 >>> 3 = " + (-8 >>> 3));
    }
}
