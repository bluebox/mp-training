package day7.isPerfect;

public class CheckPerfect {
	public static boolean findPerfect(int t1) {
		int sum=0;
		if(t1<1) {
			System.out.println("invalid value");
			return false;
		}
		for(int i=1;i<=t1/2;i++) {
			if(t1%i==0)
				sum+=i;
		}
		return sum==t1;
	}
}
