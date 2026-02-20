import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

//        Path filePath = Paths.get("class1/src/");
        String fileName = "uniqueFile_";
        long timestamp = System.currentTimeMillis();
        Path filePath = Paths.get(fileName + timestamp + ".txt");


        try {
            if (Files.exists(filePath)) {
                System.out.println("File already exist");
            } else {
                Files.createFile(filePath);
            }

            File file = new File(fileName + timestamp + ".txt");
            //sout = System.out.println();
            System.out.println("File name:" + file.getName());
            System.out.println("File locatuion:" + file.getPath());
            System.out.println("File absolute path" + file.getAbsolutePath());
            System.out.println("File read privilidge" + file.canRead());
            System.out.println("File write privilidge" + file.canWrite());
            System.out.println("File exec privilidge" + file.canExecute());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}