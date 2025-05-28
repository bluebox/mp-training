package ArrayChallenge;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseArray {
    public static void reverse(int []ans) {
    	int size=ans.length;
    	for(int i=0;i<(size/2);i++) {
    		int temp=ans[i];
    		ans[i]=ans[size-1-i];
    		ans[size-1-i]=temp;
    	}
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int size=sc.nextInt();
		int []ans=new int[size];
		for(int i=0;i<ans.length;i++) {
			ans[i]=sc.nextInt();
		}
		System.out.println("array before reversing");
		for(int ele:ans)System.out.print(ele+" ");
		System.out.println();
		System.out.println("after reversing");
		reverse(ans);
		for(int ele:ans)System.out.print(ele+" ");
	}

}
