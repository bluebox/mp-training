package Day2;

public class Main {

	public static void main(String[] args) {
		Account a1=new Account(34256, 1000.00, "charan", "charan@132", "8978970342");
		a1.depositFunds(501.00);
		a1.withdrawAmount(500.00);
		Account a2=new Account(3452, 2000.00, "kiran", "kiran@123", "789654320");
		a2.depositFunds(2000.00);
		a2.withdrawAmount(1000.00);
	}

}
