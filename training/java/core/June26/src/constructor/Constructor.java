package constructor;

public class Constructor {
	public static void main(String[] args) {
		
		Customer customer1 = new Customer();
		System.out.println(customer1.getName());
		System.out.println(customer1.getCreditLimit());
		System.out.println(customer1.getEmailAddress());
		
		Customer customer2 = new Customer("sam","sam@gmail.com");
		System.out.println(customer2.getName());
		System.out.println(customer2.getCreditLimit());
		System.out.println(customer2.getEmailAddress());
		
		Customer customer3 = new Customer("kalyan", 30000, "kalyan@gmail.com");
		System.out.println(customer3.getName());
		System.out.println(customer3.getCreditLimit());
		System.out.println(customer3.getEmailAddress());
		
	}
}
