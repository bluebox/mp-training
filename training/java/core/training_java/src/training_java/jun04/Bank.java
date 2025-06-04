package FinalandStreams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
private int routingNumber;
private long lastTransactionId;
private Map<String,BankCustomer> customers=new HashMap<>();
//private static long custId=1;
public Bank(int rounum,long lasTransId) {
	this.routingNumber=rounum;
	this.lastTransactionId=lasTransId;
}
public BankCustomer getCustomer(String id) {
	System.out.println(customers.keySet());
	for(String customerId:customers.keySet()) {
		if(customerId.trim().equals(id)) {
			return customers.get(id);
		}
	}
	return null;
}
public void addCustomer(String name,double checkingDepo,double savingDepo) {
	BankAccount check=new BankAccount("checkings",checkingDepo);
	BankAccount save=new BankAccount("savings",savingDepo);
	long custId=(long)customers.size()+1;
	BankCustomer c1=new BankCustomer(name,custId,List.of(check,save));
	customers.put(String.valueOf(custId),c1);
	System.out.println(c1);
}
public void doTransaction(String custId,BankAccountType type,double amount) {
	BankCustomer customer=this.getCustomer(custId);
	if(customer!=null) {
		BankAccount acc=customer.getAccount(type);
		acc.setBalance(amount);
		acc.commitTransaction(routingNumber, lastTransactionId, custId, amount);
		lastTransactionId+=1;
		System.out.println("Remaining Balance : "+acc.getBalance());
	}
}
}
