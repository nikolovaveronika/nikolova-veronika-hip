public class Main {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new TickTock("clock1", 750));
        Thread thread2 = new Thread(new TickTock("clock2", 550));

        thread1.start();
        thread2.start();

    }
}