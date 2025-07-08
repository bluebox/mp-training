package day7;

public class TheatreMain {
	public static void main(String[] args) {
		Theatre t1=new Theatre("Babu", 7, 70);
		t1.printSeatMap();
		t1.reserveSeat("b001");
		t1.reserveSeat("b001");
		t1.printSeatMap();
	}
}
