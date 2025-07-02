package Project;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {
	private ArrayList<ProductForSale> productsForSale;
	private ArrayList<OrderItem> orderItems;
	
	public Store() {
		this.productsForSale=new ArrayList<ProductForSale>();
		this.orderItems=new ArrayList<OrderItem>();
	}
	
	public void addProduct(ProductForSale p) {
		productsForSale.add(p);
	}
	
	public boolean addItemToOrder(ProductForSale productForSale, int quantity) {
		try {
			OrderItem orderItem = new OrderItem(productForSale,quantity);
			this.orderItems.add(orderItem);
			System.out.println("Item Ordered Succesfully");
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public void printOrder() {
		int count=0;
		int total = 0;
		System.out.println("Current Order Details are : ");
		for(OrderItem o : this.orderItems) {
			count+=o.getQuantity();
			total+=o.getProduct().getSalesPrice(o.getQuantity());
			System.out.println(o);
		}
		System.out.println("Total Items ordered are : "+count);
		System.out.println("Total Amount to be paid : "+total);
	}
	
	public static void main(String [] args) {
		Scanner sc=new Scanner(System.in);
		Store store=new Store();
		int choice,quantity=0;
		ProductForSale productA=new ProductA(20, "This is Product A");
		store.addProduct(productA);
		ProductForSale productB=new ProductB(40, "This is Product B");
		store.addProduct(productB);
		ProductForSale productC=new ProductC(60, "This is Product C");
		store.addProduct(productC);
		System.out.println("========Product Selection Choices=======");
		System.out.println("1. Product A\n2. Product B\n3. Product C\n4. Print Reciept\n5. Exit System");
		while(true) {
			System.out.print("Enter your choice : ");
			String choiceInput=sc.next();
			try {
				choice=Integer.parseInt(choiceInput);
			}catch(NumberFormatException nfe) {
				System.out.println("Your choice must be among 1,2 and 3...");
				continue;
			}
			if(choice>=1 && choice<=3) {
				System.out.print("Enter the quantity you want to order : ");
				try {
					quantity=sc.nextInt();
					if(quantity<=0) {
						System.out.println("Quantity must be a Countable Number, Please select your choice and quantity carefully...");
						continue;
					}
				}catch(Exception e) {
					System.out.println("Quantity must be a Countable Number, Please select your choice and quantity carefully...");
					continue;
				}
			}
			switch(choice) {
			case 1: {
				store.addItemToOrder(productA, quantity);
				break;
			}
			case 2: {
				store.addItemToOrder(productB, quantity);
				break;
			}
			case 3: {
				store.addItemToOrder(productC, quantity);
				break;
			}
			case 4: {
				store.printOrder();
				break;
			}
			case 5: {
				System.out.println("Exiting...");
				sc.close();
				return;
			}
			default: {
				System.out.println("Your choice must be among 1,2 and 3...");
				continue;
			}
			}
		}
	}
}
