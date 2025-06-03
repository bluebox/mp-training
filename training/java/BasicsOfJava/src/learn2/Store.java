package learn2;

//import java.util.ArrayList;
import java.util.List;

public class Store extends ProductForSale{
	
	private List<String> products;
	
	public void addItem(String item) {
		
		products.add(item);
	}
	
	public void printOrderedItems() {
		
		products.forEach(System.out::println);
	}

	@Override
	void showDetails() {
		System.out.println();
		
	}
}
