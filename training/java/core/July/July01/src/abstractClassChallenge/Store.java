package abstractClassChallenge;

import java.util.ArrayList;
import java.util.List;

public class Store {

	public static void main(String[] args) {
		
		List<ProductForSale> products = new ArrayList<>();
		
		products.add(new Tv(10000,"samsung"));
		products.add(new Tv(20000, "TCL"));
		products.add(new Phone(2000, "Realme"));
		products.add(new Phone(22000, "Oppo"));
		products.add(new Laptop(50000, "Acer"));
		products.add(new Laptop(70000, "HP"));
		
		System.out.println("Products List");
		System.out.println(products);
		
		
		
		List<OrderItem> orders = new ArrayList<>();
		for(var product: products) {
			addItemToOrder(orders,new OrderItem(4,product));
		}
		
		printOrder(orders);
		
		System.out.println("printing priced Items");
		for(var product: products) {
			product.printPricedItem(4);
		}
 
	}
	
	public static void addItemToOrder(List<OrderItem> orders, OrderItem order) {
		
		orders.add(order);
		System.out.println(order.getProduct().getType()+" Order Placed Successfully");
		
	}
	
	public static void printOrder(List<OrderItem> orders) {
		
		System.out.println("Orders:");
		for(var order : orders) {
			System.out.println(order);
		}
		
	}

}
