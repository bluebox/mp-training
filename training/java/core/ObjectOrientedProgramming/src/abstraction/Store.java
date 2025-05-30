package abstraction;

import java.util.ArrayList;
import java.util.Arrays;

public class Store {
	private ArrayList<ProductsForSale> store;
	
	public Store()
	{
		store = new ArrayList<>();
	}
	
	public void addItemToOrder(ProductsForSale...products)
	{
		store.addAll(Arrays.asList(products));
	}
	
	public void printOrder()
	{
		
	}
}
