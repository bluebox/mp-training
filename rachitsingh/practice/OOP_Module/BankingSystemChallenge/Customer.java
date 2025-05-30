package BankingSystemChallenge;
import java.util.ArrayList;
public class Customer {
	private String name;
	private ArrayList<Double> transactions = new ArrayList<Double> ();
	
	public Customer(String name, double initialTransaction)
	{
		this.name = name;
		this.transactions = new ArrayList<Double>();
		
		addTransaction(initialTransaction);
	}
	public void addTransaction(Double transaction)
	{
		transactions.add(transaction);
	}
	public String getName()
	{
		return name;
	}
	public ArrayList<Double> getTransactions()
	{
		return this.transactions;
	}
	
}
