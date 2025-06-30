package Day4_30_06;

import java.util.ArrayList;

public class Customer {
	public String name;
	public double amount;
	private ArrayList<Double> trans=new ArrayList<>();

	public Customer(String name, double amount) {
		this.name = name;
		this.amount = amount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+" "+this.amount;
	}
	public void getTrans() {
		System.out.println(this.getName());
		for(Double tr:trans) {
			System.out.println((tr>0?"Credited amount ":"Debited amount ")+Math.abs(tr));
		}
		System.out.println("Final amount: "+this.getAmount());
	}
	public void addTrans(double tr) {
		this.trans.add(tr);
		this.amount+=tr;
	}
	public static void main(String args[]) {
		Customer c1=new Customer("saketh",500);
		c1.addTrans(-400);
		c1.addTrans(800);
		c1.addTrans(600);
		c1.getTrans();
		
		Customer c2=new Customer("sai",500);
		c2.addTrans(-400);
		c2.addTrans(800);
		c2.addTrans(600);
		c2.getTrans();
		
		Bank b1=new Bank("HDFC");
		b1.addCusms(c1);
		b1.addCusms(c2);
		System.out.println(b1.getCusms());
	}
	
}
