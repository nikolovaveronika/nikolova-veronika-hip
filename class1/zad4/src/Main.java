import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Path dirPath = Paths.get("class1/src");

        try{

            Stream<Path> files = Files.list(dirPath) ;
            if(files.findFirst().isPresent()) {
                System.out.println("Dir is not empty");

            }else
                System.out.println("Dir is empty");

        }catch (IOException e) {e.printStackTrace();
    }
}