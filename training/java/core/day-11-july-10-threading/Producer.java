package day11;

public class Producer implements Runnable {
	private ShoeWarehouse warehouse;
	public Producer(ShoeWarehouse warehouse) {
		this.warehouse = warehouse;
	}
	@Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            String shoeType = ShoeWarehouse.productList.get(i % ShoeWarehouse.productList.size());
            Order order = new Order(i, shoeType, 1);
            warehouse.receiveOrder(order);
        }
    }
}
