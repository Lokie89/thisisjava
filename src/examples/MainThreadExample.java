package examples;

public class MainThreadExample {
    public static void main(String[] args) {
        Calculator2 calculator2 = new Calculator2();

        User1 user1 = new User1();
        user1.setCalculator2(calculator2);
        user1.start();

        User2 user2 = new User2();
        user2.setCalculator2(calculator2);
        user2.start();
    }
}

class Calculator2 {
    private int memory;

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        synchronized (this) {
            this.memory = memory;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ": " + this.memory);
        }
    }
}

class User1 extends Thread {
    private Calculator2 calculator2;

    public void setCalculator2(Calculator2 calculator2) {
        this.setName("User1");
        this.calculator2 = calculator2;
    }

    @Override
    public void run() {
        calculator2.setMemory(100);
    }
}

class User2 extends Thread {
    private Calculator2 calculator2;

    public void setCalculator2(Calculator2 calculator2) {
        this.setName("User2");
        this.calculator2 = calculator2;
    }

    @Override
    public void run() {
        calculator2.setMemory(50);
    }
}

