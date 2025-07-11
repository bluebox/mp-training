package day11;

public class Consumer implements Runnable {

    private ShoeWarehouse warehouse;
    private String name;

    public Consumer(ShoeWarehouse warehouse, String name) {
        this.warehouse = warehouse;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Order order = warehouse.fulfillOrder();
            System.out.println(name + " fulfilled: " + order);
        }
    }
}
