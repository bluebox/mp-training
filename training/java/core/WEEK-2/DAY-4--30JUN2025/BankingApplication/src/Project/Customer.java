package Project;
import java.util.ArrayList;

public class Customer {
	private String name;
	private ArrayList<Double> transactions;
	
	public Customer(String name, double initialTransaction) {
		this.name=name;
		this.transactions=new ArrayList<Double>();
		this.transactions.add(initialTransaction);
	}
	
	public Customer() {
		this("",0);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Double> getTransactions() {
		return transactions;
	}
	
	public void addTransaction(double transaction) {
		this.transactions.add(transaction);
	}
	public void printCustomer(boolean printTransaction) {
		System.out.println("Customer - "+this.getName());
		if(printTransaction) {
			System.out.println(this.getTransactions());
		}
	}
}
