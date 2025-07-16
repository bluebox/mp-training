package Practice.july7_arraylistpractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemovingThirdElement {

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
		System.out.println("Before Removing 3rd element");
		System.out.println(myColors);
		System.out.println("After Removing 3rd element");
		myColors.remove(2);
		//myColors.removeLast();
		//myColors.removeFirst();
		System.out.println(myColors);
		
		sc.close();
	}

}
