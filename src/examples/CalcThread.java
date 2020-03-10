package examples;

public class CalcThread extends Thread {
    public CalcThread(String name) {
        setName(name);
    }

    @Override
    public void run() {
        for (long i = 0; i < 20000000000L; i++) {

        }
        System.out.println(getName());
    }
}

class CalcThreadMain {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            CalcThread calcThread = new CalcThread("Thread " + i);
            if (i != 10) {
                calcThread.setPriority(Thread.MIN_PRIORITY);
            } else {
                calcThread.setPriority(Thread.MAX_PRIORITY);
            }
            calcThread.start();
        }
    }
}