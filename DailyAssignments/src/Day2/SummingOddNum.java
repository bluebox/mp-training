package Day2;

public class SummingOddNum {

	public static void main(String[] args) {
	System.out.println(sumOdd(20,30));
		

	}
	public static boolean isOdd(int number) {
		if(number<=0) {
			return false;
		}
		return number%2!=0; 
		
	}
	public static int sumOdd(int start,int end) {
		if(end<start || start<=0|| end<=0) {
			return -1;
		}
		int sum=0;
		for(int i=start;i<=end;i++) {
			if(isOdd(i)) {
			sum=sum+i;
			}
		}
		return sum;
		
	}
	
	

}
