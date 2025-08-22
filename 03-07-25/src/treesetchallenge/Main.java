package treesetchallenge;

public class Main {
    public static void main(String[] args) {
        Theatre theatre = new Theatre("PVR Cinemas", 5, 8);

        System.out.println("Initial seat map:");
        theatre.printSeatMap();

        System.out.println("\nReserving seat A005...");
        theatre.reserveSeat("A005");

        System.out.println("\nTrying to reserve A005 again...");
        theatre.reserveSeat("A005");

        System.out.println("\nUpdated seat map:");
        theatre.printSeatMap();
    }
}