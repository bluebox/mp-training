package TreeSet;

import java.util.TreeSet;

public class Theater {
	static class Seat{
		private char row;
		private int seatNo;
		private boolean reserved;
		
		public Seat(char row, int seatNo, boolean reserved) {
			this.row=row;
			this.seatNo=seatNo;
			this.reserved=reserved;
		}
		
		public Seat(char row, int seatNo) {
			this.row=row;
			this.seatNo=seatNo;
			this.reserved=false;
		}

		public char getRow() {
			return row;
		}

		public void setRow(char row) {
			this.row = row;
		}

		public int getSeatNo() {
			return seatNo;
		}

		public void setSeatNo(int seatNo) {
			this.seatNo = seatNo;
		}

		public boolean isReserved() {
			return reserved;
		}

		public void setReserved(boolean reserved) {
			this.reserved = reserved;
		}
		
		@Override
		public String toString() {
			return this.getRow()+"%03d".formatted(this.getSeatNo());
		}
		
	}
	
	private String name;
	private int nSeats;
	private TreeSet<Theater.Seat> seats;
	
	public Theater(String name, int nSeats, TreeSet<Theater.Seat> seats) {
		this.name=name;
		this.nSeats=nSeats;
		this.seats=seats;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getnSeats() {
		return nSeats;
	}

	public void setnSeats(int nSeats) {
		this.nSeats = nSeats;
	}

	public TreeSet<Theater.Seat> getSeats() {
		return seats;
	}

	public void setSeats(TreeSet<Theater.Seat> seats) {
		this.seats = seats;
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder("");
		int i=1;
		for(Theater.Seat s:this.getSeats()) {
			if(s.isReserved()) {
				sb.append(s+"( * ) ");
			}
			else {
				sb.append(s+"      ");
			}
			if(i%10==0) {
				sb.append("\n");
			}
			i++;
		}
		return sb.toString();
	}
}
