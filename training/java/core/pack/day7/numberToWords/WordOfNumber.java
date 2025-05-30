package day7.numberToWords;
import day6.numberInWord.NumberWord;

public class WordOfNumber {
public static void numberToWord(int num) {
	int reverseNumber=reverse(num);
	int digits=getDigitCount(num);
	
	int temp=reverseNumber,count=0;
	String s="";

	while(temp>0) {
		int res=temp%10;
		NumberWord.printNumberInWord(res);
		temp=temp/10;
		count++;
	}
	while(count!=digits) {
		System.out.print("zero ");
		count++;
	
	}
	
}
public static int reverse(int num) {
	int res=0,temp=num;
	while(temp>0) {
		int rem=temp%10;
		res=res*10+rem;
		temp=temp/10;
	}
	return res;
}
public static int getDigitCount(int num) {
	int temp=num,count=0;
	while(temp>0) {
		count++;
		temp=temp/10;
	}
	return count;
}
}
