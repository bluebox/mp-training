import java.util.Scanner;

public class EqualSumChecker {
	
    public static boolean hasEqualSum(int first,int second,int third) {
    	if(first+second==third)return true;
    	return false;
    	
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int first=sc.nextInt();
		int second=sc.nextInt();
		int third=sc.nextInt();
		sc.close();
		if(hasEqualSum(first,second,third)) {
			System.out.print("The sum is equal.");
		}
		else System.out.print("The sum is not equal.");
	}

}