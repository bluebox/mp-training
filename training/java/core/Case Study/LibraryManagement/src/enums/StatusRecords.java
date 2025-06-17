package enums;

public enum StatusRecords {
	Issued('I'),Returned('R');
	char statusRecords;
	StatusRecords(char statusRecords) {
		this.statusRecords=statusRecords;
	}
	public char getStatusRecords() {
		return statusRecords;
	}
	public void setStatusRecords(char statusRecords) {
		this.statusRecords = statusRecords;
	}
}
