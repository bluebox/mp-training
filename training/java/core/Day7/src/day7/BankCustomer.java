package day7;

import java.util.Collections;
import java.util.List;

public class BankCustomer {
	private final String name;
	private final int customerId;
	private  int lastId=10000;
	private final List<BankAccount> accounts;
	
	BankCustomer(String name,List<BankAccount> accounts){
		this.name=name;
		this.customerId=lastId++;
		this.accounts=List.copyOf(accounts);
	}

	public final String getName() {
		return name;
	}

	public final int getCustomerId() {
		return customerId;
	}

	public final int getLastId() {
		return lastId;
	}

	public final List<BankAccount> getAccounts() {
		return Collections.unmodifiableList(accounts);
	}

	@Override
	public final String toString() {
		// TODO Auto-generated method stub
		return "Name : "+name+" id :"+customerId+" accounts: "+accounts;
	}
	
	
	
}
