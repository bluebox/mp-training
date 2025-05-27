import java.util.*;

public class ConvertingDigitsIntoWords {
	
	static int reverse(int num) {
		int result=0;
		while(num>0) {
			int r=num%10;
			result=result*10 +r;
			num/=10;
		}
		return result;
	}

	static String numberToWords(int num) {
		if(num < 0) return "Invalid Value";
		int reversedNum=reverse(num);
		String result="";
		while(reversedNum>0) {
			int a=reversedNum%10;
			switch(a) {
			case 0 -> result+="Zero ";
			case 1 -> result+="One ";
			case 2 -> result+="Two ";
			case 3 -> result+="Three ";
			case 4 -> result+="Four ";
			case 5 -> result+="Five ";
			case 6 -> result+="Six ";
			case 7 -> result+="Seven ";
			case 8 -> result+="Eight ";
			case 9 -> result+="Nine ";
			}
			reversedNum/=10;
		}
		return result;		
	}
	public static void main(String args[]) {
		Scanner sci = new Scanner(System.in);
		int number=sci.nextInt();
		String res = numberToWords(number);
		System.out.println(res);
	}

}
