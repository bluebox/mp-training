package TreeSetChallenge;

import java.util.*;

public class Theatre {
    private String name;
    private int seatsPerRow;
    private TreeSet<Seat> seats;

    public Theatre(String name, int numRows, int seatsPerRow) {
        this.name = name;
        this.seatsPerRow = seatsPerRow;
        this.seats = new TreeSet<>();

        for (char row = 'A'; row < 'A' + numRows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                seats.add(new Seat(row, seatNum));
            }
        }
    }

    
    public class Seat implements Comparable<Seat> {
        private final String seatNumber;
        private boolean reserved;

        public Seat(char row, int seatNumber) {
            this.seatNumber = row + String.format("%03d", seatNumber);
            this.reserved = false;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public boolean isReserved() {
            return reserved;
        }

        public boolean reserve() {
            if (!reserved) {
                reserved = true;
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Seat other) {
            return this.seatNumber.compareTo(other.seatNumber);
        }

        @Override
        public String toString() {
            return seatNumber + (reserved ? "[R]" : "[ ]");
        }
    }

    
    public boolean reserveSeat(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat.reserve();
            }
        }
        return false;
    }

   
    public void printSeatMap() {
        char currentRow = 0;
        for (Seat seat : seats) {
            if (currentRow != seat.seatNumber.charAt(0)) {
                if (currentRow != 0) System.out.println();
                currentRow = seat.seatNumber.charAt(0);
                System.out.print(currentRow + ": ");
            }
            System.out.print(seat + " ");
        }
        System.out.println("\n");
    }

    
    public List<String> reserveContiguousSeats(int count, char rowStart, char rowEnd, int seatStart, int seatEnd) {
        List<String> reservedSeats = new ArrayList<>();

        for (char row = rowStart; row <= rowEnd; row++) {
            int maxStart = seatEnd - count + 1;
            for (int start = seatStart; start <= maxStart; start++) {
                List<Seat> contiguous = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    String seatNumber = row + String.format("%03d", start + i);
                    Seat foundSeat = getSeatByNumber(seatNumber);
                    if (foundSeat == null || foundSeat.isReserved()) {
                        contiguous.clear();  // reset if one seat is not available
                        break;
                    }
                    contiguous.add(foundSeat);
                }

                if (contiguous.size() == count) {
                    contiguous.forEach(Seat::reserve);
                    contiguous.forEach(s -> reservedSeats.add(s.getSeatNumber()));
                    return reservedSeats; // reserve first valid block found
                }
            }
        }
        return reservedSeats;
    }

    private Seat getSeatByNumber(String number) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(number)) {
                return seat;
            }
        }
        return null;
    }
}

