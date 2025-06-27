
public class Example5 {

	public static void main(String[] args) {
		double var1 = 22.00;
		double var2 = 821.00;
		double ans = (var1+var2)*100.00;
		double remainder = ans%40.00;
		boolean b;
		if(remainder == 0)
			
			System.out.println("true");
		else
		{
			System.out.println("false");
			System.out.println("Got some remainder "+ remainder);
		}
		
		
		
		

	}

}
