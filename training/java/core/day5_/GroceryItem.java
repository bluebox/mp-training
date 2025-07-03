package com.day5_;
public class GroceryItem extends Product {
	private int qty;

	public GroceryItem(String type, double price, String description, int qty) {
		super(type, price, description);
		this.qty = qty;
	}

	@Override
	public void showDetails() {
		System.out.println("this is grocery item");
		super.printPricedItem(qty);
	}

}

