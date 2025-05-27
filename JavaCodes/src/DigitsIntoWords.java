import java.util.*;
public class DigitsIntoWords {
    public static int findReverse(int num) {
    	int revNum=0;
    	while(num>0) {
    		int rem=num%10;
    		revNum=(revNum*10)+rem;
    		num/=10;
    	}
    	return revNum;
    }
    public static String WordsOfDigit(int val) {
    	switch(val) {
    	case 1:
    		return "one";
    	case 2:
    		return "two";
    	case 3:
    		return "three";
    	case 4:
    		return "four";
    	case 5:
    		return "five";
    	case 6:
    		return "six";
    	case 7:
    		return "seven";
    	case 8:
    		return "eight";
    	case 9:
    		return "nine";
    	default:
    		return "invalid";
    	
    	}
    }
    public static void DigitToWords(int num,int digits) {
    	int newDigitSize=digits-getDigitCount(num);
    	while(num>0) {
    		int rem=num%10;
    		System.out.print(WordsOfDigit(rem)+" ");
    		num/=10;
    	}
    	 while(newDigitSize>0) {
         	System.out.print("Zero"+" ");
         	newDigitSize-=1;
         }
    }
    public static int getDigitCount(int num) {
    	int cnt=0;
    	while(num>0) {
    		cnt++;
    		num/=10;
    	}
    	return cnt;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		if(num<0) {
			System.out.print("invalid num");
			return ;
		}
		int digit=getDigitCount(num);
		int revNum=findReverse(num);
		DigitToWords(revNum,digit);
  }

}
