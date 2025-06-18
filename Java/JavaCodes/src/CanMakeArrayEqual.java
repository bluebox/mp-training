import java.util.Scanner;

public class CanMakeArrayEqual {
    public static boolean solve(int arr[],int k) {
    	int a=0,b=0;
        for(int i=0;i<arr.length;i++) {
        	if(arr[i]==1)a++;
        	else b++;
        }
        int mini=Integer.MAX_VALUE;
        if(a%2==0) {
        	int first=-1,second=-1;
        	for(int i=0;i<arr.length;i++) {
        		if(arr[i]==1 && first==-1)first=i;
        		if(arr[i]==1)second=i;
        	}
        	if(first!=-1) {
        		mini=Math.min(mini,second-first);
        	}
        }
        if(b%2==0) {
        	int first=-1,second=-1;
        	for(int i=0;i<arr.length;i++) {
        		if(arr[i]==-1 && first==-1)first=i;
        		if(arr[i]==-1)second=i;
        	}
        	if(first!=-1) {
        		mini=Math.min(mini,second-first);
        	}
        }
        return mini<=k;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int arr[]=new int[n];
		for(int i=0;i<n;i++)arr[i]=sc.nextInt();
		System.out.println("enter k");
		int k=sc.nextInt();
	    System.out.println(solve(arr,k));
	    sc.close();
	}

}
