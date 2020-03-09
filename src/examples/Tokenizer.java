package examples;

import java.util.StringTokenizer;

public class Tokenizer {

    public static void main(String[] args) {
        String a = "a,a,a,a,a,a,a";
        StringTokenizer stringTokenizer = new StringTokenizer(a,",");
        System.out.println(stringTokenizer.nextToken());
    }
}
