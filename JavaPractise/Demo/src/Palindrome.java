import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number:");
       String num=sc.nextLine();
       String rev= "";
       for(int i=num.length()-1;i>=0;i--) {
    	   rev+=num.charAt(i);
       }
       if(rev.equals(num)) {
    	   System.out.println("Palindrome");
       }
       else {
    	   System.out.println("Not  a palindrome");
       }
	}

}
