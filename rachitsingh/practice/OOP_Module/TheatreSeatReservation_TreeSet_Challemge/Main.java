package TheatreSeatReservation_TreeSet_Challemge;

public class Main {
	public static void main(String[] args) {
		Theatre theatre = new Theatre("INOX", 25, 10);
		theatre.printSeatMapping();

		theatre.reserveSeat("E0005");
		theatre.reserveSeat("F0010");
		theatre.reserveBlockOfSeats(3, 'E', 'F', 1, 10);
		theatre.printSeatMapping();
	}
}
