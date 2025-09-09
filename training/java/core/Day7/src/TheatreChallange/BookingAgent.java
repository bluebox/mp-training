package TheatreChallange;

public class BookingAgent {

    public static void main(String[] args) {

        Theatre myTheatre = new Theatre("Theatre", 27, 100);

        myTheatre.printSeatMap();

        bookSingleSeat(myTheatre, 'A', 3);
//        bookSingleSeat(myTheatre, 'A', 3); 
//        bookSingleSeat(myTheatre, 'B', 1);
//        bookSingleSeat(myTheatre, 'B', 11);
//        bookSingleSeat(myTheatre, 'M', 1); 

        bookGroupSeats(myTheatre, 4, 'B', 3, 10);
//        bookGroupSeats(myTheatre, 6, 'B', 'C', 3, 10);
//        bookGroupSeats(myTheatre, 4, 'B', 1, 10);
//        bookGroupSeats(myTheatre, 4, 'B', 'C', 1, 10);
//        bookGroupSeats(myTheatre, 1, 'B', 'C', 1, 10);
//        bookGroupSeats(myTheatre, 4, 'M', 'Z', 1, 10);
//        bookGroupSeats(myTheatre, 10, 'A', 'E', 1, 10);
    }

    private static void bookSingleSeat(Theatre theatre, char row, int seatNo) {
        String seat = theatre.reserveSeat(row, seatNo);
        if (seat != null) {
            System.out.println(" Seat booked: " + seat);
            theatre.printSeatMap();
        } else {
            System.out.println(" Cannot book seat: " + row + seatNo);
        }
     
    }

    private static void bookGroupSeats(Theatre theatre, int howMany, char row,
                                       int startSeat, int endSeat) {
        bookGroupSeats(theatre, howMany, row, row, startSeat, endSeat);
    }

    private static void bookGroupSeats(Theatre theatre, int howMany,
                                       char fromRow, char toRow,
                                       int startSeat, int endSeat) {

        var seats = theatre.reserveSeats(howMany, fromRow, toRow, startSeat, endSeat);

        if (seats != null) {
            System.out.println(" Bulk seats booked: " + seats);
            theatre.printSeatMap();
        } else {
            System.out.println(" No bulk seats found from row " + fromRow + " to " + toRow);
        }
    }
}