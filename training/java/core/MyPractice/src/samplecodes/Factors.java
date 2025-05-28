package samplecodes;

public class Factors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printFactors(32);
	}
	public static void printFactors(int n) {
		for(int i=1;i<=n;i++) {
			if(n%i==0) {
				System.out.print(i+" ");
			}
		}
	}

}
