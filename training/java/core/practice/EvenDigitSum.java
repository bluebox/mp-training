
public class EvenDigitSum {

	public static void main(String[] args) {
		//getting Even digits' sum using a method
				int number=5545985;
				int result=getEvenDigitSum(number);
				if(result==-1) {
					System.out.println("The given number is negative");
				}else {
				System.out.println("The sum of even digits of number "+number+" is "+result);
				}

	}
	public static int getEvenDigitSum(int number) {
		int sum=0;
		if(number<0) {
			return -1;
		}
		while(number>0) {
			int temp=number%10;
			number=number/10;
			if(temp%2==0) {
				sum+=temp;
			}
		}
		return sum;
	}

}
