import java.util.Scanner;

public class Number_To_Word {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the number:");
		int n=sc.nextInt();
		if(n<0) {
			System.out.println("Invalid");
		}
		else {
			int rev=0;
			String temp="";
			while(n!=0) {
				rev=rev*10 + n%10;
				n=n/10;
			}
			String ans="";
			if(rev<10) System.out.println(word_Representaion(rev));
			else{
				while(rev!=0) {
				ans+=word_Representaion(rev%10);
				ans+=" ";
				rev=rev/10;
				}
				System.out.println(ans);
			}
			
		}

	}
	public static String word_Representaion(int num) {
		if(num==0) return "Zero";
		else if(num==1) return "One";
		else if(num==2) return "Two";
		else if(num==3) return "Three";
		else if(num==4) return "Four";
		else if(num==5) return "Five";
		else if(num==6) return "Six";
		else if(num==7) return "Seven";
		else if(num==8) return "Eight";
		else if(num==9) return "Nine";
		return "Other";
	}

}
