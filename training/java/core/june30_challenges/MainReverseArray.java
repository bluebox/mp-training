package june30_collections;
import java.util.Arrays;
import java.util.Scanner;
public class MainReverseArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of elemnts in array:");
		int larr=sc.nextInt();
		int[] arr=new int[larr];
		System.out.println("Enter elements");
		for(int i=0;i<larr;i++) {
			arr[i]=sc.nextInt();
		}
		int[] reversearr=ReverseArray.reverseArray(arr);
		System.out.println("Reverse of the array :"+Arrays.toString(reversearr));
	
		sc.close();
}}
