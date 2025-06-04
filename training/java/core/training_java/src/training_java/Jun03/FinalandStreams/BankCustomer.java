package FinalandStreams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BankCustomer {

	private final String name;
	private final long custId;
	private final List<BankAccount> accounts;
	public BankCustomer(String name, long custId, List<BankAccount> accounts) {
		this.name = name;
		this.custId = custId;
		this.accounts = Collections.unmodifiableList(accounts);
	}
//	{
//		accounts.add(new BankAccount("checkings",45678));
//	}

	public String getName() {
		return name;
	}

	public long getId() {
		return custId;
	}

	public List<BankAccount> getAccounts() {
//		accounts.add(new BankAccount("checkings",45678));
		return accounts;

	}
	public String toString() {
		return name+" "+custId+" "+accounts.toString();
	}
	

}
