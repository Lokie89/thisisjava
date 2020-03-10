package examples;

public class WorkObject {
    public synchronized void methodA() {
        System.out.println("ThreadA의 methodA() 작업 실행");
        notify();
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }

    public synchronized void methodB() {
        System.out.println("ThreadB의 methodB() 작업 실행");
        notify();
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }
}

class ThreadA2 extends Thread {
    private WorkObject workObject;

    public ThreadA2(WorkObject workObject) {
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            workObject.methodA();
        }
    }
}

class ThreadB2 extends Thread {
    private WorkObject workObject;

    public ThreadB2(WorkObject workObject) {
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            workObject.methodB();

        }
    }
}

class WaitNotifyExample{
    public static void main(String[] args) {
        WorkObject sharedWorkObject = new WorkObject();
        ThreadA2 threadA2 = new ThreadA2(sharedWorkObject);
        ThreadB2 threadB2 = new ThreadB2(sharedWorkObject);

        threadA2.start();
        threadB2.start();
    }
}