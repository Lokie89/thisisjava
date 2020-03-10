package examples;

public class ThreadSleepExample {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("삐ㅃ");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
        }
    }
}
