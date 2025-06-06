package day11.banking;

import java.util.ArrayList;

public class Customer {
private String customerName;
private ArrayList<Double>transactions;

Customer(String name,double transact){
	customerName=name;
	transactions =new ArrayList<Double>();
	transactions.add(transact);
}
public String getName() {
	return this.customerName;
}
public ArrayList<Double>getTransaction(){
	return transactions;
}
public void addTransaction(double d) {
	transactions.add(d);
}
}
