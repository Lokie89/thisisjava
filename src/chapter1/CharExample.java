package chapter1;

public class CharExample {
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
        System.out.println(num1+" "+num2+" "+num3);
        num2 = (int) num3;
        int result = num1 - num2;
        System.out.println(result);

        byte b = 10;
        float  f = 2.5F;
        double d = 2.5;
    }
}
