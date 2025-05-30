package day7.gcd;

public class GcdOfNumbers {
public static int findGcd(int a,int b) {
	if(b==0)
		return a;
	return findGcd(b,a%b);
}
}
