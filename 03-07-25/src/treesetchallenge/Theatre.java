package treesetchallenge;

import java.util.*;

public class Theatre {

    private final String theatreName;
    private final int seatsPerRow;
    private final int numberOfRows;
    private final TreeSet<Seat> seats;

    public Theatre(String theatreName, int numberOfRows, int seatsPerRow) {
        this.theatreName = theatreName;
        this.seatsPerRow = seatsPerRow;
        this.numberOfRows = numberOfRows;
        this.seats = new TreeSet<>();
        createSeats();
    }

    private void createSeats() {
        for (char row = 'A'; row < 'A' + numberOfRows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                String seatId = row + String.format("%03d", seatNum);
                seats.add(new Seat(seatId));
            }
        }
    }

    public boolean reserveSeat(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat.reserve();
            }
        }
        System.out.println("Seat " + seatNumber + " does not exist.");
        return false;
    }

    public void printSeatMap() {
        char currentRow = ' ';
        for (Seat seat : seats) {
            if (seat.getSeatNumber().charAt(0) != currentRow) {
                currentRow = seat.getSeatNumber().charAt(0);
                System.out.print("\nRow " + currentRow + ": ");
            }
            System.out.print(seat + " ");
        }
        System.out.println();
    }

    private class Seat implements Comparable<Seat> {
        private final String seatNumber;
        private boolean reserved;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
            this.reserved = false;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public boolean reserve() {
            if (!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved.");
                return true;
            } else {
                System.out.println("Seat " + seatNumber + " is already reserved.");
                return false;
            }
        }

        @Override
        public int compareTo(Seat other) {
            return this.seatNumber.compareTo(other.getSeatNumber());
        }

        @Override
        public String toString() {
            return reserved ? "[" + seatNumber + "*]" : "[" + seatNumber + "]";
        }
    }
}