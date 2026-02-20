import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) throws IOException {
        Path pathDir = Paths.get("class1");

        int days = 7;

        Files.walkFileTree(pathDir, new SimpleFileVisitor<>() {

            @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
                LocalDate lastModTime = attr.lastModifiedTime()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                LocalDate now = LocalDate.now();
                if (lastModTime.isAfter(now.minusDays(days))) {
                    System.out.println(file);
            }

                return FileVisitResult.CONTINUE;


            }
        });
    }
}