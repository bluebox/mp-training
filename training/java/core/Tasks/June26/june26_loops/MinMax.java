package corejava.june26_loops;

import java.util.Scanner;

public class MinMax {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Integer min=null;
		Integer max=null;
		while(true) {
			System.out.println("Enter a number or enter any charecter to quit");
			try {
				Integer in=Integer.parseInt(sc.next());
				if(min==null || min>in) {
					min=in;
				}
				if(max==null ||max<in) {
					max=in;
				}
			}
			catch(Exception e){
				System.out.println("You are out of loop.");
				if(min!=null && max!=null) {
					System.out.println("Min and Max numbers till you enetered are: min("+min+") , max("+max+")");
				}
				break;
			}
		}
		sc.close();
	}
}
