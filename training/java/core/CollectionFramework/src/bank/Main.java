package bank;

public class Main {

	public static void main(String[] args) {
		
		
		Bank b1 = new Bank("SBI","Amberpet");
		b1.addCustomer("Madhav","Amberpet");
		b1.addCustomer("Ram","Amberpet");
		b1.makeTransaction("Madhav","Amberpet" ,2000.0);
		b1.makeTransaction("Ram","Amberpet" ,3000.0);
		b1.getCustomerTrans("Madhav","Amberpet");
		b1.getCustomerTrans("Ram","Amberpet");
		

	}

}
