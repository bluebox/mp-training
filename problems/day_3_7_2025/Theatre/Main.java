package day_3_7_2025.Theatre;

import java.util.Set;
import java.util.TreeSet;
import java.util.*;

import day_3_7_2025.Theatre.Theatre.Seat;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		Set<Seat> seats=new TreeSet<>();
		  Theatre theater=new Theatre("PVR",5,seats);
	
		for(int i=0;i<26;i++) {
			for(int j=0;j<5;j++) {
				Theatre.xdhn();
//				seats.add(Theatre.new Seat((char)(64+i),j+1,false));
				seats.add(theater.new Seat());
			}
		}
		
		
     
       char row=sc.nextLine().trim().charAt(0);
       int num=Integer.parseInt(sc.nextLine());
       theater.reservationseat(row,num);
	}

}
