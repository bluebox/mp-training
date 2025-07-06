package ImmutableClassesExample;

public class BankAccount {
	private final String type;
	private final double balance;
	
	public BankAccount(String type, double balance) {
		super();
		this.type = type;
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public double getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "BankAccount [type=" + type + ", balance=" + balance + "]";
	}
	
	

}
