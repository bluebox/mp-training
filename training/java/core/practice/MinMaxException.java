import java.util.*;
public class MinMaxException {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int minimum=Integer.MAX_VALUE,maximum=Integer.MIN_VALUE;
		while(true) {
			try {
				int number=sc.nextInt();
				minimum=Math.min(minimum, number);
				maximum=Math.max(maximum, number);
				System.out.println(minimum+" "+maximum);
			}
			catch(Exception e) {
				System.out.print("Exiting from the loop");
				break;
			}
		}
		sc.close();
		
	}

}