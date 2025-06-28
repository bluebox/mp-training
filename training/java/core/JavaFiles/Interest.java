
public class Interest {
	public static void main(String[] args) {
		int amount=100;
		
		double interest =0;
		
		for(double i=7.5;i<10;i+=0.25) {
			interest = amount* (i/100);
		}
		
		System.out.println("Total Interest : $"+interest);
	}

}
