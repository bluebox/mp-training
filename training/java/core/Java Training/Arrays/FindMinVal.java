package Arrays;
import java.util.*;
public class FindMinVal {
	
	public static int getMinVal(int[] ar,int n){
		int ans=ar[0];
		for(int ele:ar) {
			if(ele<ans) {
				ans=ele;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the no of elements : ");
		int n=sc.nextInt();
		int[] ar=new int[n];
		System.out.print("Enter the elements : ");
		for(int i=0;i<n;i++) {
			ar[i]=sc.nextInt();
		}
		System.out.println("Array is : "+Arrays.toString(ar));
		System.out.println("The mininmum value is : "+getMinVal(ar,n));
	}

}
