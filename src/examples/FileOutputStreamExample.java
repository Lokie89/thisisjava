package examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileOutputStreamExample {
    public static void main(String[] args) throws Exception{
        String originalFileName =
                "D:\\_Project\\thisisjava\\src\\examples/goodPo.jpg";
        String targetFileName = "C:/Temp/goodPosition.jpg";

        File originalFile = new File(originalFileName);
        System.out.println(originalFile.exists());
        FileInputStream fis = new FileInputStream(originalFileName);
        FileOutputStream fos = new FileOutputStream(targetFileName);
        int readByteNo;
        byte[] readBytes= new byte[100];
        while((readByteNo=fis.read(readBytes))!=-1){
            fos.write(readBytes,0,readByteNo);
        }
        fos.flush();
        fos.close();
        fis.close();

        System.out.println("복사 완료");
    }
}
