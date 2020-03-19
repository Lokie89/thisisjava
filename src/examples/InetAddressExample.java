package examples;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {
    public static void main(String[] args) {
        try {
            InetAddress local = InetAddress.getLocalHost();
            System.out.println(local.getHostAddress());

            InetAddress[] iaArr = InetAddress.getAllByName("www.naver.com");
            for(InetAddress ia : iaArr){
                System.out.println(ia.getHostAddress());
            }
        } catch (UnknownHostException e) {
        }
    }
}
