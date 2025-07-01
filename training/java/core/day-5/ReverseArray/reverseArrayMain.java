package ReverseArray;

public class reverseArrayMain {
public static void main(String []args)
{

	int[] arr1=new int[5];
	arr1[0]=3;
	arr1[1]=9;
	arr1[2]=67;
	arr1[3]=76;
	arr1[4]=4;
	System.out.println("Before reversing array:");
	for(int i=0;i<arr1.length;i++)
	{
	  System.out.print(arr1[i]+" ");
	}
	System.out.println();
	System.out.println("array elements after reversing :");
	int[] arr2=reverseArray(arr1);
	
	for(int i=0;i<arr2.length;i++)
	{
		System.out.print(arr2[i]+" ");
	}
}

private static int[] reverseArray(int[] arr1) {
	int[] arr3=new int[arr1.length];
	for(int i=0;i<arr1.length;i++)
	{
		arr3[i]=arr1[arr1.length-i-1];
	}
	return arr3;
}
}
