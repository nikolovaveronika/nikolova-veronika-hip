import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path filePath = Paths.get("class1/binaryF.bin");
        byte[] dataToWrite = {1, 2, 3, 5, 'C' };

        try {
            Files.write(filePath, dataToWrite);
            System.out.println("Data successfully writtten to file");

            byte[] readData = Files.readAllBytes(filePath);
            for (byte b : readData) {
                System.out.println(b + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}