package examples;

import java.io.File;
import java.io.FileWriter;

public class FileWriterExample {
    public static void main(String[] args) throws Exception {
        File file = new File("C:/Temp/file.txt");
        FileWriter fw = new FileWriter(file, true);
        fw.write("FileWriter는 한글로 된 " + "\r\n");
        fw.write("문자열을 바로 출력할 수 있다." + "\r\n");
        fw.flush();
        fw.close();
    }
}
