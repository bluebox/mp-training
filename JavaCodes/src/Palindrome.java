import java.util.*;
public class Palindrome {
    public static boolean isPalindrome(long val) {
    	if(val<0)return false;
    	long reverseVal=0,dup=val;
    	while(dup>0) {
    		long rem=dup%10;
    		reverseVal=(reverseVal*10)+rem;
    		dup/=10;
    	}
    	return reverseVal==val;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long val=sc.nextLong();
		if(isPalindrome(val)) {
			System.out.print("yes it is a palindrome");
		}
		else System.out.print("not a palindrom");
	}
}
