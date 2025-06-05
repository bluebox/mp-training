package treeset;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Theater {
	
	private String theaterName;
	private int rows = 26;
	private int seatsPerRow;
	private Set<Seats> seats;
	public Theater(int seatsPerRow)
	{
		this.seatsPerRow = seatsPerRow;
		
		seats = new TreeSet<Theater.Seats>((s1,s2)->{
			int rowComp = s1.rowChar.compareTo(s2.rowChar);
			if(rowComp == 0)
			{
				rowComp = Integer.compare(s1.rowNum, s2.rowNum);
			}
			return rowComp;
		});
		
		
		int row = 'A';
		for(int i = 0;i<rows;i++)
		{
			char rowChar = (char)('A'+i);
			for(int j = 0;j<seatsPerRow;j++)
			{
				seats.add(new Seats(String.valueOf(rowChar),j+1));
			}
		}
		
		
		
		
	}
	public void display() {
        Iterator<Seats> itr = seats.iterator();
        int c = 0;
        while (itr.hasNext()) {
            Seats s = itr.next();
            System.out.print(" " + s.rowChar + s.rowNum + " " + (s.isReserved ? "*   " : "-   "));
            c++;
            if (c % seatsPerRow == 0) {
                System.out.println();
            }
        }
    }
	
	
	
	public boolean bookSeats(int noOfSeats, String rows)
	{
		String[]row = rows.split("-");
		String start = "";
		String end = "";
		if(row.length == 1)
		{
			start = row[0];
			end = row[0];
		}else
		{
			start = row[0];
			end = row[1];
		}
		
		Iterator<Seats>itr = seats.iterator();
		for(int i = 0;i<26;i++)
		{
			char c = (char)('A'+i);
			
			
				int req = noOfSeats;
				int x = 0,y = 0;
				ArrayList<Seats> onHold = new ArrayList<>();
				for(int j = 0;j<seatsPerRow;j++)
				{
					if((c>=start.charAt(0))&&(c<=end.charAt(0)))
					{
						if(req == 0)
						{
							System.out.println("Seats booked at row :: "+c+" from "+x+" to "+y);
							int step = y-x+1;
							while(step > 0)
							{
								onHold.get(step-1).isReserved = true;
								step-=1;
							}
							return true;
						}
						Seats s = itr.next();
						if(s.isReserved == false)
						{
							onHold.add(s);
							req -= 1;
							y=j;
						}else
						{
							onHold.clear();
							req = noOfSeats;
							x = y;
						}
					}else
					{
						itr.next();
					}
				}
		}
		
		
		return false;
	}

	
	public static void main(String[] args) {
		Theater t = new Theater(7);
		t.display();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of tickets for booking :: ");
		int noOfSeats = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the your prefered rows in the format(A-Z):: ");
		String rows = sc.nextLine();
		
		if(!t.bookSeats(noOfSeats,rows))
		{
			System.out.println("No contigious seats available");
		}
		
		t.display();
		
	}
	
	private class Seats 
	{
		String rowChar;
		int rowNum;
		boolean isReserved = false;
		public Seats(String rowChar, int rowNum)
		{
			this.rowChar = rowChar;
			this.rowNum = rowNum;
		}
	}
	
	
	
	
}
