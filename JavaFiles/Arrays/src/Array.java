import java.util.Arrays;

public class Array {

	public static void main(String[] args) {
		
		int[] arr=new int[10];
		int[] arr1= {1,2,3,4,5};
		
		for(int val : arr) {
			System.out.print(val+" ");
		}
		System.out.println();

		for(int val : arr1) {
			System.out.print(val+" ");
		}
		
		System.out.println();
		
		
		System.out.println(arr);
		System.out.println(arr1);
		
		Arrays.fill(arr, 5);
		System.out.println(Arrays.toString(arr));
		
		int[] arr2= {1,3,2,5,4,7,9,8,6};
		int[] arr3= {10,3,20,50,4,70,56,2,6,8};
		int[] arr4= {10,3,20,50,4,70,56,2,6,8};
		int[] arr5=arr4;
		
		System.out.println(Arrays.toString(arr2));
		Arrays.sort(arr2, 3, 9);
		System.out.println(Arrays.toString(arr2));
		Arrays.sort(arr2);
		System.out.println(Arrays.toString(arr2));
		Arrays.sort(arr3);
		System.out.println(Arrays.toString(arr3));
		if(Arrays.binarySearch(arr3, 70)!=-1) {
			System.out.println("Element Found at index : "+Arrays.binarySearch(arr3, 70));
		}else {
			System.out.println("Element not Found");
		}
		
		Arrays.fill(arr3, 5);
		Arrays.fill(arr4, 5);

		System.out.println(Arrays.toString(arr4));
		System.out.println(Arrays.toString(arr3));

		System.out.println(arr5.equals(arr4));
		
		String[] str=new String[10];
		
		str[0]="Hello";
		str[1]="A";
		
		System.out.println(Arrays.toString(str));
		
		Object[] obj=new Object[10];
		
		obj[0]=1;
		obj[1]="Hello";
		obj[2]='A';
		obj[3]=12.90;
		obj[4]=new int[] {1,2,3,4,5};
		obj[5]= new int[][] {{1,2,3,4,5},{6,7,8,9,10}};
		obj[6]= new int[][][] {{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}}};
		obj[7]="Hello World";
		
		System.out.println(Arrays.deepToString(obj));
		

		



	}

}
