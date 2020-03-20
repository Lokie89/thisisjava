package examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FilesCopyMethodExample {
    public static void main(String[] args) throws IOException {
        Path from = Paths.get("src/examples/goodPo.jpg");
        Path to = Paths.get("src/examples/goodPo3.jpg");
        Files.copy(from,to, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("파일 복사 성공");
    }
}
