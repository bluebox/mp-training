package abstractexamples;

import java.util.ArrayList;
import java.util.List;

public class Store {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ProductForSale> pfs=new ArrayList<>();
		ArrayList<OrderItem> orders=new ArrayList<>(); 
		
		ProductForSale milk=new Dairy("Milk",12);
		ProductForSale laptop=new Electronics("Laptop",95000);
		ProductForSale curd=new Dairy("curd",15);
		addItem(orders,milk,3);
		addItem(orders,laptop,1);
		addItem(orders,curd,3);
		printOrderedItems(orders);
		

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
