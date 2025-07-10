package July10.Practice;

public class BankMain{
	
	public static void main(String[] args) {
		
		BankAccount acc = new BankAccount("Sahithi", 10000);
		
		Thread t1 = new Thread(() ->{
		try {
			Thread.sleep(500);
			acc.withdraw(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		});
		Thread t2 = new Thread(() ->{
			try {
				Thread.sleep(500);
				acc.deposit(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			});
		Thread t3 = new Thread(() ->{
			try {
				Thread.sleep(500);
				acc.withdraw(2500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			});
		t1.start();
		t2.start();
		t3.start();
	}

}
