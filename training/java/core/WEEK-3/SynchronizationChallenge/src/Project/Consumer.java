package Project;

public class Consumer implements Runnable {
    private final ShoeWarehouse warehouse;

    public Consumer(ShoeWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                warehouse.fulfillOrder();
                Thread.sleep(150); // Simulate processing time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

