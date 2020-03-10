package examples;

public class WorkThread extends Thread {
    public WorkThread(ThreadGroup threadGroup, String threadName) {
        super(threadGroup, threadName);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted");
                break;
            }
        }
        System.out.println(getName() + " 종료됨");
    }
}

class ThreadGroupExample {
    public static void main(String[] args) {
        ThreadGroup myGroup = new ThreadGroup("myGroup");
        WorkThread workThread1 = new WorkThread(myGroup, "workThread1");
        WorkThread workThread2 = new WorkThread(myGroup, "workThread2");

        workThread1.start();
        workThread2.start();

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        mainGroup.list();
        System.out.println();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        myGroup.interrupt();
    }
}
