package Arrays;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class Person {
	public static void main (String[] args) {
	Queue<Integer> dishes=new LinkedList<Integer>();
	Queue<Integer> persons=new LinkedList<Integer>();
	int count=0;
	System.out.println("Enter size :");
	Scanner sc=new Scanner(System.in);
	int length=sc.nextInt();
	System.out.println("Enter Persons:");
	for(int i=0;i<length;i++) {
		persons.add(sc.nextInt());
	}
	System.out.println("Enter Dishes:");
	for(int i=0;i<length;i++) {
        dishes.add(sc.nextInt());
	}
	while(dishes.size()>0 && persons.size()>0 && count<persons.size()) {
		if(dishes.peek()==persons.peek()) {
			dishes.poll();
			persons.poll();
			count=0;
		}
		else {
			int p=persons.poll();
			persons.offer(p);
			count+=1;
		}
		
	}
	System.out.println("The number of hungry persons are "+ persons.size());
	sc.close();
	}

}
