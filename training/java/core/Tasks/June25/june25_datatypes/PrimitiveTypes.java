package corejava.june25_datatypes;

import java.util.Scanner;

public class PrimitiveTypes {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try {
			System.out.println("Enter a byte variable in range of -2**7 to (2**7)-1");
			byte byteVariable = sc.nextByte();
			System.out.println("Enter a short variable in range of -2**15 to (2**15)-1");
			short shortVariable = sc.nextShort();
			System.out.println("Enter a int variable in range of -2**31 to (2**31)-1");
			int intVariable = sc.nextInt();
			long longVariable = 5000 + 10 * (byteVariable + shortVariable + intVariable);
			System.out.print(longVariable);
		}
		catch(Exception e) {
			System.out.println("Warning: Please enter a valid input!!!");
		}
		finally {
			sc.close();
		}
	}
}
