package day10.minimumelement;


import java.util.Scanner;
public class MinimumElement {
     
	public static void main(String[] args) {
		//MinimumElement me=new MinimumElement();
		int n=readInteger();
		int arr[]=readElements(n);
		System.out.println("result is "+findMin(arr));
		
	}
    private static int readInteger(){
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        return num;
    }
    
    private static int[] readElements(int n){
        int[] arr =  new int[n];
        Scanner scanner =  new Scanner(System.in);
        for(int i=0;i<n;i++){
            int x = scanner.nextInt();
            arr[i] = x;
        }
        return arr;
    }
    
    private static int findMin(int[] arr){
        int min = arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }
}