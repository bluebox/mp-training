import java.util.*;
public class ArrayChallenge {

	public static void main(String[] args) {
		// TODOy Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int len=sc.nextInt();
		int arr[]=getRandomArray(len);
		Arrays.sort(arr);
		for(int i=len-1;i>=0;i--)
		{
			System.out.print(arr[i]+" ");
		}
	}
	public static int[] getRandomArray(int len)
	{
		Random random=new Random();
		int[] newArray=new int[len];
		for(int i=0;i<len;i++)
		{
			newArray[i]=random.nextInt(100);
		}
		return newArray;
	}
}
