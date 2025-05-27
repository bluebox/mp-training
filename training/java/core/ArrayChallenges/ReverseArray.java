package ArrayChallenges;

import java.util.Scanner;

public class ReverseArray {
	
	public static int[] reverse(int[] array,int size) {
		int[] answer=new int[size];
		for(int i=0;i<size;i++) {
			answer[i]=array[size-i-1];
		}
		return answer;
	}
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter size of array.");
		int size = sc.nextInt();
		int[] array=new int[size];
		System.out.println("Enter array elements one by one.");

		for (int i = 0; i < size; i++) {
			int input=sc.nextInt();
			array[i]=input;
		}
		int[] reversedArray=reverse(array,size);
		System.out.println("Reversed the array.");
		for (int i = 0; i < size; i++) {
			System.out.print(reversedArray[i]+" ");
		}
		sc.close();

	}
	

}
