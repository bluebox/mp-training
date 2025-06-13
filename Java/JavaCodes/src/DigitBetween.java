import java.util.*;
// I can optimize this but the question said to use while loop to implement it 
public class DigitBetween {
    public static boolean hasShareDigits(int firstNum,int secondNum) {
    	if(firstNum<=10 || firstNum>=99 || secondNum<=10 || secondNum>=99)return false;
    	while(firstNum>0) {
    		int rem=firstNum%10;
    		int secDup=secondNum;
    	    while(secDup>0) {
    	    	int remainder=secDup%10;
    	    	if(remainder==rem)return true;
    	    	secDup/=10;
    	    }
    	    firstNum/=10;
    	}
    	return false;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int firstNum=sc.nextInt();
		int secondNum=sc.nextInt();
		System.out.print(hasShareDigits(firstNum,secondNum));
	}

}
