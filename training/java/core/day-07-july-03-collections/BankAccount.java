package day7;

public final class BankAccount {
	private final Type type;
	private int balance;

	public BankAccount(Type type) {
		this(type, 100);
	}

	public BankAccount(Type type, int balance) {
		this.type = type;
		this.balance = balance;
	}

	public Type getType() {
		return type;
	}

	public int getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "[type=" + type + ", balance=" + balance + "]";
	}
	
	
}

enum Type {
	CHECKING, SAVINGS
}