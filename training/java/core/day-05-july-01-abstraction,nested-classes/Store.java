package day5;

import java.util.ArrayList;

public class Store {
	private ArrayList<ProductForSale> listOfProducts;
	private ArrayList<OrderItem> listOfOrders;
	
	public Store() {
		listOfProducts=new ArrayList<>();
		listOfOrders=new ArrayList<>();
	}

	public void addItemToOrder(int qty, ProductForSale product) {
		listOfProducts.add(product);
		OrderItem newOrder = new OrderItem(qty, product);
		listOfOrders.add(newOrder);
	}

	public void printOrders() {
		System.out.println("Orders are");
		for (OrderItem orderItem : listOfOrders) {
			System.out.println("Quantity is " + orderItem.getQty());
			orderItem.getProduct().printPricedItem(orderItem.getQty());
		}
	}
}
