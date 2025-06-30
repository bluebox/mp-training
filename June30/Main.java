package June30;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		
		int[] s1= {1,2,3,4,5};
		int[] s2= {1,2,3,4,5};
		int[] s3= {5,2,3,4,1};
		int[] s4= {1,2,3,4,5,0};
		int[] s5=s1;
		
		System.out.println(Arrays.equals(s1,s2));
		System.out.println(Arrays.equals(s1,s3));
		System.out.println(Arrays.equals(s1,s4));
		System.out.println(Arrays.equals(s1,s5));
		
		System.out.println(" ");
		System.out.println(s1==s2); 
		System.out.println(s1==s3);
		System.out.println(s1==s5);
		
		System.out.println(" ");
		int[] arr= {1,2,3,4,5};
		System.out.println(arr instanceof int[]);
		
		System.out.println(" ");
		Arrays.fill(arr, 17);
		System.out.println(Arrays.toString(arr));
		
	}
}
