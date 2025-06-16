package enums;

public enum BookStatus {
	Active("A"), Inactive("I");

	private final String code;

	BookStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	public static BookStatus fromCode(String code) {
		
		try {
			for(BookStatus s:BookStatus.values()) {
				if(s.code.equalsIgnoreCase(code))
					return s;
			}
		}catch(IllegalArgumentException e) {
			System.out.println("problme occcured due to invalid entry.!!");
			e.printStackTrace();
		}
		return null;
		
	}

}
