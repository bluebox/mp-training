package corejava.june26_conditionalstatements;

import java.util.Scanner;

public class NatoWord {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a word that has letters(a,b,c,d,e)");
		String s=sc.nextLine();
		s.toLowerCase();
		for(int i=0;i<s.length();i++) {
			switch(s.charAt(i)) {
			case 'a':  System.out.print("Able "); 
					  break;
			case 'b':  System.out.print("Basker ");
					  break;
			case 'c': System.out.print("Charlie ");
					  break;
			case 'd':  System.out.print("Dog ");
					  break;
			case 'e':  System.out.print("Easy ");
					  break;
			default:{
				System.out.println("Letter not found!!!");
			}
					
			}
		}
		sc.close();
	}
}
