package enums;

public enum StatusRecords {
	Issued("I"), Returned("R");

	String statusRecords;

	StatusRecords(String statusRecords) {
		this.statusRecords = statusRecords;
	}

	public String getStatusRecords() {
		return statusRecords;
	}

	public void setStatusRecords(String statusRecords) {
		this.statusRecords = statusRecords;
	}
}
