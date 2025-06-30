
public class DigitSum {

	public static int evenDigitSum(int number) {
		if(number<0) {
			return -1;
		}
		int sum=0;
		
		while(number>0) {
			int digit=number%10;
			if(digit%2==0) {
				sum+=digit;
			}
			number/=10;
		}	
		return sum;
		
	}
}
