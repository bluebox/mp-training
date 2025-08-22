package ImmutableBank;

public final class BankAcoount {
	
	
	private AccountType type;
	private double balance;
	
	

	public BankAcoount(AccountType type, double balance) {
		
		this.type = type;
		this.balance = balance;
	}

	public double getBalance( int initialAmount) {
		
		return balance=(initialAmount<0)?0:balance;
	}



	public AccountType getType() {
		return type;
	}



	@Override
	public String toString() {
		return "BankAcoount [type=" + type + ", balance=" + balance + "]";
	}
	
	
	
	
}
