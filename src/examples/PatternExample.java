package examples;

import java.util.regex.Pattern;

public class PatternExample {
    public static void main(String[] args) {
        String regExp = "(02|010)-\\d{1,4}-\\d{4}";
        String data = "010-548-7878";
        boolean result = Pattern.matches(regExp, data);
        System.out.println(result);
    }
}
