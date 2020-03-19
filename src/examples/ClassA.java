package examples;

import java.io.*;

public class ClassA implements Serializable {
    int filed1;
    ClassB field2 = new ClassB();
    static int field3;
    transient int field4;
}

class ClassB implements Serializable {
    int field1;
}

class SerializableWriter {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("C:/Temp/Object.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        ClassA classA = new ClassA();

        classA.filed1 = 1;
        classA.field2.field1 = 2;
        classA.field3 = 3;
        classA.field4 = 4;

        oos.writeObject(classA);
        oos.flush();
        oos.close();
        fos.close();
    }
}

class SerializableReader {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("C:/Temp/Object.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        ClassA v = (ClassA) ois.readObject();
        System.out.println(v.filed1);
        System.out.println(v.field2.field1);
        System.out.println(v.field3);
        System.out.println(v.field4);
    }
}