
public class Challenge1 {

	public static void main(String[] args) {
		double a=20.00;
		double b=80.00;
		double sum=(a+b)*100.00;
		System.out.println(sum);
		double remainder=sum%39.00;
		boolean isRemainderZero = (remainder==0.00);
		if(isRemainderZero==false) {
			System.out.println("got some remainder");
			System.out.println(remainder);
		}
	}
}


