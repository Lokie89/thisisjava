package examples;

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
//        try {
        while (true) {
            System.out.println("실행 중");
//                Thread.sleep(1); // interrupt 가 발생하면 예외처리
            if (Thread.interrupted()) {
                break;
            }
        }
//        } catch (InterruptedException e) {
//
//        }
        System.out.println("자원 정리");
        System.out.println("실행 종료");
    }
}
