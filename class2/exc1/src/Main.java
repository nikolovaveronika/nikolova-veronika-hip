import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter hostname:");
        String hostname = scanner.nextLine();


        try {
          //  InetAddress address = InetAddress.getByName(hostname);
         //   System.out.println("IP address" + address.getHostAddress());

            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            for (InetAddress address : addresses) {
                System.out.println(addresses);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}