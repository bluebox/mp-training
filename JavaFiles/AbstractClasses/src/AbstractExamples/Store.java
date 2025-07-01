package AbstractExamples;

import java.util.ArrayList;
import java.util.List;

public class Store {

	public static void main(String[] args) {
			
		ArrayList<ProductForSale> pro = new ArrayList<>();
		ArrayList<OrderItem> itm =new ArrayList<>();
		
		ProductForSale mobile =new Electronics("Mobile",10000);
		
		ProductForSale milk=new Dairy("Milk",12);
		
		ProductForSale laptop=new Electronics("Laptop",95000);
		
		ProductForSale curd=new Dairy("curd",15);
		
		addItem(itm,milk,3);
		addItem(itm,laptop,1);
		addItem(itm,curd,3);
		printOrderedItems(itm);
		

	}
	
	public static void addItem(ArrayList<OrderItem> lst,ProductForSale item,int quantity)
	{
		lst.add(new OrderItem(quantity,item));
		
		
	}
	public static void printOrderedItems(List<OrderItem> lst)
	{
		for(OrderItem item : lst)
		{
			var product=item.product();
			product.showDetails();
			product.printPrice(item.quanity());
			System.out.println();
		}
		
	}

}
