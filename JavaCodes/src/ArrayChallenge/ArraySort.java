package ArrayChallenge;
import java.util.*;
public class ArraySort {

	public static void main(String[] args) {
	    Scanner sc=new Scanner(System.in);
	    System.out.println("enter the size of array");
	    int n=sc.nextInt();
	    Random random=new Random();
	    int []ans=new int[n];
	    for(int i=0;i<n;i++) {
	    	ans[i]=random.nextInt(100);
	    }
	    System.out.println("array before sort");
	    for(int ele:ans) {
	    	System.out.print(ele+" ");
	    }
	    System.out.println();
	    System.out.println("array after sort");
	    Arrays.sort(ans);
	    for(int i=0;i<(n/2);i++) {
	    	int temp=ans[i];
	    	ans[i]=ans[n-1-i];
	    	ans[n-1-i]=temp;
	    }
	    for(int ele:ans) {
	    	System.out.print(ele+" ");
	    }
	}

}
