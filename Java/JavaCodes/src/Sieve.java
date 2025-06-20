import java.util.Arrays;
import java.util.Scanner;
// to print the prime numbers <= n;
public class Sieve {
    public static void sieve(boolean prime[],int n) {
    	if(n<=1)return ;
    	prime[0]=prime[1]=false;
    	for(int i=2;i*i<n*n;i++) {	
    		if(prime[i]==true) {
    			System.out.print(i+" ");
    			for(int j=i*2;j<n;j+=i) {	
    				prime[j]=false;
    			}
    		}
    	}
    }
    public static int gcd(int a,int b) {
    	if(b==0)return a;
    	return gcd(b,a%b);
   }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		boolean prime[]=new boolean [n];
		Arrays.fill(prime, true);
		sieve(prime,n);
		System.out.println(gcd(60,30));
		sc.close();
	}

}
