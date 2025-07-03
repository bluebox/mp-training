package Project;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.Scanner;

import Project.Theatre.Seat;

public class Main {
	public static void main(String [] args) {
		Scanner sc=new Scanner(System.in);
		TreeSet<Seat> seats=new TreeSet<>(Comparator.comparing(Theatre.Seat::getRow).thenComparing(Theatre.Seat::getSeatNo));
		for(char row='A'; row<='J'; row++) {
			for(int n=1; n<=10; n++) {
				Theatre.Seat s=new Theatre.Seat(row,n);
				seats.add(s);
			}
		}
		Theatre t=new Theatre("new theatre", 10, seats);
		System.out.println("=======================Seats Description=====================");
		System.out.println(t);
		System.out.print("Enter Row in which you want to book : ");
		char r=sc.next().charAt(0);
		System.out.print("Enter Seat Number which you want to book : ");
		int sn=sc.nextInt();
		for(Theatre.Seat s:t.getSeats()) {
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
