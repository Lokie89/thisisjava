package examples;

import java.io.*;

public class Parent {
    public String field1;
}

class Child extends Parent implements Serializable {
    public String field2;

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeUTF(field1);
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        field1 = ois.readUTF();
        ois.defaultReadObject();
    }
}

class NonSerializableParentExample {
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("C:/Temp/Object.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Child child = new Child();
        child.field1 = "홍길동";
        child.field2 = "홍삼원";
        oos.writeObject(child);
        oos.flush();
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("C:/Temp/Object.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Child v = (Child) ois.readObject();

        System.out.println(v.field1);
        System.out.println(v.field2);
        ois.close();
        fis.close();
    }
}
