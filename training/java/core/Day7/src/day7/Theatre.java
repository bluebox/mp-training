package day7;

import java.util.*;

public class Theatre {
    private String name;
    private int seatsPerRow;
    private TreeSet<Seat> seats;

    public Theatre(String name, int numRows, int totalSeats) {
        this.name = name;
        this.seatsPerRow = totalSeats / numRows;
        this.seats = new TreeSet<>();

        for (int row = 0; row < numRows; row++) {
            char rowChar = (char) ('A' + row);
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                seats.add(new Seat(rowChar, seatNum));
            }
        }
    }

    public boolean reserveSeat(String seatCode) {
        Seat dummy = new Seat(seatCode.toUpperCase());
        Seat seat = seats.ceiling(dummy); 
        
        if (seat != null && seat.equals(dummy)) {  
            return seat.reserve(); 
        }
        
        System.out.println("Seat " + seatCode + " is not available.");
        return false; 
    }

    public void printSeatMap() {
        char currentRow = ' ';
        System.out.println(name+" theater");
        for (Seat seat : seats) {
            if (seat.getSeatNumber().charAt(0) != currentRow) {
                if (currentRow != ' ') {
                    System.out.println();
                }
                currentRow = seat.getSeatNumber().charAt(0);
            }
            System.out.print(seat + " ");
        }
        System.out.println();
    }

    private class Seat implements Comparable<Seat> {
        private String seatNumber;
        private boolean reserved = false;

        public Seat(char row, int seatInRow) {
            this.seatNumber = String.format("%c%03d", row, seatInRow);
        }

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public boolean reserve() {
            if (!reserved) {
                reserved = true;
                System.out.println("Seat " + seatNumber + " reserved.");
                return true;
            } else {
                System.out.println("Seat " + seatNumber + " is already reserved.");
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        @Override
        public int compareTo(Seat other) {
            return this.seatNumber.compareTo(other.seatNumber);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Seat) {
                return this.seatNumber.equals(((Seat) obj).seatNumber);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return seatNumber.hashCode();
        }

        @Override
        public String toString() {
            return reserved ? "[" + seatNumber + "*]" : "[" + seatNumber + "]";
        }
    }
}
