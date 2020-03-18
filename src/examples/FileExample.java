package examples;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;

public class FileExample {
    public static void main(String[] args) throws Exception {
        File dir = new File("C:/Temp/Dir");
        File file1 = new File("C:/Temp/file1.txt");
        File file2 = new File("C:/Temp/file2.txt");

        // 파일 경로를 URI 객체로 생성하서 매개값으로 제공
        File file3 = new File(new URI("file:///C:/Temp/file3.txt"));

        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file1.exists()) {
            file1.createNewFile();
        }
        if (!file2.exists()) {
            file2.createNewFile();
        }
        if (!file3.exists()) {
            file3.createNewFile();
        }

        File temp = new File("C:/Temp");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
        File[] contents = temp.listFiles();
        System.out.println("\t날짜\t\t시간\t\t형태\t\t크기\t\t이름");
        for (File file : contents) {
            System.out.print(sdf.format(file.lastModified()));
            if (file.isDirectory()) {
                System.out.print("\t<DIR>\t\t\t" + file.getName());
            } else {
                System.out.print("\t\t\t" + file.length() + "\t" + file.getName());
            }
            System.out.println();
        }
    }
}
