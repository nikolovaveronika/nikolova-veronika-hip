public class SharedObject {

    private int counter= 0;

    public synchronized void incrementCounter() {
        counter++;

    }

}
