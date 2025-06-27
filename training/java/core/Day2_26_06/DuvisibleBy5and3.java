package Day2_26_06;

public class DuvisibleBy5and3 {
	public static void main(String args[]) {
		int count=0;
		for(int i=0;i<=1000 && count<3;i++) {
			if(checker(i)) {
				System.out.println(i+" is accepted with the count value of "+(count+1));
				count++;
			}
		}
	}
	public static boolean checker(int n) {
		if(n%3==0 && n%5==0) {
			return true;
		}
		return false;
	}
}
