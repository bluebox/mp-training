package enums;

public enum Gender {
	Male("M"), Female("F"), Other("O");

	String gender;

	Gender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
