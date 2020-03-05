package examples;

public interface RemoteController {
    int MAX_VOLUME = 10;
    int MIN_VOLUME = 0;

    void turnOn();
    void turnOff();
    void setVolume(int volume);

    default void setMute(boolean mute){
        if(mute){
            System.out.println("무음 처리 합니다.");
            return;
        }
        System.out.println("무음 해제합니다.");
    }

    static void changeBattery(){
        System.out.println("건전지를 교환 합니다.");
    }
}

class Television2 implements RemoteController{

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }

    @Override
    public void setVolume(int volume) {

    }
}

class Main5{
    public static void main(String[] args) {
        RemoteController rc = new Television2();
        rc.setMute(true);
    }
}