package banking;

public class Main {
		public static void main(String[] args) {
			System.out.println("Welcome to the Banking application");
			Bank bank =new Bank("National Australia Bank");
			if(bank.addBranch("Adelaide")) {
				System.out.println("Adelaide branch created");
			}
			
			
			bank.addCustomer("Adelaide", "Tim", 50.05);
			bank.addCustomer("Adelaide", "Mike", 175.34);
			bank.addCustomer("Adelaide", "Percy", 220.12);
			
			bank.addCustomerTransaction("Adelaide", "Tim", 44.22);
			bank.addCustomerTransaction("Adelaide", "Tim", 12.44);
			bank.addCustomerTransaction("Adelaide", "Mike", 1.65);
			
			bank.listCustomers("Adelaide", true);
		}
	

}
