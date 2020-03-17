package examples;

import java.io.InputStream;

public class SystemInExample2 {
    public static void main(String[] args) throws Exception {
        InputStream in = System.in;
        byte[] datas = new byte[100];
        System.out.print("이름: ");
        int nameBytes = in.read(datas);
        // length 에서 -2 는 enter 키에 해당하는 캐리지 리턴(13)과 라인 피드(10)을 제외하기 위함
        // 자동으로 제외?
        String name = new String(datas, 0, nameBytes);
        System.out.print("하고싶은말: ");
        int commentBytes = in.read(datas);
        String comment = new String(datas, 0, commentBytes);

        System.out.println("입력한 이름: " + name);
        System.out.println("입력한 하고싶은말:  " + comment);

    }
}
