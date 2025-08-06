package com.example.inversio_control;

public class OnePlus implements Mobiles {

	Color color;
	OnePlus()
	{
		System.out.println("Oneplus constructor triggered");
	}
	OnePlus(Color colorObject)
	{
		this.color=colorObject;
	}
	
	
	@Override
	public void getModelAndColor() {
		System.out.println("Model: 9 plus");
		color.getOnePlusColor();
		
	}

	@Override
	public void getWeight() {
		System.out.println("0.16kg");
		
	}

}
