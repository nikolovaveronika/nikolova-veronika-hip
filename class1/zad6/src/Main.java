import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main(String[] args) {
    Path filePath = Paths.get("class1/appendText.txt");
    String content = "Java is fun";

    Files.write(filePath, content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND );


    }
}