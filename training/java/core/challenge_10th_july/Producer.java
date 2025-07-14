package challenge_10th_july;

public class Producer extends Thread {
    private final ShoeWarehouse warehouse;

    public Producer(ShoeWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                String shoeType = ShoeWarehouse.productList.get(i % ShoeWarehouse.productList.size());
                Order order = new Order(i, shoeType, (i % 3)+1);
                warehouse.receiveOrder(order);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
