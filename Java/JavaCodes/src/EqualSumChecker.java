import java.util.Scanner;
public class EqualSumChecker {
    public static boolean hasEqualSum(int firstValue,int secondValue,int thirdValue) {
    	if(firstValue+secondValue==thirdValue)return true;
    	return false;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int firstValue=sc.nextInt();
		int secondValue=sc.nextInt();
		int thirdValue=sc.nextInt();
		if(hasEqualSum(firstValue,secondValue,thirdValue)) {
			System.out.print("yes the sum is equal");
		}
		else System.out.print("sum is not equal");
	}

}
