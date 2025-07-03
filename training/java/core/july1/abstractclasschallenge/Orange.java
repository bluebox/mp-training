package com.tulasidhar.july1.abstractclasschallenge;

public class Orange extends ProductForSale{
	
	public Orange(String name , double price){
		super.type = name;
		super.price = price;
	}
	
	@Override
	void showDetails() {
		System.out.println("This is an Orange , Tastes tangy :)");	
	}
	
	
}
