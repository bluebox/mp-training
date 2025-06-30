import java.util.Arrays;

public class MultiDimArrray {

	public static void main(String[] args) {
		
		int[] arr= {1,2,3,4,5};
		
		int[][] arr1= {{1,2,3,4,5},{6,7,8,9,10}};
		
		int[][][] arr2= {{ {1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}}};
		
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));

		System.out.println(Arrays.deepToString(arr1));

		System.out.println(Arrays.deepToString(arr2));
		
		arr1[1]= new int[]{10,20,30,40,50};
		System.out.println(Arrays.deepToString(arr1));


		
	}

}
