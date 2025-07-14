package challenge_10th_july;

public class Consumer extends Thread {
    private final ShoeWarehouse warehouse;

    public Consumer(ShoeWarehouse warehouse, String name) {
        super(name);
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Order order = warehouse.fulfillOrder();
                System.out.println(getName() + " processed " + order);
                Thread.sleep(150); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

