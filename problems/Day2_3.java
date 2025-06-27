package problems;
import java.util.*;
public class Day2_3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// IsPalindromeNumber
		System.out.println("Enter the number:");
		int number=Integer.parseInt(sc.nextLine());
		System.out.println(isPalindrome(number));
		
		// summing the first and last digits of a number
		System.out.println("Enter the number:");
		int number_1=Integer.parseInt(sc.nextLine());
		System.out.println(sumOfFirstAndLast(number_1));
		
		//sum of all Even Digits
		System.out.println("Enter the number:");
		int number_2=Integer.parseInt(sc.nextLine());
        System.out.println(getEvenDigitSum(number_2));
        
        // shared digit
        System.out.println("Eneter the Number greater than or equal to 10:");
        int number_3=Integer.parseInt(sc.nextLine());
        System.out.println("Eneter the Number less than or equal to 99");
        int number_4=Integer.parseInt(sc.nextLine());
        System.out.println(hasSharedDigit(number_3,number_4));
        
        //multiple Integer comparisi0ns-
    	System.out.println("Enter the number for integer comarision:");
        int number_5=Integer.parseInt(sc.nextLine());
        System.out.println("Enter the number for integer comparison:");
        int number_6=Integer.parseInt(sc.nextLine());
        System.out.println("Enter the number for integer comaprision:");
        int number_7=Integer.parseInt(sc.nextLine());
        System.out.println(hasLastDigit(number_5,number_6,number_7));
        
        
        // all factors of a given number
        System.out.println("Enter the number for all factors of a given number:");
        int Enter=Integer.parseInt(sc.nextLine());
        printFactors(Enter);
        
        //derive GCD
        System.out.println("Enter the first number for GCD:");
        int First=Integer.parseInt(sc.nextLine());
        System.out.println("Enter the second number for GCD:");
        int Second=Integer.parseInt(sc.nextLine());
        System.out.println(GCD(First,Second));
        
        
        //is Perfect number
        System.out.println("Enter the number for checking the perfection of a number:");
        int First_1=Integer.parseInt(sc.nextLine());
        System.out.println(isPerfectNumber(First_1));
        
        //converting Digits into Words
        System.out.println("Eneter the Number for conversion to word");
        int num=Integer.parseInt(sc.nextLine());
        System.out.println(numtoWord(num));
        
	}
	
	public static String numtoWord(int num) {
		if(num<0)return "Invalid  Value";
		int digitcount=0;
		StringBuilder string=new StringBuilder();
		while(num>0) {
			int n=num%10;
			digitcount++;
			switch(n) {
			case 0:
				string.append("Zero");
				break;
			case 1:
				string.append("One");
				break;	
			case 2:
				string.append("Two");
				break;	
			case 3:
				string.append("Three");
				break;
			case 4:
				string.append("Four");
				break;	
			case 5:
				string.append("Five");
				break;	
			case 6:
				string.append("Six");
				break;	
			case 7:
				string.append("Seven");
				break;	
			case 8:
				string.append("Eight");
				break;	
			case 9:
				string.append("Nine");
				break;
		    default:
		    	string.append(" ");
			}
		
		
		}
		System.out.println(digitcount+"there were thesse many digits present.");
		String real=string.reverse().toString();
		return real;
	}
	
	
	public static boolean isPerfectNumber(int num) {
		int sum=0;
		for(int i=1;i<=num;i++) {
			if(num%i==0) {
				sum+=i;
			}
		}
		if(sum==num)return true;
		return false;
	}
	
	public static int GCD(int n,int m) {
		if(m==0)return n;
		if(n==0)return m;
		if(n==m)return n;
		if(n>m) {
			GCD(n-m,m);
		}
		return GCD(n,m-n);
	}
	
	public static void printFactors(int num) {
		if(num<1)System.out.println("Invalid value");
		for(int i=1;i<=num;i++) {
			if(num%i==0) {
				System.out.print(i);
			}
		}
	}
	
	public static boolean hasLastDigit(int n,int m,int b) {
		if(n<10 || n>1000 || m<10 || m>1000 || b<10 || b>1000)return false;
		if(m%10==n%10 || m%10==b%10 || n%10==b%10) {
			return true;
		}
		return false;
	}
	
	public static boolean hasSharedDigit(int n,int m) {
	   	if(n<10 || m<10 || n>99 || m>99) {
	   		return false;
	   	}
	   	
	   	String val1=""+n;
	   	String val2=""+m;
	   	
	   	if(val1.charAt(0)==val2.charAt(1) || val1.charAt(0)==val2.charAt(0) || 
	   			val1.charAt(1)==val2.charAt(0) || val1.charAt(1)==val2.charAt(1)) {
	   		return true;
	   	}	   	
	   return false; 	       
	}
	
	public static int getEvenDigitSum(int num) {
		if(num<0)return -1;
		int sum=0;
		while(num>0) {
			int s=num%10;
			if(s%2==0) {
				sum+=s;
			}
			num=num/10;
		}
		return sum;
	}
	
	
	public static int sumOfFirstAndLast(int number) {
		if(number<0)return -1;
		String val=""+number;
		String num=""+val.charAt(0);
		System.out.println(num);
		String num2=""+val.charAt(val.length()-1);
		System.out.println(num2);
		int sum=Integer.parseInt(num)+Integer.parseInt(num2);
		return sum;
	}
	
	public static boolean isPalindrome(int num) {
		if(num<0) {
			num=0-(num);
		}
		
		String val=""+num;
		
		int i=0;
		int j=val.length()-1;
		while(i<=j){
	       if(val.charAt(i) != val.charAt(j)) {
	    	   return false;
	       }
	       i++;
	       j--;
		}
		return true;
	}

}
