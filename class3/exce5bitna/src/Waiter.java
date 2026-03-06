public class Waiter implements Runnable{


    private String[] specialties;
    private String name;
    private ServingCounter servingCounter;
    public Waiter(String[] specialties, String name, ServingCounter servingCounter) {
        this.specialties = specialties;
        this.name = name;
        this.servingCounter = servingCounter;
    }

    @Override
    public void run() {

        while (true) {

            try {
                String dish = servingCounter.takeDish(name);
                System.out.println("Serign the dish to the customer");
                Thread.sleep(1000);
                System.out.println("CWaiter" + name + " served " + dish + " to the customerr");

            } catch (InterruptedException e) {
                System.out.println("Chef is going home");
            }
            servingCounter.addDish(name, dish);
        }
}
