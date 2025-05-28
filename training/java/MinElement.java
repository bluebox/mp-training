import java.util.*;
public class MinElement {
	public static void readIntegers(int[] array) {
		for(int i=0;i<array.length;i++) {
			Random digit= new Random();
			int digit1=digit.nextInt(100);
			array[i]=digit1;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		//int n=sc.nextInt();
		int[] array=new int[sc.nextInt()];
		readIntegers(array);
		System.out.println(Arrays.toString(array));
		int min=Integer.MAX_VALUE;
		for(int i: array) {
			if(min > i) {
				min=i;
			}
		}
		System.out.print("Minumum element in the array :"+min);
		
	}

}
