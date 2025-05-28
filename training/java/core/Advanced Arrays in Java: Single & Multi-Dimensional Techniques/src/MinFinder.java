import java.util.Scanner;

public class MinFinder {
	static Scanner sc=new Scanner(System.in);
	public static int readInteger()
	{
		return sc.nextInt();
	}
	public static int[] readElements(int num)
	{
		int arr[]=new int[num];
		for(int i=0;i<num;i++)
		{
			arr[i]=readInteger();
	
		}
		return arr;
	}
    public static int findMin(int[] arr){
        int min = arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }

}
