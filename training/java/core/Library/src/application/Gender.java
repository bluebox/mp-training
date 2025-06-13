package application;

public enum Gender {
	MALE('M'),FEMALE('F');
	private char code;
	Gender(char code) {
		this.code=code;
	}
	public char getCode() {
		return code;
	}
	public static Gender fromCode(char code) {
		
		try {
			for(Gender g:Gender.values()) {
				if(g.code==code)
					return g;
			}
		}catch(IllegalArgumentException e) {
			System.out.println("problme occcured due to invalid entry.!!");
			e.printStackTrace();
		}
		return null;
		
	}
	
}
