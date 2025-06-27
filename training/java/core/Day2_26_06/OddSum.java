package Day2_26_06;

public class OddSum {
	public static void main(String args[]) {
		System.out.println("the sum of all odd numbers in range 5 to 10 is "+sumOdd(5,10));
	}
	public static boolean isOdd(int n) {
		if(n%2!=0) {
			return true;
		}return false;
	}
	public static int sumOdd(int low,int high) {
		int sum=0;
		if(low<0 || high <0 || low> high) {
			return -1;
		}
		for(int i=low;i<=high;i++) {
			if(isOdd(i)) {
				sum+=i;
			}
		}
		return sum;
	}
}
