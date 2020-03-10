package examples;

public class ThreadYieldExample {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        threadA.start();
        threadB.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        threadA.work = false;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        threadA.work = true;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        threadA.stop = true;
        threadB.stop = true;
    }
}

class ThreadA extends Thread {
    public boolean stop = false;
    public boolean work = true;

    @Override
    public void run() {
        while (!stop) {
            if (work) {
                System.out.println("ThreadA");
            } else {
                Thread.yield();
            }
        }
        System.out.println("ThreadA 종료");
    }
}

class ThreadB extends Thread {
    public boolean stop = false;
    public boolean work = true;

    @Override
    public void run() {
        while (!stop) {
            if (work) {
                System.out.println("ThreadB");
            } else {
                Thread.yield();
            }
        }
        System.out.println("ThreadB 종료");
    }
}