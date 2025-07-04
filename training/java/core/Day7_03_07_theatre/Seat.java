package Day7_03_07_theatre;

import java.util.Objects;

public class Seat implements Comparable<Seat> {
    private char row;
    private int seatNumber;
    private String seatId;
    private boolean reserved;

    public Seat(char row, int seatNumber, String seatId) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.seatId = seatId;
        this.reserved = false;
    }

    public char getRow() {
        return row;
    }

    public String getSeatId() {
        return seatId;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public int compareTo(Seat o) {
        return this.seatId.compareTo(o.seatId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seat)) return false;
        Seat seat = (Seat) o;
        return Objects.equals(seatId, seat.seatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatId);
    }
}
