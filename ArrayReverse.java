import java.util.Scanner;
public class ArrayReverse {

	public static void main(String[] args) {
		Scanner scan =new Scanner(System.in);
		int n=scan.nextInt();
		int[] arr=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=scan.nextInt();
		}
		System.out.println("Array before reversing:");
		for(int ele:arr) {
			System.out.print(ele+" ");
		}
		System.out.println();
		System.out.println("Array after revering:");
		int[] res=reverse(arr,n);
		for(int ele:res) {
			System.out.print(ele+" ");
		}

	}
	public static int[] reverse(int[] arr,int n) {
		int start=0;
		int end=n-1;
		while(start<=end) {
			int temp=arr[start];
			arr[start]=arr[end];
			arr[end]=temp;
			start++;
			end--;
		}
		return arr;
	}

}
