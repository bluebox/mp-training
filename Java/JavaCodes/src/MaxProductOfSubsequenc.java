import java.util.Scanner;

public class MaxProductOfSubsequenc {
    public static int solve(int arr[],int n,int m) {
    	int maxi=arr[0],mini=arr[0],ans=maxi*arr[m-1];
    	int cnt=1;
    	for(int i=m;i<n;i++) {
    		maxi=Math.max(maxi, arr[cnt]);
    		mini=Math.min(mini,arr[cnt]);
    		ans=Math.max((Math.max(ans,maxi*arr[i])),(Math.max(mini*arr[i],ans)));
    		cnt++;
    	}
    	return ans;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int arr[]=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
		}
		System.out.println("enter m");
		int m=sc.nextInt();
		System.out.println(solve(arr,n,m));
		sc.close();
	}

}
