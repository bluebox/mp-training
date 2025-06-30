package corejava.june26_methods;

import java.util.Scanner;

public class DigitToWord {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number to get word representation of it");
		int number=sc.nextInt();
		String str=numberToWord(reverse(number));
		if(getDigitCount(number)==Integer.toString(reverse(number)).length()) {
			System.out.println("Entered number is: "+str);
		}
		else {
			int zeros=getDigitCount(number)-Integer.toString(reverse(number)).length();
			System.out.println("Entered number is: "+str+"ZERO ".repeat(zeros));
		}
		sc.close();
	}

	public static String numberToWord(int number) {
		if(number>=0) {
			int rem=0;
			String str="";
			while(number>0) {
				rem=number%10;
				number/=10;
				switch(rem) {
				case 0:str+="ZERO ";
				break;
				case 1:str+="ONE ";
				break;
				case 2:str+="TWO ";
				break;
				case 3:str+="THREE ";
				break;
				case 4:str+="FOUR ";
				break;
				case 5:str+="FIVE ";
				break;
				case 6:str+="SIX ";
				break;
				case 7:str+="SEVEN ";
				break;
				case 8:str+="EIGHT ";
				break;
				default:str+="NINE ";
				}
			}
			return str;
		}
		return "Invalid Statement";
	}
	public static int reverse(int number) {
		int rev=0;
		int rem=0;
		while(number>0) {
			rem=number%10;
			rev=rev*10+rem;
			number/=10;
		}
		return rev;
	}
	public static int getDigitCount(int number) {
		String s=Integer.toString(number);
		return s.length();
	}
}
