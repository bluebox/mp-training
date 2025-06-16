package enums;

public enum Availability {
	
	
	Available('A'),Issued('I');

	private char c;

	Availability(char c) {
		this.c = c;
	}
	public char getChar() {
		return c;
	}
}