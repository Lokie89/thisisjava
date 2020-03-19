package examples;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UdpSendExample {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket();
        for (int i = 1; i < 3; i++) {
            String data = "메세지" + i;
            byte[] byteArr = data.getBytes("UTF-8");
            DatagramPacket datagramPacket = new DatagramPacket(
                    byteArr, byteArr.length,
                    new InetSocketAddress("localhost", 5001)
            );
            datagramSocket.send(datagramPacket);
            System.out.println(" 보낸 바이트 수 : " + byteArr.length);
        }
        System.out.println("발신 종료");
        datagramSocket.close();
    }
}

class UdpReceiveExample {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket(5001);
        Thread thread = new Thread(() -> {
            System.out.println("수신 시작");
            try {
                while (true) {
                    DatagramPacket packet = new DatagramPacket(new byte[100], 100);
                    datagramSocket.receive(packet);
                    String data = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
                    System.out.println(packet.getSocketAddress() + " " + data);
                }
            } catch (Exception e) {
                System.out.println("수신 종료");
            }
        });

        thread.start();
        Thread.sleep(10000);
        datagramSocket.close();
    }
}