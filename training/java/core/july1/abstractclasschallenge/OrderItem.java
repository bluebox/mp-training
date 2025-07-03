package com.tulasidhar.july1.abstractclasschallenge;

//using class here instead of record because java version: 1.8 does not have record

public class OrderItem {
	int qty;
	ProductForSale product;
	
	public OrderItem(int qty, ProductForSale product) {
		this.qty = qty;
		this.product = product;
	}
	
}