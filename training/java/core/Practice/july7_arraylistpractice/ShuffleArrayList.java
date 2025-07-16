package Practice.july7_arraylistpractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ShuffleArrayList {

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
		System.out.println("ArrayList before shuffling: "+myColors);
		System.out.println("Shuffling the arrayList using Collections shuffle method");
		Collections.shuffle(myColors);
		System.out.println("ArrayList after shuffling: "+myColors);
		sc.close();
	}

}
