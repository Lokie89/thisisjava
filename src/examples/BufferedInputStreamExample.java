package examples;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class BufferedInputStreamExample {
    public static void main(String[] args) throws Exception {
        long start = 0;
        long end = 0;
        FileInputStream fis1 = new FileInputStream(
                "D:\\_Project\\thisisjava\\src\\examples/goodPo.jpg"
        );
        start = System.currentTimeMillis();
        while (fis1.read() != -1) {

        }
        end = System.currentTimeMillis();
        System.out.println("buffered 사용 전: " + (end - start) + "ms");
        fis1.close();

        FileInputStream fis2 = new FileInputStream(
                "D:\\_Project\\thisisjava\\src\\examples/goodPo.jpg"
        );

        BufferedInputStream bis = new BufferedInputStream(fis2);
        start = System.currentTimeMillis();
        while (bis.read() != -1) {

        }
        end = System.currentTimeMillis();
        System.out.println("buffered 사용 후: " + (end - start) + "ms");
        bis.close();
        fis2.close();
    }
}
