package Practice.july7_arraylistpractice;

import java.util.ArrayList;
import java.util.Scanner;

public class TrimArrayListCapacity {

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
		System.out.println("Arraylist size before trimming: "+myColors.size());
		myColors.trimToSize();
		System.out.println("Arraylist size After trimming:"+myColors.size());
		sc.close();
	}

}
