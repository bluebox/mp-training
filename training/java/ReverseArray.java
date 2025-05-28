import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class ReverseArray {
	public static void readIntegers(int[] array) {
		for(int i=0;i<array.length;i++) {
			Random digit= new Random();
			int digit1=digit.nextInt(100);
			array[i]=digit1;
		}
		
	}
	public static void reverse(int[] array) {
		for(int i=0;i<array.length/2;i++) {
			int temp=array[i];
			array[i]=array[array.length-1-i];
			array[array.length-i-1]=temp;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		//int n=sc.nextInt();
		int[] array=new int[sc.nextInt()];
		readIntegers(array);
		System.out.println("Array before Reversing :"+Arrays.toString(array));
		reverse(array);
		System.out.println("Array after Reversing :"+Arrays.toString(array));
		
	}

}
