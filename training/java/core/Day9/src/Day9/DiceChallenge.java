package Day9;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DiceChallenge {
	public static void main(String[] args) {
		
		int[] arr=new int[5];
		generate(arr,"ALL");
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println(Arrays.toString(arr));
			System.out.println("enter ALL to get the dices roll again or write the numbers you want to roll again ");
			System.out.println("enter to stay with the generation ");
			String str=sc.nextLine();
			if(str.isBlank()) {
				break;
			}
			generate(arr,str.toUpperCase());
		}
	}
	
	public static int[] generate(int[] arr, String str) {
		String[] nums=str.split(" ");
		if(str.equals("ALL")) {
			for(int i=0;i<arr.length;i++) {
				arr[i]=ints();
			}
		}
		else{
			int j=0;
			while(j<nums.length) {
			for(int i=0;i<arr.length;i++) {
				if(arr[i]==(Integer.parseInt(nums[j]))) {
					arr[i]=ints();
					break;
				}
			}
			j++;
			}
		}
		return arr;
	}
	public static int ints() {
		Random r=new Random();
		return r.nextInt(1,7);
	}
}
