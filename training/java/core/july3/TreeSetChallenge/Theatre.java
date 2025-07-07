package dev.tulasidhar.july3.TreeSetChallenge;

import java.util.TreeSet;

class Theatre {
    private String theatreName;
    private int seatsPerRow;
    private TreeSet<Seat> seats;
    
    //nested Seat class inside Theatre
    class Seat implements Comparable<Seat> {
        private char row;
        private int seatNumber;
        private boolean reserved;
        
        public Seat(char row, int seatNumber) {
            this.row = row;
            this.seatNumber = seatNumber;
            this.reserved = false;
        }
        
        public char getRow() {
            return row;
        }
        
        public int getSeatNumber() {
            return seatNumber;
        }
        
        public boolean isReserved() {
            return reserved;
        }
        
        public void setReserved(boolean reserved) {
            this.reserved = reserved;
        }
        
        //format like A005 
        @Override
        public String toString() {
            return String.format("%c%03d", row, seatNumber);
        }
        
        //compare by row first then seat number
        @Override
        public int compareTo(Seat other) {
            if (this.row != other.row) {
                return Character.compare(this.row, other.row);
            }
            return Integer.compare(this.seatNumber, other.seatNumber);
        }
    }
    
    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;
        this.seatsPerRow = seatsPerRow;
        this.seats = new TreeSet<>();
        
        for (int row = 0; row < numRows; row++) {
            char rowChar = (char) ('A' + row);
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                seats.add(new Seat(rowChar, seatNum));
            }
        }
    }
    
    public String getTheatreName() {
        return theatreName;
    }
    
    public int getSeatsPerRow() {
        return seatsPerRow;
    }
    
    public int getTotalSeats() {
        return seats.size();
    }
    
    public int getNumRows() {
        return seats.size() / seatsPerRow;
    }
    
    //book a single seat
    public boolean reserveSeat(String seatId) {
        if (seatId.length() != 4) {
            return false;
        }
        
        char row = seatId.charAt(0);
        int seatNumber = 0;
        
        try {
            seatNumber = Integer.parseInt(seatId.substring(1));
        } catch (NumberFormatException e) {
            return false;
        }
        
        //find the seat and reserve it
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getSeatNumber() == seatNumber) {
                if (!seat.isReserved()) {
                    seat.setReserved(true);
                    return true;
                }
                return false; 
            }
        }
        return false; //seat not found
    }
    
    //cancel booking
    public boolean cancelReservation(String seatId) {
        if (seatId.length() != 4) {
            return false;
        }
        
        char row = seatId.charAt(0);
        int seatNumber = 0;
        
        try {
            seatNumber = Integer.parseInt(seatId.substring(1));
        } catch (NumberFormatException e) {
            return false;
        }
        
        //find the seat and cancel reservation
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getSeatNumber() == seatNumber) {
                if (seat.isReserved()) {
                    seat.setReserved(false);
                    return true;
                }
                return false; //not reserved
            }
        }
        return false; //seat not found
    }
    
    
    public void printSeatMap() {
        System.out.println("\n=== " + theatreName + " Seat Map ===");
        System.out.println("Reserved seats marked with *");
        System.out.println();
        
        char currentRow = 'A';
        int count = 0;
        
        for (Seat seat : seats) {
            if (seat.getRow() != currentRow) {
                System.out.println(); 
                currentRow = seat.getRow();
                count = 0;
            }
            
            if (seat.isReserved()) {
                System.out.print(seat.toString() + "* ");
            } else {
                System.out.print(seat.toString() + "  ");
            }
            count++;
        }
        System.out.println();
    }
}