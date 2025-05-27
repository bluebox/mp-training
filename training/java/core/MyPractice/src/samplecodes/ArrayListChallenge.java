package samplecodes;
import java.util.*;
public class ArrayListChallenge {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ArrayList<Integer> l=new ArrayList<>();
		while(true) {
			System.out.println("Enter a number you want to do :");
			System.out.println("0 - to shutdown");
			System.out.println("1 - to add item");
			System.out.println("2 - to remove item");
			int n=sc.nextInt();
			switch(n) {
			case 0:
				return;
			case 1:
				System.out.println("Enter a number you want to insert into list:");
				int num=sc.nextInt();
				l.add(num);
				break;
			case 2:
				System.out.println("Enter a number you want to remove from the list:");
				num=sc.nextInt();
				int idx=l.indexOf(num);
				l.remove(idx);
				break;
			default:
				System.out.println("Please enter number among these only 0,1,2:");
				break;
			}
			System.out.println(l);
		}
	}
}
