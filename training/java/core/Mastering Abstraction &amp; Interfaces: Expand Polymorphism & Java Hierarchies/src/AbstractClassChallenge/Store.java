package AbstractClassChallenge;

import java.util.ArrayList;

public class Store {
	private ArrayList<ProductForSale> products=new ArrayList<>();
	private ArrayList<OrderItem> order=new ArrayList<>();
	public static void main(String[] args)
	{
		
	}
	public void setupStore()
	{
		this.products.add(new ProductA("pen",10.0,"a nice pen"));
		this.products.add(new ProductB("pencil",5.0,"a nice pencil apsara"));
		this.products.add(new ProductC("eraser",2.0,"a nice eraser"));		
	}
	public void printProducts()
	{
		for(var i:products)
		{
			i.showDetails();
		}
	}
	public void addItemToOrder(ProductForSale p,int qty)
	{
		order.add(new OrderItem(qty, p));
	}
	public void printOrder()
	{
		for(var i:order)
		{
			i.product().printPricedItem(i.quantity());
		}
	}
}
