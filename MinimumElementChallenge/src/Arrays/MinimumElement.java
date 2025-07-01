package Arrays;
import java.util.Scanner;
public class MinimumElement {
	public static int[] readIntegers() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("enter the integers :");
		String input=scanner.nextLine();
		String[]splitInput=input.split(",");
		int[] num=new int[splitInput.length];
		
		for(int i=0;i<splitInput.length;i++) {
			num[i]=Integer.parseInt(splitInput[i].trim());
		}
		return num;
	}
	public static int findMin(int[] array) {
		int min=Integer.MAX_VALUE;
		for(int numm:array) {
			if(numm<min) {
				min=numm;
			}
		}
		return min;
	}
}
