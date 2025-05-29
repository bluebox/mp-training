package AbstractClasses;

import java.util.ArrayList;

public class Store {
	public static void main(String[] args) {
		ArrayList<OrderItem> lis=new ArrayList<OrderItem>();
		OrderItem o1=new OrderItem(5,new ProductA("Milk",19.8,"Household Item"));
		OrderItem o2=new OrderItem(100,new ProductB("Water",5.7,"Drink"));
		OrderItem o3=new OrderItem(50,new ProductC("Bread",170.8,"Food"));
		lis.add(o1);
		lis.add(o2);
		lis.add(o3);
		lis.get(0).p.showDetails();
		lis.get(1).p.showDetails();
		lis.get(2).p.showDetails();
		
		
		
		
	}
	

}
