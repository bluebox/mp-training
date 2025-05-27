import java.util.*;
public class DigitSum {
    public static long sumDigits(long no) {
    	long sum=0;
    	while(no>0) {
    		sum+=(no%10);
    		no/=10;
    	}
    	return sum;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long Number=sc.nextLong();
		System.out.print(sumDigits(Number));
	}

}
