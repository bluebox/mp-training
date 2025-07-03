package com.tulasidhar.july1.abstractclasschallenge;

import java.util.ArrayList;

public class Store {
	ArrayList<ProductForSale> products ;
	ArrayList<OrderItem> order;
	
	public static void main(String[] args) {
		Store store = new Store();
		
		ProductForSale item1 = new Apple("Apple",2.3d);
		ProductForSale item2 = new Orange("Orange",43.2d);
		ProductForSale item3 = new Guava("Guava",3.1d);
		
		store.addItemToOrder(new OrderItem(2,item1));
		store.addItemToOrder(new OrderItem(4,item2));
		store.addItemToOrder(new OrderItem(1,item3));
		
		store.printOrder();
		
	}
	
	public Store() {
		products = new ArrayList<ProductForSale>();
		order = new ArrayList<OrderItem>();
	}
	
	void addItemToOrder(OrderItem orderItem){
		order.add(orderItem);
	}
	
	void printOrder() {
		for(OrderItem o: order) {
			o.product.printPricedItem(o.qty);
		}
	}
}
