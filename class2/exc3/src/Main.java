import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        String urlString = "https://github.com";
        String filePathString = "downloadContent.html";
        downloadContent(urlString, "downloadContent1.html");

        try {
            InputStream inputStream = new BufferedInputStream(new URL(urlString).openStream());
            FileOutputStream fileOutputstream = new FileOutputStream(filePathString);
            byte[] bufferData = new byte[1024];
            int dataRead;

            while ((dataRead = inputStream.read(bufferData, 0, bufferData.length)) != -1) {

                fileOutputstream.write(bufferData, 0, dataRead);
            }
            System.out.println("Downloading finished ");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//2 nacin polesen
    public static void downloadContent(String urlString, String filePath) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(urlString).openStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();

            }
            System.out.println("Finished another download");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}