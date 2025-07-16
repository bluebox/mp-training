package Practice.july7_arraylistpractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JoinTwoArrayLists {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		List<String> myColors=new ArrayList<>();
		System.out.println("Enter how many elements that you need to add to the array");
		int n=sc.nextInt();
		System.out.println("Enter elements to the array");
		while (n>0){
			myColors.add(sc.next());
			n--;
		}
		List<String> myFavColors=Arrays.asList("seaGreen","NavyBlue","Mint");
		myColors.addAll(myFavColors);
		System.out.println("My colors list after joining the favarouite colors: "+myColors);
		sc.close();
	}

}
