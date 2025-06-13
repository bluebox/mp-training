package application;

public enum Availability {
	Available('A'), Issued('I');

	private char code;

	Availability(char code) {
		this.code = code;
	}
	
	public char getCode() {
		return code;
	}
	public static Availability fromCode(char code) {
		
		try {
			for(Availability a:Availability.values()) {
				if(a.code==code)
					return a;
			}
		}catch(IllegalArgumentException e) {
			System.out.println("problme occcured due to invalid entry.!!");
			e.printStackTrace();
		}
		return null;
		
	}
}
