package ArrayChallenges;

import java.util.*;

public class SortInDescendingOrder {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter size of array.");
		int sizeOfArray = sc.nextInt();
		int[] array=new int[sizeOfArray];
		System.out.println("Enter array elements one by one.");

		for (int i = 0; i < sizeOfArray; i++) {
			int input=sc.nextInt();
			array[i]=input;
		}
		//sorting in descending order using bubble sort startegy
		for (int i = 0; i < sizeOfArray-1; i++) {
			for(int j=0;j<sizeOfArray-i-1;j++) {
				if(array[j+1]>array[j]) {
					int temp=array[j+1];
					array[j+1]=array[j];
					array[j]=temp;
				}
			}
		}
		System.out.println("Sorted the array in descending order.");
		for (int i = 0; i < sizeOfArray; i++) {
			System.out.print(array[i]+" ");
		}
		sc.close();

	}

}
