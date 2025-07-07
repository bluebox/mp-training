package challenge_3rd_july;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class Theatre {
	class Seat implements Comparable<Seat> {
		private String seatNum;
		private boolean reserved;

		public Seat(char row, int seatNum) {
			this.seatNum = String.format("%c%03d", row, seatNum).toUpperCase();
			reserved = false;
		}

		public boolean isReserved() {
			return reserved;
		}

		public void reserve() {
			this.reserved = true;
		}

		@Override
		public int compareTo(Seat otherSeat) {
			return this.seatNum.compareTo(otherSeat.seatNum);
		}

		@Override
		public String toString() {
			return seatNum;
		}
	}

	private String theatreName;
	private int seatsPerRow;
	private NavigableSet<Seat> seats;

	public Theatre(String theatreName, int rows, int seatsPerRow) {
		seats = new TreeSet<>();
		this.theatreName = theatreName;
		this.seatsPerRow = seatsPerRow;
		if (rows > 26)
			rows = 26;
		char lastRow = (char) ('A' + rows - 1);
		for (char row = 'A'; row <= lastRow; row++) {
			for (int i = 1; i <= seatsPerRow; i++) {
				seats.add(new Seat(row, i));
			}
		}
	}

	public void reserveSeats(char row, int seatNo, int number) {
		if (seatNo <= 0 || (seatNo + number - 1) > this.seatsPerRow) {
			System.out.println("invalid selection seat number must be + in between 1 and " + this.seatsPerRow);
			return;
		}
		row = Character.toUpperCase(row);
		Seat startSeat = new Seat(row, seatNo);
		Seat endSeat = new Seat(row, seatNo + number - 1);
		NavigableSet<Seat> requestedSeats = seats.subSet(startSeat, true, endSeat, true);
		if (requestedSeats.size() != number) {
			System.out.println("required number of seats doesn't exist");
			return;
		}
		for (Seat seat : requestedSeats) {
			if (seat.isReserved()) {
				System.out.println("reservation failed, seat: " + seat + " already reserved");
				return;
			}
		}
		for (Seat seat : requestedSeats) {
			seat.reserve();
		}
		System.out.println("seats reserved successfully");
	}

	public void printSeatLayout() {
		System.out.println("\n==============" + this.theatreName + "===========");
		int seatCounter = 0;
		for (Seat seat : seats) {
			System.out.print(seat);
			System.out.print(seat.isReserved() ? "[X] " : "[_] ");
			seatCounter++;
			if (seatCounter % this.seatsPerRow == 0) {
				System.out.println();
			}
		}
		System.out.println();
	}
}