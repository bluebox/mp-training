package day10.reversearray;



import java.util.Arrays;
import java.util.Scanner;
public class ReverseArray {
    public static void main(String args[]) {
    	Scanner sc=new Scanner(System.in);
    	int n=sc.nextInt();
    	int arr[]=new int[n];
    	for(int i=0;i<n;i++)
    		arr[i]=sc.nextInt();
    	reverse(arr);
    }
    
    private static void reverse(int[] array) {
        System.out.println("Array = " + Arrays.toString(array));
        int maxIndex = array.length -1;
        int halfLength = array.length / 2;
        for(int i=0; i< halfLength; i++) {
            int temp = array[i];
            array[i] = array[maxIndex -i];
            array[maxIndex - i] = temp;
        }
        System.out.println("Reversed array = " + Arrays.toString(array));
    }
}