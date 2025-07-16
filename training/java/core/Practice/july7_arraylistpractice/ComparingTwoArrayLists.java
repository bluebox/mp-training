package Practice.july7_arraylistpractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ComparingTwoArrayLists {

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
		System.out.println("Copying all the elements to another arrayList by using Collection.copy()[Deep copy]");
		List<String> copiedList=new ArrayList<>();
		Collections.copy(myColors, copiedList);
		System.out.println("Copying all the elements to another arrayList direct copy [Shallow copy/ reference copy]");
		List<String> copiedList2=myColors;
		
		System.out.println("Comparing the two arrayLists copied using Collection.copy() [reference check]");
		System.out.println(copiedList2==copiedList);
		System.out.println("Comparing the two arrayLists copied using Collection.copy()[equals check] ");
		System.out.println(copiedList2.equals(myColors));
		System.out.println("Comparing the two arrayLists copied directly shallow copy [reference check]");
		System.out.println(copiedList2==myColors);
		System.out.println("Comparing the two arrayLists copied directly shallow copy [equals check]");
		System.out.println(copiedList2.equals(myColors));
		sc.close();
	}

}
