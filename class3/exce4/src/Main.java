import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executor.execute(new Worker("Local thread" + i));
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
            //wait
            System.out.println("Waiting..");
        }
        System.out.println("all threads ended");
    }
}

//napisan main, nemam se vo class