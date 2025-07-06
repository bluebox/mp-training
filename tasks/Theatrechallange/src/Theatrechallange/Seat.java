package Theatrechallange;
public class Seat implements Comparable<Seat> {
    private final char row;
    private final int number;
    private boolean reserved = false;

    public Seat(char row, int number) {
        this.row = row;
        this.number = number;
    }

    public char getRow() {
        return row;
    }

    public int getNumber() {
        return number;
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

    public String getLabel() {
        return row + String.format("%02d", number);
    }

    public static Seat fromLabel(String label) {
        char row = label.charAt(0);
        int number = Integer.parseInt(label.substring(1));
        return new Seat(row, number);
    }

    @Override
    public int compareTo(Seat other) {
        int rowCompare = Character.compare(this.row, other.row);
        return rowCompare != 0 ? rowCompare : Integer.compare(this.number, other.number);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Seat)) return false;
        Seat other = (Seat) obj;
        return this.row == other.row && this.number == other.number;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(row, number);
    }
}
