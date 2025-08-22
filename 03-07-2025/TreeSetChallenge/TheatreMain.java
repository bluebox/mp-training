package TreeSetChallenge;

public class TheatreMain {
    public static void main(String[] args) {
        Theatre theatre = new Theatre("Cineplex", 5, 10); 

        theatre.reserveSeat("A003");
        theatre.reserveSeat("C007");

        theatre.printSeatMap();

        
        var reserved = theatre.reserveContiguousSeats(3, 'A', 'C', 2, 8);
        System.out.println("Bonus reserved seats: " + reserved);

        theatre.printSeatMap();
    }
}

