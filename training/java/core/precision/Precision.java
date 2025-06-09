package precision;

import java.util.Formatter;

public class Precision {
	
	public static void main(String[] args) {
	
//		Declaring and assigning value to a float number
		float f= 12.78342f;
		System.out.format(f+ " formatted to : %.2f \n",f);
		
//		Declaring and assigning value to a double number
		double d= 1928.1827118;
		System.out.println(
				String.format("%.6f", d));
//		Creating object for Formatter to format the numbers
		Formatter fm=new Formatter();
		
//		Format 3 decimal places
		fm.format("%.3f", 13.63746872);
		System.out.println("Formatted value of float using Formatter : "+fm);
		fm.close();
		
//		Format upto 10 characters in a string
		fm=new Formatter();
		fm.format("%.20s","Hello welcome to java programming");
		System.out.println("Formatted String : "+fm);
	}
}
