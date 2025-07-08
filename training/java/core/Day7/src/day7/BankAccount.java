package day7;

public class BankAccount {
	
	public enum type{ CURRENT,SAVINGS };
	private final String name;
	private final type accountType;
	private final double balance;
	
	 BankAccount(String name,type accountType, double balance) {
		 this.name=name;
		this.accountType=accountType;
		this.balance=balance;
	}
	 
	public String getName() {
		return name;
	}

	public type getAccountType() {
		return accountType;
	}

	public double getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Bank Name : "+name+"Account type : "+accountType+" Balance : "+balance+"\n";
	}
	
	
}
