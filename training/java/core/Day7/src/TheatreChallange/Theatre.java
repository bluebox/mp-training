package TheatreChallange;

import java.util.*;

public class Theatre {

    private String theatreName;
    private int seatsPerRow;
    private TreeSet<Seat> allSeats;

    public Theatre(String name, int totalRows, int totalSeats) {
        this.theatreName = name;
        this.seatsPerRow = totalSeats / totalRows;
        this.allSeats = new TreeSet<>();

        for (int i = 0; i < totalSeats; i++) {
            char row = (char) ('A' + (i / seatsPerRow));
            int seatNum = (i % seatsPerRow) + 1;
            allSeats.add(new Seat(row, seatNum));
        }
    }

    public void printSeatMap() {
        System.out.println("Seat Map of " + theatreName);
        char currentRow = ' ';
        for (Seat seat : allSeats) {
            if (seat.getRow() != currentRow) {
                currentRow = seat.getRow();
                System.out.print("\nRow " + currentRow + ": ");
            }
            System.out.print(seat);
        }
        System.out.println("\n");
    }

    public String reserveSeat(char row, int seatNo) {
        Seat seat = new Seat(row, seatNo);
        if (allSeats.contains(seat)) {
            for (Seat s : allSeats) {
                if (s.equals(seat) && !s.isReserved()) {
                    s.reserve();
                    return s.toString();
                }
            }
        }
        return null;
    }

    public List<String> reserveSeats(int howMany, char startRow, char endRow, int startSeat, int endSeat) {
        List<String> bookedSeats = new ArrayList<>();

        for (char row = startRow; row <= endRow; row++) {
            List<Seat> found = new ArrayList<>();

            for (int seatNo = startSeat; seatNo <= endSeat; seatNo++) {
                Seat seat = getSeat(row, seatNo);
                if (seat != null && !seat.isReserved()) {
                    found.add(seat);
                    if (found.size() == howMany) break;
                } else {
                    found.clear(); 
                }
            }

            if (found.size() == howMany) {
                for (Seat seat : found) {
                    seat.reserve();
                    bookedSeats.add(seat.toString());
                }
                return bookedSeats;
            }
        }

        return null;
    }

    private Seat getSeat(char row, int number) {
        for (Seat s : allSeats) {
            if (s.getRow() == row && s.getNumber() == number) return s;
        }
        return null;
    }

    class Seat implements Comparable<Seat> {
        private String seatId;
        private boolean reserved;

        public Seat(char row, int number) {
            this.seatId = String.format("%c%03d", row, number).toUpperCase();
        }

        public boolean isReserved() {
            return reserved;
        }

        public void reserve() {
            this.reserved = true;
        }

        public char getRow() {
            return seatId.charAt(0);
        }

        public int getNumber() {
            return Integer.parseInt(seatId.substring(1));
        }

        @Override
        public String toString() {
            return reserved ? seatId + "\u2022 " : seatId + "  ";
        }

        @Override
        public int compareTo(Seat other) {
            return this.seatId.compareTo(other.seatId);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Seat) {
                return this.seatId.equals(((Seat) obj).seatId);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return seatId.hashCode();
        }
    }
}