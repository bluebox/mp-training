package Day2_26_06;

public class HasSameLastDigit {
	public static void main(String args[]) {
		System.out.println(sameLastDigit(156,269,236));
		System.out.println(sameLastDigit(156,266,236));
		System.out.println(sameLastDigit(156,-269,236));
		System.out.println(sameLastDigit(157,269,236));

	}
	public static boolean sameLastDigit(int n1,int n2,int n3) {
		if(n1<10 || n2 <10 || n3<10 || n1>10000 || n2>10000 || n3>10000) {
			return false;
		}
		int rem1=n1%10;
		int rem2=n2%10;
		int rem3=n3%10;
		if(rem1==rem2|| rem2==rem3 || rem1==rem3) {
			return true;
		}
		return false;
	}
}
