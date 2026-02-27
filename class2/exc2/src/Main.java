import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        try {
            URI uri = URI.create("https://uacs.edu.mk/home/home");
            URL url = uri.toURL();

            System.out.println(url.getAuthority());
            System.out.println("Path" + url.getPath());
            System.out.println("Protocol:" + url.getProtocol());
            System.out.println("Default gate " + url.getDefaultPort());
            System.out.println(url.getQuery());

        }catch (MalformedURLException e ) {
            e.printStackTrace();
        }
    }
}