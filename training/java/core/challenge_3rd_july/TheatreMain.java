package challenge_3rd_july;

public class TheatreMain {

	public static void main(String[] args) {
		Theatre pvr=new Theatre("PVR INOX",6,8);
		
//		pvr.reserveSeats('D', 4, 1);
		pvr.reserveSeats('D', 5, 3);
		pvr.printSeatLayout();
		
		
	}

}