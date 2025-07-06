package ImmutableClassesExample;

import java.util.List;

public class BankCustomer {
	
	private final String name;
	private final String CustomerID;
	private final List<BankAccount> accounts;
	
	
	public BankCustomer(String name, String customerID, List<BankAccount> accounts) {
		this.name = name;
		CustomerID = customerID;
		this.accounts = List.copyOf(accounts);
	}


	public String getName() {
		return name;
	}


	public String getCustomerID() {
		return CustomerID;
	}
	
	


	public List<BankAccount> getAccounts() {
		return accounts;
	}


	@Override
	public String toString() {
		return "BankCustomer [name=" + name + ", CustomerID=" + CustomerID + ", accounts=" + accounts + "]";
	}
	
	
	
	

}
