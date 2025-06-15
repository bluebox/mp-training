package enums;

public enum Availability {
	Available("A"), Issued("I");

	private final String code;

	Availability(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	public static Availability fromCode(String code) {
		
		try {
			for(Availability a:Availability.values()) {
				if(a.code.equalsIgnoreCase(code))
					return a;
			}
		}catch(IllegalArgumentException e) {
			System.out.println("problme occcured due to invalid entry.!!");
			e.printStackTrace();
		}
		return null;
		
	}
}