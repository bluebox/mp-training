
public class SumOfAllEvenNumInInt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          System.out.println("Sum of Even digits in number is "+getEvenDigitSum(10));
          System.out.println("Sum of Even digits in number is "+getEvenDigitSum(-1));
          System.out.println("Sum of Even digits in number is "+getEvenDigitSum(98765));
          System.out.println("Sum of Even digits in number is "+getEvenDigitSum(252));
          System.out.println("Sum of Even digits in number is "+getEvenDigitSum(-42));
	}

	public static int getEvenDigitSum(int n) {
		int r=0,s=0;
		if(n<0) {
			return -1;
		}
		else {
			while(n!=0) {
			r=n%10;
			if(r%2==0)
			{
				s=s+r;
			}
			n=n/10;
			}
			return s;
		}
	}
}
