package abstraction;

public class Main {

	public static void main(String[] args) {
		
		Store s1 = new Store();
		
		Product1 p1 = new Product1("health",80.0,"sanitizer");
		Product1 p2 = new Product1("Eatable",70.0,"Sweet");
		
		OrderItem o1 = new OrderItem(10,p1);
		OrderItem o2 = new OrderItem(20, p2);
		
		System.out.println("\n"+o1+"\n");
		System.out.println("\n"+o2);
		
		
	}

}
