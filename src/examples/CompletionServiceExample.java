package examples;

import java.util.concurrent.*;

public class CompletionServiceExample extends Thread {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        CompletionService<Integer> completionService =
                new ExecutorCompletionService<>(executorService);

        System.out.println("[ 작업 처리 요청 ]");
        for (int i = 0; i < 3; i++) {
            completionService.submit(() -> {
                int sum = 0;
                for (int i1 = 1; i1 <= 10; i1++) {
                    sum += i1;
                }
                return sum;
            });
        }

        System.out.println("[ 처리 완료된 작업 확인 ]");
        executorService.submit(() -> {
            while (true) {
                try {
                    Future<Integer> future = completionService.take();
                    int value = future.get();
                    System.out.println("[ 처리 결과 ] " + value);
                } catch (Exception e) {
                    break;
                }
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        executorService.shutdown();
    }
}
