public class Main {
    public static void main(String[] args) {

        SharedObject sharedObject = new SharedObject();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedObject.incrementCounter();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedObject.incrementCounter();
            }
        });


        thread1.start();
                thread2.start();


                try {

                    thread1.join();
                    thread2.join();
                }catch (InterruptedException e) {

                }
        System.out.println("counter value:" + sharedObject.);
    }
}