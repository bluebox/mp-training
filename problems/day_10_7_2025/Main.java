package day_10_7_2025;

public class Main {

	public static void main(String[] args) {
		
		shoewarehouse warehouse=new shoewarehouse(5);
		shoewarehouse warehouse2=new shoewarehouse(5);
		warehouse.start();
		warehouse2.start();
		warehouse.receiveOrder(1);
		warehouse.receiveOrder(1);
		warehouse2.receiveOrder(3);
		warehouse.receiveOrder(1);
		warehouse.produceorder();
		warehouse.receiveOrder(1);
			warehouse2.produceorder();
		warehouse.receiveOrder(1);
	}

}
