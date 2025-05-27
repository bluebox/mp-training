package POS_System;

// Utility class, does not store any instance data members i.e only for calculation
public class Bill {
	
	public static double calculateTotal(Product product, int quantity)
	{
		if(product == null || quantity <= 0)
		{
			return 0;
		}
		else
		{
			double totalBill = product.getPricePerUnit() * quantity;
			return totalBill;
		}
	}
	
	public static double calculateTotal(Product product, int quantity, double discountPercentage)
	{
		double totalBill = calculateTotal(product, quantity);
		if(discountPercentage > 0 && discountPercentage <= 100)
		{
			totalBill = totalBill - ((totalBill * discountPercentage)/100);
		}
		return totalBill;
	}
	
	public static double calculateTotal(Product product, int quantity, double discountPercentage, double gstPercentage)
	{
		double totalBill = calculateTotal(product, quantity, discountPercentage);
		if(gstPercentage > 0 && gstPercentage <= 100)
		{
			totalBill = totalBill + ((gstPercentage / 100) * totalBill);
		}
		return totalBill;
	}
}
