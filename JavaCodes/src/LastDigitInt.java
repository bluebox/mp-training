import java.util.*;
public class LastDigitInt {
    public static boolean hasSameLastDigit(int firstNum,int secondNum,int thirdNum) {
    	if(firstNum<=10 || firstNum>=1000 || secondNum<=10 || thirdNum<=10 || secondNum>=1000 || thirdNum>=1000)return false;
    	int firstLast=firstNum%10;
    	int secondLast=secondNum%10;
    	int thirdLast=thirdNum%10;
    	if((firstLast==secondLast)|| (secondLast==thirdLast)|| (firstLast==thirdLast))return true;
    	return false;
    }
	public static void main(String[] args) {
		 Scanner sc=new Scanner(System.in);
		 int firstNum=sc.nextInt();
		 int secondNum=sc.nextInt();
		 int thirdNum=sc.nextInt();
		 System.out.println(hasSameLastDigit(firstNum,secondNum,thirdNum));
	}

}
