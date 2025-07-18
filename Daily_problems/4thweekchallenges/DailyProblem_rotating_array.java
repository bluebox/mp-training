package Weekproblems;

import java.util.Arrays;

public class DailyProblem_rotating_array {

	public static void main(String[] args) {
		
		int[] arr= {1,2,3,4,5,6,7,8};
		int k=3;
		k=k%arr.length;
		System.out.println(Arrays.toString(rotatearr(arr,k)));
		int[] arr1= {1,2,3,4,5,6,7,8};
		System.out.println(Arrays.toString(rotate(arr1,k)));
		
	}
	public static int[] rotatearr(int[] arr,int k) {
		
		while(k>0) {
			int temp=arr[0];
			int arrsize=arr.length;
			for(int i=0;i<arrsize;i++) {
				if(i==arrsize-1) {
					arr[i]=temp;
					break;
				}
				arr[i]=arr[i+1];
			}
			k--;
		
		}
		return arr;
		
	}
	public static int[] rotate(int[] arr,int k ) {
		int arrsize=arr.length-1;
		reversearr(arr,0,k-1);
		System.out.println(Arrays.toString(arr));
		reversearr(arr,k,arrsize);
		System.out.println(Arrays.toString(arr));
		reversearr(arr,0,arrsize);
		System.out.println(Arrays.toString(arr));
		return arr;
		
	}
	public static void reversearr(int[] arr,int i,int j) {
		while(i<j) {
			int temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
			i++;
			j--;
		}
	}
	
	
}
