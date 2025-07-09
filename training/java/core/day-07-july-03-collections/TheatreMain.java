package day7;

public class TheatreMain {

	public static void main(String[] args) {
		Theatre pvr=new Theatre("PVR INOX",6,8);
		
		pvr.reserveSeats('D', 4, 2);
		pvr.printSeatLayout();
		
		pvr.reserveSeats('D', 5, 3);
		
	}

}
