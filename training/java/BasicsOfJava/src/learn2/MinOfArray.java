package learn2;
import java.util.Scanner;
public class MinOfArray {
	
	public static void main(String[] args) {
		
		int array[] =readIntegers();
		System.out.println("minimum number is "+ minimumNumber(array));
	}
	
	public static int minimumNumber(int[] array) {
		
		int minimum = array[0];
		for(int i : array) {
			if(minimum > i) 
			{
				minimum = i;
			}
		}
		return minimum;
	}
	
	public static int[] readIntegers() {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter number of elements");
//		String s = scanner.nextLine();
//		
//		int n = Integer.parseInt(s);
		int n = scanner.nextInt();
		scanner.nextLine();
		String[] array = new String[n];
		
		System.out.println("Enter elements");
		String inputString = scanner.nextLine();
		
		array = inputString.split(",");
		
		int[] array2 = new int[n];
		
		for(int i = 0;i < n;i++) {
			
			array2[i] = Integer.parseInt(array[i]);
		}
		
		for(int i : array2) {
			
			System.out.println(i + " ");
		}
		
		return array2;
	}
}
