package examples;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class ByteBufferToStringExample {
    public static void main(String[] args) {
        Charset charset = Charset.forName("UTF-8");

        // 문자열 -> 인코딩 -> ByteBuffer
        String data = "하이!";
        ByteBuffer byteBuffer = charset.encode(data);

        // ByteBuffer -> 디코딩 -> 문자열
        data = charset.decode(byteBuffer).toString();
        System.out.println("문자열 복원 "+data);
    }
}
