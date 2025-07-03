
package com.day5_;

public class OrderItem {
	private int qty;
	private Product product;
	
	public OrderItem(int qty, Product product) {
		this.qty = qty;
		this.product = product;
	}

	public int getQty() {
		return qty;
	}

	public Product getProduct() {
		return product;
	}
}

