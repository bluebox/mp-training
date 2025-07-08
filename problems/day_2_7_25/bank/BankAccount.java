package day_2_7_25.bank;

public class BankAccount {
	public enum AccountType{
		Savings,
		Checking;
	}
	
	  private final double balance;
	  private final AccountType AccountType;
	  
	  public double getBalance() {
		  return balance;
	  }
	
	  public BankAccount(double balance,AccountType accountType) {
		  this.balance=balance>0?balance:500;
		  this.AccountType=accountType;
	  }
	
	   public String getsavings() {
		   return AccountType.name();
	   }
	   
	   public String getChecking() {
		   return AccountType.name();
	   }
	   
	   public AccountType getAccountType() {
		   return AccountType;
	   }

  
}
