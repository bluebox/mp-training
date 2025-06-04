package challenges;

import java.io.Serializable;

public class Contact implements Serializable{
	
	private String phoneNumber;
	private String emailId;
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Contact(String phoneNumber, String emailId) {
		super();
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
	}
	
	
	
}
