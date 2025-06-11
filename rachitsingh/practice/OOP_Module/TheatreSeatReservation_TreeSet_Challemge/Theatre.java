package TheatreSeatReservation_TreeSet_Challemge;

import java.util.*;

public class Theatre {
	private String theatreName;
	private int seatsPerRow;
	private TreeSet<Seat> seats;

	public Theatre(String name, int totalRows, int seatsPerRow) {
		this.theatreName = name;
		this.seatsPerRow = seatsPerRow;
		this.seats = new TreeSet<>();

		for (int row = 0; row < totalRows && row <= 25; row++) {
			char rowLabel = (char) ('A' + row);
			for (int seatNo = 1; seatNo <= seatsPerRow; seatNo++) {
				seats.add(new Seat(rowLabel, seatNo));
			}
		}
	}

	private static class Seat implements Comparable<Seat> {
		private String seatID;
		private boolean isReserved;

		public Seat(char row, int seatNumber) {
			this.seatID = String.format("%c%04d", row, seatNumber);
			this.isReserved = false;
		}

		public Seat(String seatID) {
			this.seatID = seatID;
			this.isReserved = false;
		}

		public String getSeatID() {
			return seatID;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (!(obj instanceof Seat)) return false;
			return seatID.equals(((Seat) obj).seatID);
		}

		@Override
		public int hashCode() {
			return seatID.hashCode();
		}

		@Override
		public int compareTo(Seat other) {
			return this.seatID.compareTo(other.seatID);
		}
	}

	public boolean reserveSeat(String seatID) {
		Seat dummySeat = new Seat(seatID);
		Seat seatFound = seats.floor(dummySeat);

		if (seatFound != null && seatFound.equals(dummySeat)) {
			if (!seatFound.isReserved) {
				seatFound.isReserved = true;
				System.out.println("Seat: " + seatID + " has been reserved.");
				return true;
			} else {
				System.out.println("Seat: " + seatID + " has already been reserved.");
				return false;
			}
		}
		System.out.println("Seat: " + seatID + " was not found.");
		return false;
	}

	public void printSeatMapping() {
		char currentRow = 0;
		for (Seat seat : seats) {
			if (seat.getSeatID().charAt(0) != currentRow) {
				currentRow = seat.getSeatID().charAt(0);
				System.out.println();
				System.out.print(currentRow + ": ");
			}
			System.out.print(seat.isReserved ? "[RESERVED] " : "[" + seat.getSeatID().substring(1) + "] ");
		}
		System.out.println("\n");
	}

	// Bonus challenge method (non-static)
	public boolean reserveBlockOfSeats(int reservationCount, char rowStart, char rowEnd, int seatStart, int seatEnd) {
		if (reservationCount <= 0 || rowStart > rowEnd || seatStart > seatEnd
				|| reservationCount > (seatEnd - seatStart + 1)) {
			return false;
		}

		for (char row = rowStart; row <= rowEnd; row++) {
			for (int start = seatStart; start <= (seatEnd - reservationCount + 1); start++) {
				List<Seat> seatBlock = new ArrayList<>();
				boolean allAvailable = true;

				for (int i = 0; i < reservationCount; i++) {
					String seatID = String.format("%c%04d", row, start + i);
					Seat dummy = new Seat(seatID);
					Seat actual = seats.floor(dummy);

					if (actual == null || !actual.equals(dummy) || actual.isReserved) {
						allAvailable = false;
						break;
					}
					seatBlock.add(actual);
				}

				if (allAvailable) {
					for (Seat s : seatBlock) {
						s.isReserved = true;
					}
					System.out.println("Block of " + reservationCount + " seats has been reserved in row " + row
							+ " starting at seat " + start);
					return true;
				}
			}
		}
		System.out.println("Unable to reserve block of " + reservationCount + " seats in given row.");
		return false;
	}
}
