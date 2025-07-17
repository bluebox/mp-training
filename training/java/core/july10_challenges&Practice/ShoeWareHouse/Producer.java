package shoeWareHouse;

public class Producer implements Runnable {
    private final ShoeWarehouse warehouse;

    public Producer(ShoeWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            String type = ShoeWarehouse.productList.get(i % ShoeWarehouse.productList.size());
            Order order = new Order(i, type, (i % 3) + 1);
            warehouse.receiveOrder(order);

            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
