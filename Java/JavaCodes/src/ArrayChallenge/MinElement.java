package ArrayChallenge;
import java.util.*;
public class MinElement {
    public static int minElement(int []ans) {
    	int mini=Integer.MAX_VALUE;
    	for(int ele:ans) {
    		mini=Math.min(ele, mini);
    	}
    	return mini;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int size=sc.nextInt();
		int []ans=new int[size];
		for(int i=0;i<ans.length;i++) {
			ans[i]=sc.nextInt();
		}
		System.out.println("min element= "+minElement(ans));
	}

}
