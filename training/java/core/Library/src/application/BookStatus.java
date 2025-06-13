package application;

public enum BookStatus {
	Active('A'), Inactive('I');

	private char code;

	BookStatus(char code) {
		this.code = code;
	}

	public char getCode() {
		return code;
	}
	public static BookStatus fromCode(char code) {
		
		try {
			for(BookStatus s:BookStatus.values()) {
				if(s.code==code)
					return s;
			}
		}catch(IllegalArgumentException e) {
			System.out.println("problme occcured due to invalid entry.!!");
			e.printStackTrace();
		}
		return null;
		
	}

}
