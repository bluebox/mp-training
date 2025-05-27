package POS_System;

public class Product {
	// 	Implementing Encapsulation Pillar of OOP
	private  String productId;
	private String name;
	private double pricePerUnit;
	
	public Product(String productId, String name, double pricePerUnit)
	{
		this.productId = productId;
		this.name = name;
		this.pricePerUnit = pricePerUnit;
	}
	
	// Implementing getters and setters for controlled access of our private data members
	
	public String getProductId()
	{
		return productId;
	}
	public String getName()
	{
		return name;
	}
	public double getPricePerUnit()
	{
		return pricePerUnit;
	}
	public void setProductId(String productId)
	{
		if(productId == null || productId.isBlank())
		{
			System.out.println("Invalid or no Product ID entered !!!");
		}
		else
		{
			this.productId = productId;
		}
	}
	public void setName(String name)
	{
		if(name == null || name.isBlank())
		{
			System.out.println("Invalid product name entered !!!");
		}
		else
		{
			this.name = name;
		}
	}
	public void setpricePerUnit(double pricePerUnit)
	{
		if(pricePerUnit < 0.0)
		{
			System.out.println("Invalid price entered for the given product !!!");
		}
		else
		{
			this.pricePerUnit = pricePerUnit;
		}
	}
	
	@Override
	public String toString()
	{
		return "Product ID: " + productId + "\n" +
				"Product Name:" + name + "\n" + 
				"Price/unit: Rs." + pricePerUnit + "\n";
	}
}
