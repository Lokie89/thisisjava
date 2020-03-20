package examples;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BufferExample {
    public static void main(String[] args) {
        System.out.println("[ 7바이트 크기로 버퍼 생성 ]");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(7);
        printState(byteBuffer);

        // 상대적 저장
        byteBuffer.put((byte) 10);
        byteBuffer.put((byte) 11);
        System.out.println("[ 2바이트 저장 후 ]");
        printState(byteBuffer);

        // 상대적 저장
        byteBuffer.put((byte) 12);
        byteBuffer.put((byte) 13);
        byteBuffer.put((byte) 14);
        System.out.println("[ 3바이트 저장 후 ]");
        printState(byteBuffer);

        byteBuffer.flip(); // 데이터를 읽기 위해 위치 속성값 변경
        System.out.println("[ flip() 실행 후 ]");
        printState(byteBuffer);

        // 상대적 읽기
        byteBuffer.get(new byte[3]);
        System.out.println("[ 3바이트 읽은 후 ]");
        printState(byteBuffer);

        byteBuffer.mark(); // 마킹
        System.out.println("------[ 현재 위치를 마크 해놓음 ]");

        // 상대적 읽기
        byteBuffer.get(new byte[2]);
        System.out.println("[ 2바이트 읽은 후 ]");
        printState(byteBuffer);

        byteBuffer.reset();
        System.out.println("------[ position 을 마크 위치로 옮김 ]");
        printState(byteBuffer);

        byteBuffer.rewind(); // position 을 0 으로 이동
        System.out.println("[ rewind() 실행 후 ]");
        printState(byteBuffer);

        byteBuffer.clear(); // 모든 위치 속성값을 초기화
        System.out.println("[ clear() 실행 후 ]");
        printState(byteBuffer);

    }

    public static void printState(Buffer buffer) {
        System.out.print("\tposition: " + buffer.position() + ", ");
        System.out.print("\tlimit: " + buffer.limit() + ", ");
        System.out.println("\tcapacity: " + buffer.capacity());
    }
}
