package problems;
import java.util.*;

public class day2_5_AccountClass {
       
	private String number;
	private String balance;
	private String name;
	private String email;
	private String mobile;

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	// method to withdraw
	   public String withdraw(long amount) {
		if(amount>Long.parseLong(this.balance)) {
			return "insufficient Balance";
		}
		Long store=Long.parseLong(this.balance)-amount;
		this.balance=store.toString();
		return "The balance amount after transaction is "+(Long.parseLong(this.balance)-amount);
	}
	
	// method to deposit
      public String deposit(long amount) {
		if(amount<0) {
			return "Eneter the valid amount";
		}
		Long store=Long.parseLong(this.balance)+amount;
		this.balance=store.toString();
		return "The balance amount after transaction is "+(Long.parseLong(this.balance)+amount);
	}
	

}
