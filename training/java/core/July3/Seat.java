package July3;

import java.util.Objects;

public class Seat implements Comparable <Seat> {
	 	private char row;
	    private int number;
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
	        return row + String.format("%03d", number);
	    }

	    public static Seat fromLabel(String label) {
	        char row = label.charAt(0);
	        int number = Integer.parseInt(label.substring(1));
	        return new Seat(row, number);
	    }

		@Override
		public int hashCode() {
			return Objects.hash(number, row);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Seat other = (Seat) obj;
			return row == other.row && number == other.number;
		}

		@Override
		public int compareTo(Seat o) {
			int rowCompare = Character.compare(this.row, o.row);
			if(rowCompare != 0) return rowCompare;
			return Integer.compare(this.number, o.number);
		}
	    
}
