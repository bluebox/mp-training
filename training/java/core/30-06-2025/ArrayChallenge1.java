import java.util.*;
public class ArrayChallenge1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]=new int[5];
		Scanner s=new Scanner(System.in);
		System.out.println("Enter array elements:");
		for(int i=0;i<5;i++)
		{
			arr[i]=s.nextInt();
		}
		
		System.out.println("Array Elements are:");
		
//		for(int i=0;i<arr.length;i++)
//		{
//			System.out.println(arr[i]);
//		}
//		for(int num:arr)
//		{
//			System.out.println(num);
//		}
		
		System.out.println(Arrays.toString(arr));
		
	}

}
