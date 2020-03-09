package examples;

public class Member implements Cloneable {
    public String id;
    public String name;
    public String password;
    public int age;
    public boolean adult;
    public int[] scores;

    public Member(String id, String name, String password, int age, boolean adult,int[] scores) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.adult = adult;
        this.scores = scores;
    }

    public Member getMember() {
        Member cloned = null;
        try {
            cloned = (Member) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloned;
    }
}

class MemberExample {
    public static void main(String[] args) {
        Member original = new Member("blue", "홍길동", "12345", 25, true,new int[]{4,5,6});
        Member cloned = original.getMember();
        cloned.scores[0] = 1;
        for(int s : original.scores){
            System.out.println(s);
        }
        for(int s : cloned.scores){
            System.out.println(s);
        }

    }
}
