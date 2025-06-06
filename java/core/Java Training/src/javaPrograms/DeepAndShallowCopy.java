package javaPrograms;

import java.util.Arrays;

public class DeepAndShallowCopy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name="Jai";
		String name1=name;
		String name2=new String(name);
		
		int[] ar= {3,45,23,76};
		
		System.out.println("	Deep copy Demonstration ");
		int[] val=new int[ar.length];
		for(int i=0;i<ar.length;i++) {
			val[i]=ar[i];
		}
		val[0]=100;
		System.out.println("Original array is : "+Arrays.toString(ar));
		System.out.println("Deep copy array is : "+Arrays.toString(val));
		System.out.println("---------------------------------------------");
		System.out.println("	Shallow copy Demonstration ");
		int[] val1=ar;
		val1[0]=100;
		System.out.println("Original array is : "+Arrays.toString(ar));
		System.out.println("Shallow copy array is : "+Arrays.toString(val1));


	}

}
