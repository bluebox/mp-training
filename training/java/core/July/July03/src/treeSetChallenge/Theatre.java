package treeSetChallenge;

import java.util.TreeSet;

public class Theatre{
	
	private String name;
	private int rowSize;
	private TreeSet<Seat> seats;
	
	public Theatre(String name, int rowSize,int totalSeats) {
		this.name = name;
		if(rowSize > 26)
		{
			rowSize = 26;
		}
		this.rowSize = rowSize;
		seats=new TreeSet<>();
		for(int i=0;i<totalSeats;i++) {
			this.seats.add(new Seat((char)('A'+(i/rowSize)),i+1));
		}
	}

	public void printSeatMap() {
		int c=0;
		for(var seat: seats) {
			System.out.print(seat + (seat.isReserved ? "(R) " :"    "));
			c++;
			if(c==rowSize) {
				c=0;
				System.out.println();
			}
		}
	}

	public String getName() {
		return name;
	}

	class Seat implements Comparable<Seat> {
		private boolean isReserved;
		private String SeatId; 
		public Seat(char row,int seatNumberInRow) {
			this.isReserved = false;
			this.SeatId = row +("%03d").formatted(seatNumberInRow);
		}
		
		public boolean isReserved() {
			return isReserved;
		}
		
		public void setReserved(boolean isReserved) {
			this.isReserved = isReserved;
		}

		public String getSeatId() {
			return SeatId;
		}

		@Override
		public String toString() {
			return SeatId;
		}
		
		@Override
		public int compareTo(Seat o) {
			return (int) this.SeatId.compareTo(o.SeatId);
		}
	}
	public TreeSet<Seat> getSeats(){
		return seats;
	}
	
	public Seat getSeat(char row, int number) {
		Seat seat = new Seat(row,number);
		return seats.contains(seat)? seats.ceiling(seat):null;
	}
	
}
