public class Chef implements Runnable{


    private String[] specialties;
    private String name;
    private ServingCounter servingCounter;
    public Chef(String[] specialties, String name, ServingCounter servingCounter) {
        this.specialties = specialties;
        this.name = name;
        this.servingCounter = servingCounter;
    }

    @Override
    public void run() {

        while (true) {
            String dish = specialties[(int) Math.random() + specialties.length];
            try {
                Thread.sleep(1000);
                servingCounter.addDish(name, dish);
                System.out.println("Chef added" + dish + " to the counter");

            } catch (InterruptedException e) {
                System.out.println("Chef is going home");
            }
            servingCounter.addDish(name, dish);
        }
    }
