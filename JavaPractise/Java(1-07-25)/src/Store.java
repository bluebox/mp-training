import java.util.*;
public class Store {
	  static ArrayList<ProductForSale>storeProducts=new ArrayList<>();
		public static void main(String[] args) {
		// TODO Auto-generated method stub
		    storeProducts.add(new A("Wireless Mouse",550.00,"Ergonomic wireless mouse with USB receiver"));
		    storeProducts.add(new A("Bluetooth",1500.50,"Ergonomic mouse with silent clicks and long battery life."));
		    System.out.println("Products for Sale");
		    listProducts();
		    ArrayList<OrderItem> myOrder = new ArrayList<>();
	        addItemToOrder(myOrder, 2, 0); 
	        addItemToOrder(myOrder, 1, 1); 
	        System.out.println("Reciept");
	        printOrderedItems(myOrder);

	}
		public static void listProducts()
		{
			for(var item:storeProducts)
			{
				item.showDetails();
			}
		}
		public static void addItemToOrder(ArrayList<OrderItem>order,int quantity,int orderIndex)
		{
			order.add(new OrderItem(quantity,storeProducts.get(orderIndex)));
		}
		public static void printOrderedItems(ArrayList<OrderItem>order)
		{
			double total = 0.0;
	        for (OrderItem oi : order) {
	            oi.product().printPricedItem(oi.quantity());
	            total += oi.product().getSalesPrice(oi.quantity());
	        }
	        System.out.printf("%nTotal amount due: $%.2f%n", total);
	    }
		
			
		}
