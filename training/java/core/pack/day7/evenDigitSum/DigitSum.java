package day7.evenDigitSum;

public class DigitSum {
public static int evenSum(int num) {
	int temp=num,res,sum=0;
	while(temp>0) {
		res=temp%10;
		if(res%2==0)
			sum+=res;
		temp=temp/10;
	}
	return sum;
}
}
