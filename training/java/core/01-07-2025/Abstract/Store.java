package Abstract;
import java.util.*;
public class Store {

	private ArrayList<Product> list1=new ArrayList<>();
	private ArrayList<OrderItem> list2=new ArrayList<>();
	
	void add(Product p)
	{
		list1.add(p);
	}
	void displayProducts()
	{
		for(Product p:list1)
		{
			p.displayDetails();
		}
	}
	void addToOrder(Product product,int quantity)
	{
		list2.add(new OrderItem(product,quantity));
	}
	public void printOrder()
	{
		for(OrderItem item:list2)
		{
			item.printItem();
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Store mystore=new Store();
		Product p1=new Book("Java ",300,"James Gosling");
		Product p2=new Gadget("Python",200,"Premium");
		mystore.add(p1);
		mystore.add(p2);
		mystore.displayProducts();
		mystore.addToOrder(p2, 2);
		mystore.addToOrder(p1,1);
		mystore.printOrder();

	}

}
