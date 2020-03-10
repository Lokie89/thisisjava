package examples;

import java.util.Map;

public class ThreadInfoExample {
    public static void main(String[] args) {
        AutoSaveThread autoSaveThread = new AutoSaveThread();
        autoSaveThread.setName("AutoSaveThread");
        autoSaveThread.setDaemon(true);
        autoSaveThread.start();
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces(); // 프로세스에서 실행하는 모든 스레드의 정보
        for (Thread thread : map.keySet()) {
            System.out.println("Name: " + thread.getName()
                    + (thread.isDaemon() ? "(데몬)" : "주"));
            System.out.println("\t" + "소속그룹: " + thread.getThreadGroup().getName());
            System.out.println();
        }
    }
}
