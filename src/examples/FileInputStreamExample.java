package examples;

import java.io.FileInputStream;

public class FileInputStreamExample {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream(
                "D:\\_Project\\thisisjava\\src\\examples/FileInputStreamExample.java"
        );
        int data;
        while((data=fis.read())!=-1){
            System.out.write(data);
        }
        fis.close();
    }
}
