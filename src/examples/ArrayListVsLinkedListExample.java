package examples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListVsLinkedListExample {
    public static void work(int value) {
    }

    public static long testParallel(List<Integer> list) {
        long start = System.nanoTime();
        list.parallelStream().forEach(a -> work(a));
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 1000000; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        long arrayListParallel = testParallel(arrayList);
        long linkedListParallel = testParallel(linkedList);

        arrayListParallel = testParallel(arrayList);
        linkedListParallel = testParallel(linkedList);

        System.out.println(arrayListParallel);
        System.out.println(linkedListParallel);
    }
}
