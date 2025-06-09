package minFinder;
import java.util.*;

public class MinFinder {
	Scanner sc=new Scanner(System.in);
	int readInteger() {
		int n=sc.nextInt();
		return n;
	}
	
	int[] readElements(int n) {
		int[] a=new int[n];
		for(int i=0;i<n;i++) {
			a[i]=sc.nextInt();
		}
		return a;
	}
	
	int findMin(int[] a) {
		int min=a[0];
		for(int i=0;i<a.length;i++) {
			if(min>a[i]) {
				min=a[i];
			}
		}
		return min;
	}
}
