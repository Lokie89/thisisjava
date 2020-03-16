package examples;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class DescendingComparator implements Comparator<Fruit2> {
    @Override
    public int compare(Fruit2 t1, Fruit2 t2) {
        if (t1.price < t2.price) {
            return 1;
        } else if (t1.price == t2.price) {
            return 0;
        } else {
            return -1;
        }
    }
}

class Fruit2 {
    String name;
    int price;

    public Fruit2(String name, int price) {
        this.name = name;
        this.price = price;
    }
}

class ComparatorExample {
    public static void main(String[] args) {
//        TreeSet<Fruit2> treeSet = new TreeSet<>();
//        treeSet.add(new Fruit2("포도", 3000)); // Fruit2 가 Comparable 을 구현하지 않아서 예외 발생
//        treeSet.add(new Fruit2("수박", 10000));
//        treeSet.add(new Fruit2("딸기", 6000));

        TreeSet<Fruit2> treeSet = new TreeSet<>(new DescendingComparator());
        treeSet.add(new Fruit2("포도", 3000));
        treeSet.add(new Fruit2("수박", 10000));
        treeSet.add(new Fruit2("딸기", 6000));

        Iterator<Fruit2> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            Fruit2 fruit2 = iterator.next();
            System.out.println(fruit2.name + ": " + fruit2.price);
        }
    }
}
