package BankSystem;

public class Main {
	public static void main(String[] args) {
		System.out.println("-----");
		System.out.println("-----");

		Bank bank1=new Bank("SBI");
		bank1.addBranch("nellore");
		bank1.addBranch("kurnool");
		
		bank1.addcustomer("nellore", "ramu", 100.0);
		bank1.addCustomerTransaction("nellore","ramu", 200.0);
		
		bank1.addcustomer("nellore", "bharath", 111.0);
		bank1.addCustomerTransaction("nellore","bharath", 222.0);

		bank1.addcustomer("nellore", "lakshman", 10.0);
		bank1.addCustomerTransaction("nellore","lakshman", 20.0);
		
		bank1.addcustomer("kurnool", "bheem", 11.0);
		bank1.addCustomerTransaction("kurnool","bheem", 22.0);
		System.out.println("+++++++++++++");
		bank1.listCustomers("nellore", true);
		
		bank1.listCustomers("kurnool", true);
		
	}

}
