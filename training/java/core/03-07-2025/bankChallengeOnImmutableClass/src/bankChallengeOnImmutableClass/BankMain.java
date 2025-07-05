package bankChallengeOnImmutableClass;

import java.util.ArrayList;
import java.util.List;

public class BankMain {
public static void main(String []args)
{
	BankAccount savings=new BankAccount("Savings",500);
	System.out.println("Savings Account: "+savings);
	//savings.balance=6000.000;
	List<BankAccount> customer1Account=new ArrayList<>();
	
	customer1Account.add(new BankAccount("checking",2500.00));
	customer1Account.add(savings);
	BankCustomer customer1=new BankCustomer("gopi","000001",customer1Account);
	System.out.println("Customer1: "+customer1);
//	customer1Account.add(new BankAccount("new investment",2500.00));
//	BankCustomer customer2=new BankCustomer("raju","000011",customer1Account);
//	System.out.println("Customer1: "+customer2);
	try {
        List<BankAccount> gopisAccounts = customer1.getAccounts();
        gopisAccounts.add(new BankAccount("Loan", 10000.00));
    } catch (UnsupportedOperationException e) {
        System.out.println("Attempted to modify customer1's account list: " + e.getMessage());
    }
    System.out.println("Customer 1 (after attempted modification): " + customer1);
}
}
