package Day5_01_07;

import java.util.ArrayList;

public class Store {
	ArrayList<ProductForSale> ord=new ArrayList<>();
	
	public void addItemsToOrder(ProductForSale p){
		ord.add(p);
	}
	public void printOrder() {
		for(ProductForSale p:ord) {
			p.printPricedItem(5);
			System.out.println();
			
		}
	}
	public static void main(String args[]) {
		Store st=new Store();
		st.addItemsToOrder(new ProductA(50.0,"This is type a"));
		st.addItemsToOrder(new ProductB(60.0,"This is type b"));
		st.addItemsToOrder(new ProductC(70.0,"This is type c"));
		st.addItemsToOrder(new ProductC(80.0,"This is type c"));
		st.printOrder();
	}
}
