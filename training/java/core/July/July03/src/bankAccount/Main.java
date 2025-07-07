package bankAccount;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		
		BankAccount account = new BankAccount(Type.CHECKING,1234);
		
		System.out.println(account);
		
		Type accountType = account.getType();
		double accountBalance = account.getBalance();
		
		accountType = Type.CURRENT;
		accountBalance = 345;
		
		System.out.println("Type : "+accountType);
		System.out.println("Balance :"+ accountBalance);
		System.out.println(account);
		
		System.out.println("_".repeat(100));
		BankCustomer customer = new BankCustomer("ram",1,account);
		System.out.println(customer);
		
		List<BankAccount> customerAccount = customer.getAccounts();
		
		customerAccount = null;
		
		System.out.println(customerAccount);
		System.out.println(customer);
	}
}
