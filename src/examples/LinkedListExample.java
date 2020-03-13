package examples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new LinkedList<>();

        long startTime;
        long endTime;

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            // 0인데스에 해당값 넣음
            list1.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();
        System.out.println("ArrayList: "+(endTime-startTime)); // ArrayList: 39861200

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            // 0인데스에 해당값 넣음
            list2.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();
        System.out.println("LinkedList: "+(endTime-startTime)); // LinkedList: 9541800
    }
}
