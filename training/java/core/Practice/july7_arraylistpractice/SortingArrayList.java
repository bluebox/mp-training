package Practice.july7_arraylistpractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortingArrayList {

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
		System.out.println("ArrayList Before sorting: "+myColors);
		Collections.sort(myColors);
		System.out.println("ArrayList after sorting in natural order: "+myColors);
		System.out.println("Sorting in descending order");
		Collections.sort(myColors, Collections.reverseOrder());
		System.out.println("ArrayList after sorting in reverse order: "+myColors);
		sc.close();
	}

}
