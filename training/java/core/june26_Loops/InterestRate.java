package june26_Loops;

public class InterestRate {
	public static double calculateInterest(double amount,double interest) {
		return (amount*1*interest/100);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			for(double i=7.5;i<=10;i+=0.25) {
				double interestRate=calculateInterest(100.0,i);
				if(i>8.5)
					break;
				System.out.println("$100.0 at "+i+"Interest rate= $"+interestRate);
			}
	}

}
