package training_java.May26;

public class EvenDigitsSum {
public static int getEvenSum(int number) {
	if(number<0) {
		return -1;
	}
	int sum=0;
	while(number>0) {
		if(number%2==0) {
		sum+=number%10;
		}
		number=number/10;
	}
	return sum;
}
public static void main(String[] args) {
	System.out.println(getEvenSum(123456789));
	System.out.println(getEvenSum(252));
	System.out.println(getEvenSum(-22));
	
}
}
