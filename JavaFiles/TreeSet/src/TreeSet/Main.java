package TreeSet;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		
		Seat se =new Seat();
				
		int Capacity= 150;
		TreeSet<Seat> seats =new TreeSet<>();
		
		IntStream.rangeClosed(1, Capacity).forEach(s -> seats.add(new Seat(s, false)));
		System.out.println("Seats Available: ");
		print(new ArrayList<>(seats));
		System.out.println();
		System.out.println("------------------------------------------------------------");

		int [] arr = {1,2,3,4,6};
		int [] arr1 = {10,20,30,4,6};

		
		reserve(seats,1);
		print(new ArrayList<>(seats));
		System.out.println();
		System.out.println("------------------------------------------------------------");

		reserve(seats,1);

		System.out.println();
		System.out.println("------------------------------------------------------------");

		reserve(seats,2);
		print(new ArrayList<>(seats));
		System.out.println();
		System.out.println("------------------------------------------------------------");


		reserve(seats,arr);
		System.out.println();
//		System.out.println("------------------------------------------------------------");
		
		unreserve(seats,2);
		print(new ArrayList<>(seats));
		System.out.println();
		System.out.println("------------------------------------------------------------");
		
		unreserve(seats,9);
		print(new ArrayList<>(seats));
		System.out.println();
		System.out.println("------------------------------------------------------------");
		
		unreserve(seats,arr1);
		System.out.println();


	}
	
	public static <T> void print(ArrayList<T> arrayList){
		
		int counter =0;
		for(T seat : arrayList) {
			if(counter%10==0) {
				System.out.println();
			}
			counter++;
			System.out.print(seat);

		}
		System.out.println();

		
	}
	
	public static void reserve(TreeSet<Seat> seats,int seatNo) {
		

		for(Seat s : seats) {
			if(s.getSeatNo()==seatNo) {
				if(!s.isSeatStatus()) {
					s.setSeatStatus(true);	
					System.out.println("The SeatNo "+seatNo+" is booked");
				}else {
					System.out.println("The SeatNo "+seatNo+" is all ready booked");
				}
			}
		}
		
	}
	
	public static void unreserve(TreeSet<Seat> seats,int seatNo) {
			
	
			for(Seat s : seats) {
				if(s.getSeatNo()==seatNo) {
					if(s.isSeatStatus()) {
						s.setSeatStatus(false);	
						System.out.println("The SeatNo "+seatNo+" is unreserved");
					}else {
						System.out.println("The SeatNo "+seatNo+" is not booked");
					}
				}
			}
			
		}
	
	public static void reserve(TreeSet<Seat> seats,int[] arr) {
		
		for(int i=0;i<arr.length;i++) {	
			for(Seat s : seats) {
				if(s.getSeatNo()==arr[i]) {
					if(!s.isSeatStatus()) {
						s.setSeatStatus(true);
						System.out.println("The SeatNo "+arr[i]+" is booked");
						print(new ArrayList<>(seats));
						System.out.println("------------------------------------------------------------");
						
					}else {
						System.out.println("The SeatNo "+arr[i]+" is all ready booked");
						System.out.println("------------------------------------------------------------");

						break;
					}
				}
			}
			System.out.println();

		}
	}
	
	public static void unreserve(TreeSet<Seat> seats,int[] arr) {
		
		for(int i=0;i<arr.length;i++) {	
			for(Seat s : seats) {
				if(s.getSeatNo()==arr[i]) {
					if(s.isSeatStatus()) {
						s.setSeatStatus(false);
						System.out.println("The SeatNo "+arr[i]+" is unreserved");
						print(new ArrayList<>(seats));
						System.out.println("------------------------------------------------------------");
						
					}else {
						System.out.println("The SeatNo "+arr[i]+" is not booked");
						System.out.println("------------------------------------------------------------");

						break;
					}
				}
			}
			System.out.println();

		}
	}

}