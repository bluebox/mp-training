package Project;

public class Producer implements Runnable {
    private final ShoeWarehouse warehouse;

    public Producer(ShoeWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            Order order = new Order(i, ShoeWarehouse.productList.get(i % ShoeWarehouse.productList.size()), i * 2);
            try {
                warehouse.receiveOrder(order);
                Thread.sleep(100); // Simulate time to produce
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

