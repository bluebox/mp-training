import java.util.*;
public class DecimalComparator {
    public static boolean areEqualThreeDecimalPlaces(double firstDouble,double secondDouble) {
    	String firstD=Double.toString(firstDouble);
    	String secondD=Double.toString(secondDouble);
    	if(firstD.length()>=3 && secondD.length()>=3)return true;
    	return false;
    }
	public static void main(String[] args) {
		 Scanner sc=new Scanner(System.in);
		 double firstDouble=sc.nextDouble();
		 double secondDouble=sc.nextDouble();
		 System.out.print(areEqualThreeDecimalPlaces(firstDouble,secondDouble));
	}

}
