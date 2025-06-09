package ds;

public class MainBank {
	
	public static void main(String[] args) {
		
		Bank bank = new Bank("SBI");
		
		bank.addBranch("Hyderabad");
		bank.addCustomer("Hyderabad", "Deepak ", 1200.00);
//		System.out.println(bank);
		System.out.println(bank.listCustomers("Hyderabad", true));
		System.out.println(bank.getBranches());
		
		
		bank.addBranch("Tadepalligudem");
		bank.addCustomer("Tadepalligudem", "Sri", 123.50);
		bank.addCustomerTransaction("Tadepalligudem", "Sri", 240.23);
		bank.addCustomerTransaction("Tadepalligudem", "Sri", -540.23);
		bank.addCustomerTransaction("Tadepalligudem", "Sri", 40.23);
		
		bank.addCustomer("Tadepalligudem", "Prasad", 23.50);
		System.out.println(
			bank.addCustomerTransaction("Tadepalligudem", "Prasad", 240.23) + " " +
			bank.addCustomerTransaction("Tadepalligudem", "Prasad", 2540.23)+ " " +
			bank.addCustomerTransaction("Tadepalligudem", "Prasad", 40.23) + " " +
			bank.addCustomerTransaction("Tadepalligudem", "Prasad", 2400.23));
		
		bank.listCustomers("Tadepalligudem", false);
		
		bank.listCustomers("Tadepalligudem", true);
	}
	
}
