import java.util.*;
public class SumOfEvenDigits {
    public static long sumOfEven(long val) {
    	long sum=0;
    	while(val>0) {
    		long rem=val%10;
    		if(rem%2==0)sum+=rem;
    		val/=10;
    	}
    	return sum;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long val=sc.nextLong();
		System.out.println(sumOfEven(val));
	}

}
