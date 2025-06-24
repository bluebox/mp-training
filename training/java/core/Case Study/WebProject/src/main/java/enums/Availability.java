package enums;

public enum Availability {

	Available("A"), Issued("I");

	private String c;

	Availability(String c) {
		this.c = c;
	}

	public String getAvailability() {
		return c;
	}
}