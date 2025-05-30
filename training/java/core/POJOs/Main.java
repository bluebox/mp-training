package POJOs;


public class Main {
	public static void main(String args[]) {
		
		Products product=new Products("Cipla",76.99,100);
		System.out.println(product.toString());
		product.setProductName("Glenmark");
		System.out.println(product.toString());
		
		Customers customer=new Customers("Alice","9876543412","example.mail");
		System.out.println(customer.toString());

	}
}
