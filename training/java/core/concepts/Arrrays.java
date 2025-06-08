package concepts;
import java.util.Arrays;
import java.lang.Math;
public class Arrrays {

	public static void main(String[] args) {
		int num[]= {1,2,3,4,5};
		System.out.println(num[0]);
		num[0]=22;
		System.out.println(num[0]);
		for(int i: num) {
			System.out.println(i);
		}
		int[] nums=new int[5];
		int j=0;
		for(int i=15; i>=11;i--) {
			nums[j]=i;
			j++;
		}
		for(j=0; j<5;j++) {
			System.out.println(nums[j]);
		}
		System.out.println(nums.length);
		System.out.println(Arrays.toString(nums));
		Arrays.sort(nums);
		System.out.println(Arrays.toString(nums));
		System.out.println(Arrays.binarySearch(nums, 14));
		int[] copy=Arrays.copyOf(nums, 3);
		System.out.println(Arrays.toString(copy));
		int[] copyrange=Arrays.copyOfRange(nums, 2,4);
		System.out.println(Arrays.toString(copyrange));
		int[] copi=copy;
		System.out.println(Arrays.equals(copi, copy));
		Arrays.fill(copi, 1);
		System.out.println(Arrays.toString(copi));
		int tarr[][]=new int[3][4];
		for(int i=0;i<3;i++) {
			for(int k=0; k<4; k++) {
				tarr[i][k]=(int) (Math.random()*100);	
			}
		}
		for(int i=0;i<3;i++) {
			System.out.println(Arrays.toString(tarr[i]));			
		}
		for(int i=0;i<3;i++) {
			for(int k=0; k<4; k++) {
				tarr[i][k]=(int) (Math.random()*10);	
			}
		}
		for(int i=0;i<3;i++) {
			System.out.println(Arrays.toString(tarr[i]));			
		}
	}
}
