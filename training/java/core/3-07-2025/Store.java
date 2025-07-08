import java.util.ArrayList;

public class Store {
	private ArrayList<Product> listOfProducts;
	private ArrayList<OrderItem> listOfOrders;
	
	public Store() {
		listOfProducts=new ArrayList<>();
		listOfOrders=new ArrayList<>();
	}

	public void addItemToOrder(int qty, Product product) {
		OrderItem newOrder = new OrderItem(qty, product);
		listOfOrders.add(newOrder);
	}

	public void printOrders() {
		System.out.println("The Placed Orders are");
		for (OrderItem orderItem : listOfOrders) {
			System.out.println("Quantity is " + orderItem.getQty());
			orderItem.getProduct().printPricedItem(orderItem.getQty());
		}
	}
}