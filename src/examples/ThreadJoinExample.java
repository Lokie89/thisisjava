package examples;

public class ThreadJoinExample {
    public static void main(String[] args) {
        SumThread sumThread = new SumThread();
        sumThread.start();

        try {
            sumThread.join(); // sumThread가 종료할 때까지 메인 스레드 정지
        } catch (InterruptedException e) {
        }
        System.out.println("합 : " + sumThread.getSum());
    }

}

class SumThread extends Thread {
    private long sum;

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
    }
}