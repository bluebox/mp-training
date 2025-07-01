package Day5;

import java.util.ArrayList;
import java.util.List;


 record OrderItems(int quant, ProductForSale product){}


public class Store {
	private static List<ProductForSale> products =new ArrayList<>();
	private static List<OrderItems> Items=new ArrayList<>();
	
	public static void addItemToOrder(ProductForSale product) {
		products.add(product); 
	}
	
	public static void printTheOrders() {
		for(int i=0;i<Items.size();i++) {
			System.out.println("Quantity : "+Items.get(i).quant()+" Total Price : "+Items.get(i).product().getSalesPrice(Items.get(i).quant()));
			Items.get(i).product().printPricedItem(Items.get(i).quant());
		}
	}
	
	public static void OrderItem(int ind,int quant) {
		if(ind>=0&&ind<=products.size()) {
			ProductForSale pro=products.get(ind);
			Items.add(new OrderItems(quant, pro));
		}
	}
	
	
	public static void main(String[] args) {
		Store store=new Store();
		addItemToOrder(new Furniture("Chair",100,"Wooden Chair"));
		addItemToOrder(new Clothing("Shirt",500,"Branded Shirt"));
		addItemToOrder(new Electronics("phone",1000,"Smart Phone"));
		
		for(int i=0;i<products.size();i++) {
			System.out.print(i+" ");
			products.get(i).showDetails();
		}
		
		OrderItem(0,5);
		OrderItem(1,10);
		printTheOrders();
	}	
}
