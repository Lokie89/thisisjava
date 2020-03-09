package examples;

import java.util.Objects;

public class NewInstance {
    interface Action {
        public void execute();
    }

    static class SendAction implements Action {
        @Override
        public void execute() {
            System.out.println("데이터를 보냅니다");
        }
    }

    static class ReceiveAction implements Action {
        @Override
        public void execute() {
            System.out.println("데이터를 받습니다");
        }
    }

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("examples.NewInstance$SendAction");
            Class clazz2 = Class.forName("examples.NewInstance$ReceiveAction");
            Action action = (Action) clazz2.newInstance();
            action.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
