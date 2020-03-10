package examples;

import java.awt.*;

public class BeepPrintExample1 {
    public static void main(String[] args) {
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        for (int i = 0; i < 5; i++) {
//            toolkit.beep();
//            try {
//                Thread.sleep(500);
//            }catch (Exception e){
//
//            }
//        }

//        Thread thread = new Thread(new BeepTask());

//        Thread thread = new BeepTask2();
        Thread thread = new Thread(()->{
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            for (int i = 0; i < 5; i++) {
                toolkit.beep();
                try {
                    Thread.sleep(500);
                }catch (Exception e){

                }
            }
        });
        thread.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("띵");
            try {
                Thread.sleep(500);
            }catch (Exception e){

            }
        }

    }
}

class BeepTask implements Runnable {
    @Override
    public void run() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i = 0; i < 5; i++) {
            toolkit.beep();
            try {
                Thread.sleep(500);
            }catch (Exception e){

            }
        }
    }
}
class BeepTask2 extends Thread {
    @Override
    public void run() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i = 0; i < 5; i++) {
            toolkit.beep();
            try {
                Thread.sleep(500);
            }catch (Exception e){

            }
        }
    }
}
