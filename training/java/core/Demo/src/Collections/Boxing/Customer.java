package Collections.Boxing;
import java.util.*;

public class Customer {
	private String name ;
	private List<Double> transactions;
	
	public Customer(String name, double intialTransaction) {
		this.name=name;
		this.transactions=new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public List<Double> getTransactions(){
		return transactions;
	}
	public void addTransaction(double transaction) {
		transactions.add(transaction);
	}
	

}
