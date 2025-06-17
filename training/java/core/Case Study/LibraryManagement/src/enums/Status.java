package enums;

public enum Status {
	Active('A'),Inactive('I');
	char status;
	Status(char status) {
		this.status=status;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
}
