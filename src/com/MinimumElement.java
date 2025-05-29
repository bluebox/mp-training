import java.util.Scanner;
import java.util.Arrays;

public class MinimumElement {
	public static void main(String[] args) {
		int[] arr = readIntegers();
		int mini = findMin(arr);
		System.out.println(mini);
	}
	
	static int[] readIntegers() {
		System.out.println("Enter the integers (comma-seperated)");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] numberedStrings = input.split(",");
//		System.out.println(Arrays.toString(numberedStrings));
		int[] arr = new int[numberedStrings.length];
		for(int i=0;i<numberedStrings.length;i++) {
			int num = Integer.parseInt(numberedStrings[i].trim());
			arr[i] = num;
		}
		return arr;
	}
	
	static int findMin(int[] arr) {
		int mini = Integer.MAX_VALUE;
		for(int i:arr) {
			mini = Math.min(mini, i);
		}
		return mini;
	}
}
