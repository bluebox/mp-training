package day7;

public class BankMain {

	public static void main(String[] args) {
		BankAccount acc1 = new BankAccount(Type.SAVINGS, 300);
		BankAccount acc2 = new BankAccount(Type.CHECKING);
		
		BankCustomer kaushik = new BankCustomer("Kaushik", acc1, acc2);
		kaushik.printDetails(); 
	}

}
