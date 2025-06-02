package sets;

import java.util.TreeSet;

public class Theater {
	String name;
	int seatsPerRow;
	int totalRows;
	TreeSet<Seat> seats;
	Theater(String name,int seatsPerRow,int totalRows)
	{
		this.name=name;
		this.seatsPerRow=seatsPerRow;
		this.totalRows=totalRows;
		
		seats=new TreeSet<>();
		for(char i='A';i<'A'+totalRows;i++)
		{
			for(int j=1;j<seatsPerRow;j++)
			{
				seats.add(new Seat(i,j));
			}
		}
	}
	
	static class Seat implements Comparable<Seat>{
		String seatId;
		boolean flag;
		public Seat(char row,int no)
		{
			seatId=row+String.format("%03d", no);
		}
		@Override
		public int compareTo(Seat o) {
			// TODO Auto-generated method stub
			return this.seatId.compareTo(o.seatId);
		}
		public boolean isReserved()
		{
			return flag;
		}
		public void reserve()
		{
			this.flag=true;
		}
		public String toString()
		{
			return seatId+(flag?"(.)": "   ");
		}
		
		
	}
	public void printSeatMap()
	{
		int count=1;
		for(Seat seat:seats)
		{
			count++;
			System.out.print(seat);
			if(count==seatsPerRow)
			{
				count=1;
				System.out.println();
				
			}
			
		}
	}
	
	
	

}
