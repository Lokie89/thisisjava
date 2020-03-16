package examples;

public class Student6 implements Comparable<Student6> {
    private String name;
    private int score;

    public Student6(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Student6 o) {
        return Integer.compare(score, o.score);
    }
}