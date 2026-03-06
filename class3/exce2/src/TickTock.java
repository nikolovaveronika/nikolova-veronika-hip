public class TickTock implements Runnable {

    private String name;
    private int rate;


    public TickTock(String name, int rate) {
        this.name = name;
        this.rate = rate;
    }

    @Override
    public void run() {
        while (true) {

            System.out.println("Clock" + name + " Tick");
            try{
                Thread.sleep(rate);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Clock" + name + " Tack");

            try{
                Thread.sleep(rate);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
