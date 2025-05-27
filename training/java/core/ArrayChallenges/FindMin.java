package ArrayChallenges;
import java.util.*;

public class FindMin {

	public static void readIntegers() {
		Scanner sc=new Scanner(System.in);

		System.out.println("Enter size of array.");
		
		int sizeOfArray = sc.nextInt();
		int[] array=new int[sizeOfArray];
		System.out.println("Enter array elements one by one.");

		for (int i = 0; i < sizeOfArray; i++) {
			int input=sc.nextInt();
			array[i]=input;
		}
		String elements=Arrays.toString(array);
		System.out.println("Elements in array are: \n"+elements);
		
		findMin(array,sizeOfArray);
	}
	public static void findMin(int[] array,int sizeOfArray) {
		int mini=array[0];
		
		for(int i=1;i<sizeOfArray;i++) {
			if(array[i]<mini) {
				mini=array[i];
			}
		}
		System.out.println("Minimum element in array is: "+mini);
	}
	public static void main(String[] args) {
		
		//calling the method to read input
		readIntegers();
		
	}
}
