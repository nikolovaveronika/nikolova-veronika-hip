public class ServingCounter {


    private String [] dishes;
    private int capacity;
    private int counter;

    public ServingCounter(int capacity) {
        this.capacity = capacity;
        dishes = new String[capacity];
        System.out.println("serving counter is ready with max capacity of:" + );

    }

    public synchronized void addDish(String chef, String dish) throws InterruptedException {
        while (count > capacity) {
            System.out.println("Chef " + chef + "is waiting for free space");
            wait();
        }
        dishes[count] = dish;
        count++;

        notifyAll();
    }

    public synchronized void takeDish(String waiter) throws InterruptedException {
        while (count < 0) {
            System.out.println("Chef " + waiter + "is waiting for new dish");
            wait();
        }

        String dish = dishes[count - 1];
        count--;

        notifyAll();

        System.out.println("waiter" + waiter " took a " + dish + " from the table");

        return dish;

    }

}
