package domain;

public enum BookAvailability {
	
//	Defining all the constants
	AVAILABLE,ISSUED;
	
//	Defining all the funtions
	public char getCharValue() {
		int ordinal=this.ordinal();
		switch(ordinal) {
		case 0:
			return 'A';
		case 1:
			return 'I';
		default:
			return 'N';
		}
	}
}
