import java.io.InterruptedIOException;

public class AThread extends Thread {

    private int counter = 10;
    private static int threadCounter = 0;
    private int threadNo = ++ threadCounter;

    public AThread() {

        System.out.println("Created thread with number: " + threadNo);
    }

    @Override

    public void run() {
        while (true) {

            System.out.println("Thread" + threadNo + "(" + counter + ")" );

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            counter--;

            if (counter == 0) {
                return;
            }
        }
    }

}
