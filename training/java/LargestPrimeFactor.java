import java.util.*;
public class LargestPrimeFactor {
	
	public static boolean prime(int num) {
		if(num==0 || num==1) return true;
		for(int i=2;i<=num/2;i++) {
			if(num%i==0) return false;
		}
		return true;
	}
	
	public static int getLargestPrime(int num) {
		if(num<0) return -1;
		int maxPrime=1;
		for(int i=1;i<=num;i++) {
			if(num%i==0) {
				if(prime(i) && i>maxPrime) {
					maxPrime=i;
				}
			}
		}
		return maxPrime;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner (System.in);
		int num=sc.nextInt();
		int result=getLargestPrime(num);
		System.out.print(result);

	}

}
