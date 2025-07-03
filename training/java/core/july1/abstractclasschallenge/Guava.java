package com.tulasidhar.july1.abstractclasschallenge;

public class Guava extends ProductForSale{
	
	
	public Guava(String name , double price){
		super.type = name;
		super.price = price;
	}
	
	@Override
	void showDetails() {
		System.out.println("This is an Guava , its pink inside :)");	
	}
	
	
}
