package Practice.july7_arraylistpractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CloneArrayList {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ArrayList<String> myColors=new ArrayList<>();
		System.out.println("Enter how many elements that you need to add to the array");
		int n=sc.nextInt();
		System.out.println("Enter elements to the array");
		while (n>0){
			myColors.add(sc.next());
			n--;
		}
		List<String> myFav=(ArrayList<String>) myColors.clone();
		System.out.println(myFav);
		sc.close();
	}

}
