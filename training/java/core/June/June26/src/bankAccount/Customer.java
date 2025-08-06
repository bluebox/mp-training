package bankAccount;

public class Customer {
	public static void main(String[] args) {
		BankAccount myAccount = new BankAccount("2345",5000,"manoj","manoj@gmail.com","1234567890");
		
		myAccount.deposite(300);
		myAccount.withdraw(8000);
		myAccount.withdraw(1000);
		myAccount.withdraw(-100);
		myAccount.deposite(-1000);
		
	}
}
