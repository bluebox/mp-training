
public class Main {
	public static void main(String[] args) {
		Customer c1 = new Customer("A", 2000, "a@gmail.com");
		Customer c2 = new Customer();
		Customer c3 = new Customer("B", "b@gmail.com");

		System.out.println(c1.getName()); 
		System.out.println(c2.getEmail()); 
		System.out.println(c3.getCreditLimit());
	}
    
}
