package boxing;

public class AutoBoxingChallenge {
	public static void main(String[] args) {;
		Bank bank = new Bank("KDCC");
		bank.addCustomer("manoj");
		bank.addCustomer("surya");
		
		bank.addtranscation("manoj", 55000.0);
		bank.addtranscation("manoj", -500.0);
		
		bank.printtranscation("manoj");
		
		bank.addtranscation("manoj", 5000.0);
		bank.printtranscation("manoj");
		bank.addCustomer("manoj");

	}

}
