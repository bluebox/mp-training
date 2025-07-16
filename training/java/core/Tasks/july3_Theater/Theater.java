package corejava.july3_Theater;

import java.util.LinkedHashSet;
import java.util.Set;

public class Theater {
    private String theaterName;
    private int noOfRows;
    private int seatsPerRow;
    private Set<Seat> seats;

    public Theater(String theatreName, int numRows, int totalSeats) {
        if (numRows > 26) {
            throw new IllegalArgumentException("Number of rows cannot exceed 26 (A-Z).");
        }
        if (totalSeats % numRows != 0) {
            throw new IllegalArgumentException("Total seats must divide evenly among rows.");
        }

        this.setTheatreName(theatreName);
        this.noOfRows=numRows;
        this.seatsPerRow = totalSeats / numRows;
        this.seats = new LinkedHashSet<>();

        for (int row = 0; row < numRows; row++) {
            char rowChar = (char) ('A' + row);
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                seats.add(new Seat(rowChar, seatNum));
            }
        }
    }

    public void printSeatMap() {
        for (char row = 'A'; row < 'A' + noOfRows; row++) {
            boolean rowPrinted = false;
            for (int i = 1; i <= seatsPerRow; i++) {
                String seatId = String.format("%c%03d", row, i);
                for (Seat seat : seats) {
                    if (seat.getSeatId().equals(seatId)) {
                        if (!rowPrinted) {
                            System.out.print(row + ": ");
                            rowPrinted = true;
                        }
                        System.out.print(seat + " ");
                    }
                }
            }
            if (rowPrinted) {
                System.out.println();
            }
        }
    }

    public boolean reserveSeat(String seatId) {
        for (Seat seat : seats) {
            if (seat.getSeatId().equals(seatId)) {
                if (seat.isReserved()) {
                    System.out.println("Seat " + seatId + " is already reserved.");
                    return false;
                } else {
                    seat.reserve();
                    System.out.println("Seat " + seatId + " reserved.");
                    return true;
                
                }
            }
        }
        System.out.println("Seat " + seatId + " does not exist.");
        return false;
    }

    public String getTheatreName() {
		return theaterName;
	}

	public void setTheatreName(String theatreName) {
		this.theaterName = theatreName;
	}

	private class Seat {
        private final String seatId;
        private boolean reserved;

        public Seat(char row, int seatNumber) {
            this.seatId = String.format("%c%03d", row, seatNumber);
            this.reserved = false;
        }

        public String getSeatId() {
            return seatId;
        }

        public boolean isReserved() {
            return reserved;
        }

        public void reserve() {
            this.reserved = true;
        }

        @Override
        public String toString() {
            return reserved ? "[XX]" : "[" + seatId + "]";
        }
    }
}
