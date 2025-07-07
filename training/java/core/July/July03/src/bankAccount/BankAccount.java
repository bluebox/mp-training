package bankAccount;

public class BankAccount {
	
	final private Type type;
	final private double balance;
	
	public BankAccount(Type type, double balance) {
		this.type = type;
		this.balance = balance;
	}

	public Type getType() {
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
