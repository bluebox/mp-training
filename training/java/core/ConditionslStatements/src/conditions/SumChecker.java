package conditions;

public class SumChecker {
	public static boolean hasEqualSum(int num1, int num2, int num3) {
        return (num1 + num2) == num3;
    }
	public static void main(String[] args) {
        System.out.println(hasEqualSum(1, 1, 2));   
        System.out.println(hasEqualSum(1, -1, 0));  
        System.out.println(hasEqualSum(1, 1, 1));   
        System.out.println(hasEqualSum(5, 3, 8));   
        System.out.println(hasEqualSum(10, 10, 21)); 
    }
}
