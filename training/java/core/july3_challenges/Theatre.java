package july_3Challenges;

import java.util.*;

public class Theatre {
    private final String name;
    private final int seatsPerRow;
    private final TreeSet<Seat> seats = new TreeSet<>();

    public Theatre(String name, int numberOfRows, int totalSeats) {
        this.name = name;
        this.seatsPerRow = totalSeats / numberOfRows;

        for (int row = 0; row < numberOfRows && row < 26; row++) {
            char rowChar = (char) ('A' + row);
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                String seatId = String.format("%c%02d", rowChar, seatNum);
                seats.add(new Seat(seatId));
            }
        }
    }

    public boolean reserveSeat(String seatNumber) {
        Seat dummy = new Seat(seatNumber);
        Seat found = seats.floor(dummy);
        if (found != null && found.getSeatNumber().equals(seatNumber)) {
            return found.reserve();
        }
        return false;
    }

    public void printSeatMap() {
        char currentRow = 0;
        for (Seat seat : seats) {
            char row = seat.getSeatNumber().charAt(0);
            if (row != currentRow) {
                if (currentRow != 0) System.out.println();
                currentRow = row;
            }
            System.out.print(seat + " ");
        }
        System.out.println("\n");
    }

    public String getName() {
        return name;
    }

    
    private class Seat implements Comparable<Seat> {
        private final String seatNumber;
        private boolean reserved = false;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public boolean reserve() {
            if (!reserved) {
                reserved = true;
                return true;
            }
            return false;
        }

        public boolean isReserved() {
            return reserved;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        @Override
        public String toString() {
            return reserved ? "[" + seatNumber + "*]" : "[" + seatNumber + "]";
        }

        @Override
        public int compareTo(Seat other) {
            return this.seatNumber.compareTo(other.seatNumber);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Seat seat)) return false;
            return seatNumber.equals(seat.seatNumber);
        }

        @Override
        public int hashCode() {
            return seatNumber.hashCode();
        }
    }
}
