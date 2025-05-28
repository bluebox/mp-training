import java.util.Scanner;
public class SortIntegersDescending {

	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] integerArray=getIntegers(5);
	}
	public static int[] getIntegers(int n)
	{
		int[] array=new int[n];
		System.out.println("Enter "+n+" Numbers :");
		for(int i=0;i<n;i++)
		{
			array[i]=sc.nextInt();
		}
		return array;
	}
	public static int[] sortIntegers(int[] array)
	{
		for (int i=0;i<array.length-1;i++)
		{
			for(int j=i+1;j<array.length;j++)
			{
				if(array[i]<array[j])
				{
					int temp=array[i];
					array[i]=array[j];
					array[j]=temp;
				}
			}
		}
		return array;
	}
	public static void printArray(int[] arr)
	{
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+" ");
		}
	}

}
/*
Create a program using arrays that sorts a list of integers in descending order.
Descending order is highest value to lowest.
In other words if the array had the values in it 106, 26, 81, 5, 15 your program should
ultimately have an array with 106, 81, 26, 15, 5 in it.
Set up the program so that the numbers to sort are read in from the keyboard.
Implement the following methods - getIntegers, printArray, and sortIntegers
getIntegers returns an array of entered integers from keyboard
printArray print out the contents of the array
sortIntegers should sort the array and return a new array containing the sorted numbers
you will have to figure out how to copy the array elements from the passed array into a
new array and sort them and return the new sorted array.
*/