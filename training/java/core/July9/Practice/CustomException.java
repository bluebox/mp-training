package July9.Practice;

public class CustomException extends Exception {
	
	private static int accno[] = { 1001, 1002, 1003, 1004, 1005 };
	private static String name[] = { "Nish", "Shubh", "Sush", "Abhi", "Akash" };
	private static double bal[] = { 10000.00, 12000.00, 5600.0, 999.00, 1100.55 };

	public CustomException() {
	}

	public CustomException(String message) {
		super(message);
	}

	public static void main(String[] args) {
		
		try {
			System.out.println("AccNum \t Name \t Balance");
			for(int i = 0; i < accno.length ; i++) {
				System.out.println(accno[i] + "  \t" + name[i] + "  \t" + bal[i]);
				if(bal[i] < 1000.0) throw new CustomException("Balance should be greater than or equal to 1000.0");
			}
		}
		catch(Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
