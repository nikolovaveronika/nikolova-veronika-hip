public class Main {
    public static void main(String[] args) {

       for (int i = 0; i < 5; i++ ) {
           AThread aThread = new AThread();
           aThread.start();
           System.out.println("Thread name: " + aThread.getName());
           System.out.println("Thread priority: " + aThread.getPriority());
           System.out.println("If the thread is alive " + aThread.isAlive());

       }

        System.out.println("All threads are running");

    }
}