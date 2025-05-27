package conditions;

public class DecimalComperator {
	public static boolean areEqualByThreeDecimalPlaces(double a, double b) {
        int aInt = (int) (a * 1000);
        int bInt = (int) (b * 1000);
        return aInt == bInt;
    }
	public static void main(String[] args) {
        
        System.out.println(areEqualByThreeDecimalPlaces(-3.1756, -3.175));   
        System.out.println(areEqualByThreeDecimalPlaces(3.175, 3.176));      
        System.out.println(areEqualByThreeDecimalPlaces(3.0, 3.0));          
        System.out.println(areEqualByThreeDecimalPlaces(-3.123, 3.123));     
    }

}
