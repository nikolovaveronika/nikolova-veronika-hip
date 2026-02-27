import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class Main {
    public static void main(String[] args) {

        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop operations are not supported");
            return;
        }
        try {
            Desktop desktop = Desktop.getDesktop();
          //  File file = new File("uniqueFile.txt");
          //  desktop.open(file);

            URI uri = URI.create("mailto:nikolovaveronika07@gmail.com?subject=Test&body=Something");
            desktop.mail(uri);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}