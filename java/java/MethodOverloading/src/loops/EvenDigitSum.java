package loops;

public class EvenDigitSum {
	public static void main(String [] args) {
		int number = 1231;
		System.out.println(number + " number even digit sum :" + getEvenDigitSum(number));
		
	}
public static int getEvenDigitSum(int number) {
	if(number < 0) {
		return -1;
	}
	int temp = number;
	int remainder;
	int result = 0;
	while(temp>0) {
		remainder = temp %10;
		if(remainder %2 ==0)
			result+=remainder;
		temp = temp/10;
	}
	
	return result;
	
}

}
