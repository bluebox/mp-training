package samplecodes;

public class GreatestCommonDivisor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getGreatestCommonDivisor(30,12));
	}
	public static int getGreatestCommonDivisor(int a,int b) {
		if(a<10 || b<10) return -1;
		int gcd=0;
		int min=Math.min(a, b);
		for(int i=1;i<=min;i++) {
			if(a%i==0 && b%i==0) {
				gcd=i;
			}
		}
		return gcd;
	}

}
