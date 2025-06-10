package sets;

public class TreeSetChallenge {
	public static void main(String[] args) {
		Theater t = new Theater("Jmax", 10, 10);
		System.out.println(t.reserveSeat("A005"));
		System.out.println(t.reserveSeat("C006"));
		System.out.println(t.reserveSeat("D002"));
		System.out.println(t.reserveSeat("A005"));
		System.out.println(t.reserveSeat("E007"));
		System.out.println(t.reserveSeat("F009"));
		System.out.println(t.reserveSeat("A0034"));
		t.printSeatMap();

	}

}
