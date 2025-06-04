package CreatingImmutableClasses;


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


	public String getName() 
	{
		return name;
	}

	public long getId() {
		return custId;
	}

	public List<BankAccount> getAccounts() {

		return accounts;

	}
	public String toString() {
		return name+" "+custId+" "+accounts.toString();
	}
	

}