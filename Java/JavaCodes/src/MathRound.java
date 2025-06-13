import java.util.*;
public class MathRound {
	 public static double mathRound(double firstNumber,double secondNumber) {
		 double avg=(firstNumber+secondNumber)/2;
		 long avgRoundedValue=Math.round(avg);
		 return avgRoundedValue;
	 }
     public static void main(String[] args) {
    	 Scanner sc=new Scanner(System.in);
    	 double firstNumber=sc.nextDouble();
    	 double secondNumber=sc.nextDouble();
    	 System.out.println("Rounded Value is:"+mathRound(firstNumber,secondNumber));
     }
}
