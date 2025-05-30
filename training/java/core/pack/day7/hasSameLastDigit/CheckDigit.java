package day7.hasSameLastDigit;

public class CheckDigit {
public static boolean hasSameDigit(int t1,int t2, int t3) {
	int res;
	int r1=t1%10,r2=t2%10,r3=t3%10;
	if(r1==r2 || r1==r3 || r2==r3)
		return true;
	
	return false;
}
public static boolean isValid(int t1) {
	return t1>=10 && t1<=1000;
}
}
