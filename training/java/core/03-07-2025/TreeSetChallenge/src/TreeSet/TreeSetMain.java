package TreeSet;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

import TreeSet.Theater.Seat;

public class TreeSetMain {
	public static void main(String [] args) {
		Scanner sc=new Scanner(System.in);
		TreeSet<Seat> seats=new TreeSet<>(Comparator.comparing(Theater.Seat::getRow).thenComparing(Theater.Seat::getSeatNo));
		for(char row='A'; row<='J'; row++) 
		{
			for(int n=1; n<=10; n++) {
				Theater.Seat s=new Theater.Seat(row,n);
				seats.add(s);
			}
		}
		Theater t=new Theater("new theatre", 10, seats);
		System.out.println("Seats Description: ");
		System.out.println(t);
		System.out.print("Enter Row in which you want to book : ");
		String str=sc.nextLine();
		char r=str.charAt(0);
		while(r==' ') {
			System.out.print("U  DID NOT ENTERED ANYTHING!!!!!! ");

		System.out.print("Enter Row in which you want to book : ");
		 r=sc.next().charAt(0);
		}
		
		
	
		while(!(r<='J'&& r>='A'))
		{
			System.out.println("ENTERED WRONG ROW NUMBER!!!!!!! ");
			System.out.print("Please Enter Row in which you want to book : ");
			 r=sc.next().charAt(0);
		
		}
		System.out.print("Enter Seat Number which you want to book : ");
		int sn=sc.nextInt();
		while(!(sn>=1 && sn<=10))
		{
			System.out.println("ENTERED WRONG SEAT NUMBER !!!!!!!  ");
			System.out.print("Enter Seat Number which you want to book : ");
			 sn=sc.nextInt();
		}
		for(Theater.Seat s:t.getSeats()) {
			if(s.getRow()==r && s.getSeatNo()==sn) {
				if(s.isReserved()) {
					System.out.println("Seat already reserved...");
				}
				else {
					s.setReserved(true);
					System.out.println(t);
					System.out.println("Seat reserved Succesfully...");
				}
				sc.close();
				return;
			}
		}
		sc.close();
	}
}
