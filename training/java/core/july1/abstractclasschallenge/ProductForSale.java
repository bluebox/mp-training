package com.tulasidhar.july1.abstractclasschallenge;

public abstract class ProductForSale {
	String type;
	double price;
	String description;
	
	void printPricedItem(int qty) {
		System.out.printf("%d of %s costs %f @ %f per %s\n" , qty , type , (qty*price),price,type);
	}
	
	double getSalesPrice(int qty) {
		return qty*price;
	}
	
	abstract void showDetails();
}
