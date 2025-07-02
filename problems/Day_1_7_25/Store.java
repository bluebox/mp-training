package Day_1_7_25;
import java.util.*;

record OrderItem(int qty,ProductForSale product) {
	
}

public class Store {
	
	static Scanner sc=new Scanner(System.in);
	
	public static ArrayList<ProductForSale> productslist=new ArrayList<>();
    public ArrayList<OrderItem> orders=new ArrayList<>();
	public static void main(String[] args) {
		productslist.add(new Book("Stationary",10,"To write"));
		productslist.add(new Pen("Stationary",5,"will write"));
		int num=2;
		ArrayList<OrderItem> order=new ArrayList<>();
		for(int i=0;i<num;i++) {
			additemtoorder(order,0,5);
		}
         
	}
	
	public static void additemtoorder(ArrayList<OrderItem> order,int orderInd,int qty) {
			order.add(new OrderItem(qty,productslist.get(orderInd)));
	}
	
	public static ArrayList<OrderItem> printOrder(ArrayList<OrderItem> order) {
		 return order;
	}

}
