package POS_System;

public class Main {
	public static void main(String [] args)
	{
//		Product p1 = new Product("P1110222", "Montelukast strip", 150.0);
//		Product p2 = new Product("P1110223", "Dolo 650 strip", 24.73);
		
		Product [] products = {
				new Product("P1110222", "Montelukast strip", 150.0),
				new Product("P1110223", "Dolo 650 strip", 24.73),
				new Product("P1110223", "Dettol Disinfectant Liquid", 98.0),
				new Product("P1110223", "Thermometer", 259.00)
		};
		
		// manually assigning quantities, discount and gst for each product
		int [] quantities = {3, 2, 1, 3};
		double [] discountPercentages = {10.0, 5.0, 5.0, 0.0};
		double [] gstPercentages = {12.0, 6.0, 0.0, 0.0};
		
		System.out.println("Details of each products are as follows: ");
		System.out.println("-------------------------------------------------------------------------");
		// traversing the products array to compute each product's bill on the basis of quantity, discount and GST
		for(int i = 0; i < products.length; i++)
		{
			 Product singleProduct = products[i];
			 int qty = quantities[i];
			 
			 System.out.println(singleProduct.toString());
			 System.out.println("Billing for " + singleProduct.getName() + " is as follows: ");
			 
			 if(discountPercentages[i] == 0.0 && gstPercentages[i] == 0.0)
			 {
				 double netBill = Bill.calculateTotal(singleProduct, qty);
				 System.out.println("Total Bill (neither discount nor GST is applicable): " + netBill);
			 }
			 else if(gstPercentages[i] == 0.0)
			 {
				 double netBill = Bill.calculateTotal(singleProduct, qty, discountPercentages[i]);
				 System.out.println("Total Bill (only discount is applicable): " + netBill);
			 }
			 else
			 {
				 double netBill = Bill.calculateTotal(singleProduct, qty, discountPercentages[i], gstPercentages[i]);
				 System.out.println("Total Bill (both discount and GST are applicable): " + netBill);
			 }
			 System.out.println("-------------------------------------------------------------------------");
		}
	}
}
