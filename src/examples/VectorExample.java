package examples;

import java.util.List;
import java.util.Vector;

public class VectorExample {
    public static void main(String[] args) {
        List<Board> list = new Vector<>();
        for (int i = 1; i <= 5; i++) {
            list.add(getBoard(i));
        }
        list.remove(2);
        list.remove(3);
        for (int i = 0; i < list.size(); i++) {
            Board board = list.get(i);
            System.out.println(board.subject + "\t" + board.content + "\t" + board.writer);
        }
    }

    public static Board getBoard(int i) {
        return new Board("제목" + i, "내용" + i, "글쓴이" + i);
    }
}

class Board {
    String subject;
    String content;
    String writer;

    public Board(String subject, String content, String writer) {
        this.subject = subject;
        this.content = content;
        this.writer = writer;
    }

}