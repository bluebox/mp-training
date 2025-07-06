package ImmutableClassesExample;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		BankAccount acc1 = new BankAccount("Checking", 1500);
        BankAccount acc2 = new BankAccount("Savings", 5000);

        List<BankAccount> accountList = new ArrayList<>();
        accountList.add(acc1);
        accountList.add(acc2);
        
        BankCustomer customer = new BankCustomer("abc", "12345", accountList);

        System.out.println(customer);
        
        accountList.add(new BankAccount("Investment", 10000));
        System.out.println(accountList);
        System.out.println(customer);
	
	
	try {
        customer.getAccounts().add(new BankAccount("Fraud", 0));
    } catch (UnsupportedOperationException e) {
        System.out.println("Cannot modify customer's accounts: " + e);
    }

	}
}