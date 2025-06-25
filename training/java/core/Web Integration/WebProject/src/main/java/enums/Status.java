package enums;

public enum Status {
	Active("A"), Inactive("I");

	String status;

	Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
