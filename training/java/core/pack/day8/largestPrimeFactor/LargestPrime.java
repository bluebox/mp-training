package day8.largestPrimeFactor;

public class LargestPrime {
public static int getLargestPrime(int num) {
	if(num<0)
		return -1;
	int res=-1;
	for(int i=2;i<=num/2;i++) {
		if(num%i==0 && isPrime(i))
			res=Math.max(res, i);
	}
	if(res==-1 && isPrime(num))
		return num;
	return res;
}

public static boolean isPrime(int num) {
	if(num==1)
		return false;
	
	if(num==2 || num==3)
		return true;
	
	if(num%2==0 || num%3==0)
		return false;
	
	for(int i=5;i<=num/2;i+=6) {
		if(num%i==0)
			return false;
		if(num%(i+2)==0)
			return false;
	}
	return true;
}
}
