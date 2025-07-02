package bankManagementSystem;


public class AutoBoxing {

	public static void main(String[] args) {
		
		Bank bank=new Bank("ADB BANK");
		bank.addBranch("hyd");
		bank.addCustomer("hyd", "Prabhas", 0);
		bank.findBranch("hyd");
		bank.addCustomerTransaction("Hyd", "Prabhas", 10);
		System.out.println(bank.listCustomers("hyd", true));
	}

}
