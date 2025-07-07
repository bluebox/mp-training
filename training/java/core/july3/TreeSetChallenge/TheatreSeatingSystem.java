package dev.tulasidhar.july3.TreeSetChallenge;

public class TheatreSeatingSystem {
  public static void main(String[] args) {
      Theatre theatre = new Theatre("Grand Theatre", 5, 10);
      
      System.out.println("Theatre: " + theatre.getTheatreName());
      System.out.println("Total seats: " + theatre.getTotalSeats());
      System.out.println("Seats per row: " + theatre.getSeatsPerRow());
      System.out.println("Number of rows: " + theatre.getNumRows());
      
      //show initial seat map
      theatre.printSeatMap();
      

      System.out.println("\n=== Booking Agent Test ===");
      System.out.println("Reserving A005: " + theatre.reserveSeat("A005"));
      System.out.println("Reserving B010: " + theatre.reserveSeat("B010"));
      System.out.println("Reserving C001: " + theatre.reserveSeat("C001"));
      System.out.println("Reserving A005 again: " + theatre.reserveSeat("A005")); 
      
      theatre.printSeatMap();

      System.out.println("\n=== Cancel Booking ===");
      System.out.println("Cancelling A005: " + theatre.cancelReservation("A005"));
      

      theatre.printSeatMap();
  }
}