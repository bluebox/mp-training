package Day7_03_07_theatre;

import java.util.*;

public class Theatre {

    private String name;
    private int seatsPerRow;
    private TreeSet<Seat> seats;

    public Theatre(String name, int numRows, int totalSeats) {
        this.name = name;
        this.seatsPerRow = totalSeats / numRows; 
        this.seats = new TreeSet<>();

        for (int i = 0; i < numRows; i++) {
            char row = (char) ('A' + i);
            for (int j = 1; j <= seatsPerRow; j++) {
                String seatId = row + String.format("%03d", j);
                seats.add(new Seat(row, j, seatId));
            }
        }
    }

    public boolean reserveSeat(String seatId) {
        for (Seat s : seats) {
            if (s.getSeatId().equals(seatId)) {
                if (!s.isReserved()) {
                    s.setReserved(true);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    public boolean reserveMultipleSeats(String startSeatId,int numOfSeats) {
    	boolean gotStartSeat=false;
    	int count=0;
    		for(Seat s:seats) {
    			if (s.getSeatId().equals(startSeatId) && !gotStartSeat) {
    				gotStartSeat=true;
    			}
    			if(gotStartSeat && count<=numOfSeats) {
    				if(s.isReserved()) {
    					count++;
    					return false;}
    			}
    			
    		}
    		if(count<numOfSeats) {
    			return false;
    		}
    		gotStartSeat=false;
    		count=0;
    		for(Seat s:seats) {
    			//boolean gotSeat=false;
    			//if(s.getSeatId().equals(startSeatId)) {got}
    			if (s.getSeatId().equals(startSeatId) && !gotStartSeat) {
    				gotStartSeat=true;
    			}
    			if(gotStartSeat && count<=numOfSeats) {
    				s.setReserved(true);
    				count++;
    				}
    			}

        return true;
    }


    public void printSeatMap() {
        char lastRow = ' ';
        for (Seat s : seats) {
            if (s.getRow() != lastRow) {
                lastRow = s.getRow();
                System.out.println();
                System.out.print(lastRow + ": ");
            }
            if (s.isReserved()) {
                System.out.print("[XXXX] ");
            } else {
                System.out.print("[" + s.getSeatId() + "] ");
            }
        }
        System.out.println();
    }

    
    public static void main(String[] args) {
        Theatre t = new Theatre("Seun's Chill Theatre", 5, 50);
        t.printSeatMap();

        System.out.println("\nTrying to reserve A003...");
        if (t.reserveSeat("A003")) {
            System.out.println("Reserved A003");
        } else {
            System.out.println("Oops, can't reserve A003");
        }
        
        System.out.println("\nTrying to reserve 5 seats from A006...");
        if (t.reserveMultipleSeats("E010",25)) {
            System.out.println("Reserved 5 seats from A006");
        } else {
            System.out.println("Oops, can't reserve 5 seats from A006");
        }
        
        t.printSeatMap();

    }
} 
