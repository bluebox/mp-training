package Day2_26_06;

public class Palindrome {
	public static void main(String args[]) {
		System.out.println(isPalin(121));
		System.out.println(isPalin(-121));
		System.out.println(isPalin(12585));
		System.out.println(isPalin(121549864));

	}
	public static boolean isPalin(int num) {
		int rev=0;
		num=num>=0?num:-num;
		int temp=num;
		while(temp>0) {
			int rem=temp%10;
			rev=rev*10+rem;
			temp/=10;
		}
		return rev==num;
	}
}
