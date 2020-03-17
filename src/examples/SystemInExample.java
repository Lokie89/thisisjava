package examples;

import java.io.InputStream;

public class SystemInExample {
    public static void main(String[] args) throws Exception {
        System.out.println("== 메뉴 ==");
        System.out.println("1. 예금 조회");
        System.out.println("2. 예금 출금");
        System.out.println("3. 예금 입금");
        System.out.println("4. 종료하기");
        System.out.println("메뉴를 선택하세요.");
        InputStream inputStream = System.in;

        char inputChar = (char) inputStream.read();
        switch (inputChar){
            case '1':
                System.out.println("예금 조회");
                break;
            case '2':
                System.out.println("예금 출금");
                break;
            case '3':
                System.out.println("예금 입금");
                break;
            case '4':
                System.out.println("종료하기");
                break;
        }

    }
}
