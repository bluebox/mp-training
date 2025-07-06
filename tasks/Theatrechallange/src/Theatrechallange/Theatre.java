package Theatrechallange;
import java.util.*;

public class Theatre {
    private final String theatreName;
    private final int seatsPerRow;
    private final TreeSet<Seat> seats = new TreeSet<>();

    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        if (numRows > 26) {
            throw new IllegalArgumentException("Number of rows cannot exceed 26");
        }
        this.theatreName = theatreName;
        this.seatsPerRow = seatsPerRow;
        createSeats(numRows, seatsPerRow);
    }

    private void createSeats(int numRows, int seatsPerRow) {
        for (char row = 'A'; row < 'A' + numRows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                seats.add(new Seat(row, seatNum));
            }
        }
    }

    public boolean reserveSeat(String seatLabel) {
        Seat requestedSeat = Seat.fromLabel(seatLabel);
        Seat foundSeat = seats.floor(requestedSeat);
        if (foundSeat != null && foundSeat.equals(requestedSeat)) {
            return foundSeat.reserve();
        }
        return false;
    }

    public void printSeatMap() {
        char currentRow = 0;
        for (Seat seat : seats) {
            if (seat.getRow() != currentRow) {
                currentRow = seat.getRow();
                System.out.println();
                System.out.print(currentRow + ": ");
            }
            System.out.print(seat.isReserved() ? "(*) " : String.format("[%02d] ", seat.getNumber()));
        }
        System.out.println();
    }
}
