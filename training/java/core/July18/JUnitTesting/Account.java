package maven.challenges;

public class Account {
	String name;
	Integer amount;
	
	public Account(String name, Integer amount ) {
		this.name  = name;
		this.amount  = amount;
	}
	
	public void withdraw(int amount) {
		this.amount-=amount;
	}
	
	public void deposite(int depositeAmount) {
		this.amount += depositeAmount;
	}
}
