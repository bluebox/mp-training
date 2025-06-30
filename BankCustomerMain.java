
public class BankCustomerMain {	
	public static void main(String[] args) {
		Bank bank=new Bank("National Ausis Bank");
		
		bank.addBranch("Adeline");
		bank.addCustomer("Adeline","Setty", 1000.00);
		bank.addTransaction("Adeline","Setty", 20.00);
		bank.addTransaction("Adeline","Setty", -100.00);
		
		bank.addCustomer("Adeline","Mike", 100.00);
		bank.addTransaction("Adeline","Mike", 200.00);
		
		bank.addCustomer("Adeline","Percy", 200.00);
		bank.listCustomers("Adeline",false);
	}
}