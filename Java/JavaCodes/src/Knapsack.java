import java.util.Arrays;
import java.util.Scanner;

public class Knapsack {
	public static int solve(int W[],int V[],int dp[][],int n,int w) {
    	if(n<0 || w<=0)return 0;
    	if(dp[n][w]!=-1)return dp[n][w];
    	int maxi=0;
    	if(W[n]<=w) {
    		maxi=Math.max(maxi,V[n]+solve(W,V,dp,n-1,w-W[n]));
    	}
    	maxi=Math.max(maxi, solve(W,V,dp,n-1,w));
    	return dp[n][w]=maxi;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int W[]=new int[n];
		for(int i=0;i<n;i++)W[i]=sc.nextInt();
		int V[]=new int[n];
		for(int i=0;i<n;i++)V[i]=sc.nextInt();
		System.out.println("enter the max weight");
		int w=sc.nextInt();
		int dp[][]=new int[n][w+1];
		for(int i=0;i<n;i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(solve(W,V,dp,n-1,w));
	}

}
