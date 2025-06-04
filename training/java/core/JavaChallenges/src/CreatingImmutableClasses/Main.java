package CreatingImmutableClasses;


import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<BankAccount> b=new ArrayList<BankAccount>();
		b.add(new BankAccount("a",1.1));
		b.add(new BankAccount("b",2.2));
		BankCustomer b1=new BankCustomer("uday",12346L,b);

		System.out.println(b1.toString());
	 
	}
	
}