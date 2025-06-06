package Jun5;

public class BankAccount {
	
	final String type;
	final double balance;
	
	public BankAccount(String type,double balance) {
		this.balance=balance;
		this.type=type;
	}
	public String toString() {
		return "Type: "+type+"\nBalance: "+balance;
	}
}
