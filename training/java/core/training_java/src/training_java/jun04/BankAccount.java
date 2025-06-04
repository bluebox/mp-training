package FinalandStreams;

import java.util.HashMap;
import java.util.Map;
enum BankAccountType{SAVINGS,CHECKINGS};
public class BankAccount {

private final BankAccountType type;
private double balance;
private Map<Long,Transaction>transactions =new HashMap<>();

public BankAccount(String type,double balance) {
	 this.type=BankAccountType.valueOf(type.toUpperCase());
	 this.balance=balance;
 }

 public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		if(this.balance+balance<0) {
			System.out.println("Insufficient Balance to withdraw");
		}
		else {
			this.balance+=balance;
		}
	}

	public String getType() {
		return String.valueOf(type);
	}
	public Map<Long, Transaction> getTransactions() {
		return transactions;
	}
	public void commitTransaction(int routingNumber,long transactionId,String customerId,double amount) {
		Transaction t=new Transaction(routingNumber,Integer.valueOf(customerId),transactionId,amount);
		transactions.put(transactionId,t);
	}
	
 public String toString() {
	 return "Type: "+type+" balance: "+balance+" Transactions "+ transactions ;
 }
 
}
