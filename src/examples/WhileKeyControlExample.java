package examples;

public class WhileKeyControlExample {
    public static void main(String[] args) throws Exception {
        boolean run = true;
        int speed = 0;
        int keyCode = 0;
        while (run) {
            if (keyCode != 13 && keyCode != 10) {
                System.out.println("--------------------------");
                System.out.println("1. 증속 | 2. 감속 | 3. 중지");
                System.out.println("--------------------------");
                System.out.println("선택: ");
            }
            keyCode = System.in.read();

            if (keyCode == 49) {
                speed++;
                System.out.println("현재속도 : " + speed);
            } else if (keyCode == 50) {
                speed--;
                System.out.println("현재속도 : " + speed);
            } else if (keyCode == 51) {
                run = false;
            } else {
                System.out.println("재입력하세요");
            }
        }
        System.out.println("프로그램 종료");
    }

}

class StringTest {
    public static void main(String[] args) {
        String a1 = new String("a");
        String a2 = new String("a");
        System.out.println(a1 == a2);
        String a3 = "a";
        System.out.println(a3 == a1);
        System.out.println(a3 == a2);
        String a4 = "a";
        System.out.println(a3 == a4);
        a1 = a3;
        System.out.println(a1 == a3);
        System.out.println(a1 == a4);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(a4);
    }
}

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
