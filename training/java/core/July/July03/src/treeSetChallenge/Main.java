package treeSetChallenge;

import java.util.Scanner;

import treeSetChallenge.Theatre.Seat;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Theatre Name");
		String name = sc.nextLine();
		
		var theatre =new Theatre(name,20,520);
		
		boolean exit = false;
		
		while(!exit){
			printOptions();
			
			int option = sc.nextInt();
			
			switch(option){
			
				case 1:
					while(true){
						System.out.println("Enter Seat Number or (exit) for exit");
						String seatNumber = sc.nextLine();
						System.out.println(seatNumber);
						if(seatNumber.toLowerCase().equals("exit")) {
							break;
						}
						
						
						if(seatNumber.length() >= 2) {
							char row = seatNumber.toUpperCase().charAt(0);
							int number = Integer.parseInt(seatNumber.substring(1));
							Seat seat = theatre.getSeat(row,number);
							if(seat != null) {
								seat.setReserved(true);
								System.out.println("Seat Booked Sucessfully");
								theatre.printSeatMap();
								break;
							}
							else {
								System.out.println("enter Correct Seat Number");
								 
							}
						}else {
							System.out.println("enter Correct Seat Number");
						}
					}
					break;
				case 2:
					theatre.printSeatMap();
					break;
				default:
					exit=true;
					break;
			}
		}
		sc.close();
		
	}
	
	public static void printOptions() {
		System.out.println("1. Booking Tackets " );
		System.out.println("2. View the Seat Map");
		System.out.println("3. Exit");
		System.out.println("Enter Your Choice");
	}
}
