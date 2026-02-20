import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        Path dirPath = Paths.get("class1/src");
        String ext = ".java";

        try {
            DirectoryStream<Path> files = Files.newDirectoryStream(dirPath, "e" + ext);
            for (Path f : files) {
            System.out.println(f);
        }

        }catch (IOException e) {}
    }
}