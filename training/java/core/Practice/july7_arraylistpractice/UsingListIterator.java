package Practice.july7_arraylistpractice;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class UsingListIterator {

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
		System.out.println("Counting the number of colors in the arrayList starts with the letter 'b'");
		int count=0;
		ListIterator<String> myIterator=myColors.listIterator();
		while(myIterator.hasNext()) {
			if(myIterator.next().toLowerCase().startsWith("b"))
				count++;
		}
		System.out.println(count);
		sc.close();
	}

}
