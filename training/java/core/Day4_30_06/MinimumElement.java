package Day4_30_06;
import java.util.Scanner;
public class MinimumElement {
	public static void main(String args[]) {
		int[] arr=readIntegers();
		System.out.println(findMin(arr));
	}
	public static int[] readIntegers() {
		System.out.println("Enter the integers lis:");
		
		String[] inp= (new Scanner(System.in)).nextLine().split(",");
		int[] out=new int[inp.length];
		for(int i=0;i<inp.length;i++) {
			out[i]=Integer.parseInt(inp[i]);
		}
		return out;
	}
	public static int findMin(int[] inp) {
		int min=inp[0];
		for(int i:inp) {
			if(i<min) {
				min=i;
			}
		}
		return min;
	}
}
