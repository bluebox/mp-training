package day_2_june26_basics_of_oops;

public class SumOfOddNumbers {

	public static void main(String[] args) {
		System.out.println(sumOdd(1,100));
		System.out.println(sumOdd(-1,100));
		System.out.println(sumOdd(100,100));
		System.out.println(sumOdd(13,13));
		System.out.println(sumOdd(100,-100));
		System.out.println(sumOdd(100,1000));
	}
	public static int sumOdd(int start,int end) {
		if(start>end||start<0||end<0) return -1;
		int sum=0;
		if (!isOdd(start)) start+=1;
		for(int i=start;i<=end;i+=2) {
			sum+=i;
		}
		return sum;
	}
	public static boolean isOdd(int number) {
		return number%2==1;
	}
}
