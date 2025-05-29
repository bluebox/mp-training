package BankAuto;

public class MainBank {
	public static void main(String[] args) {		
	
	Bank bank=new Bank("National Australia Bank");
	bank.addBranch("Adelaide");
	bank.addCustomer("Adelaide", "Tim", 50.05);
	bank.addCustomer("Adelaide", "Mike", 175.34);
	bank.addCustomer("Adelaide", "Percy", 220.12);
	
	bank.addBranch("Sydney");
	bank.addCustomer("Sydney","Tim", 50.05);
	bank.addCustomer("Sydney", "Mike", 175.34);
	bank.addCustomer("Sydney", "Percy", 220.12);
	

	
	bank.addCustomerTransaction("Adelaide", "Tim", 44.22);
	bank.addCustomerTransaction("Adelaide", "Tim", 12.44);
	bank.addCustomerTransaction("Adelaide", "Mike", 1.65);
	bank.listCustomers("Adelaide", true);
	bank.listCustomers("Sydney", true);
	
	
	}
}
