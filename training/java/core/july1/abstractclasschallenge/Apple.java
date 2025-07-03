package com.tulasidhar.july1.abstractclasschallenge;

public class Apple extends ProductForSale{
	
	public Apple(String name , double price){
		super.type = name;
		super.price = price;
	}
	
	@Override
	void showDetails() {
		System.out.println("This is an apple , good for your health :)");	
	}
	
	
}
