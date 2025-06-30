package bankingChallenge;

public class Main {

	public static void main(String[] args) {
		Bank bank = new Bank("bob");
		
		bank.addNewCustomer("sam", 4000);
		bank.addTransaction("sam",200);
		bank.addTransaction("sam",-200);
		bank.addTransaction("ram",2000);
		bank.printTransactions("sam");
	}

}
