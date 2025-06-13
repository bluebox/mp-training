import java.util.*;
public class LargestPrimeFactor {
	public static boolean isPrime(int number) {
		for(int i=2;i<number;i++)if(number%i==0)return false;
		return true;
	}
    public static int getLargestPrime(int number) {
    	if(number<=0)return -1;
    	for(int i=number;i>1;i--) {
    		if(number%i==0) {
    			if(isPrime(i))return i;
    		}
    	}
    	return -1000;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int number=sc.nextInt();
		System.out.println(getLargestPrime(number));
	}

}
