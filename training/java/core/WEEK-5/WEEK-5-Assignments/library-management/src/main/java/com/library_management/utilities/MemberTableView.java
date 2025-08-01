package com.library_management.utilities;



import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MemberTableView {
	private final SimpleIntegerProperty memberId;
	private final SimpleStringProperty name;
	private final SimpleStringProperty emailId;
	private final SimpleStringProperty mobileNo;
	private final SimpleStringProperty gender;
	private final SimpleStringProperty address;

    public MemberTableView(int id, String name, String emailId, String mobileNo, String gender, String address) {
    	this.memberId=new SimpleIntegerProperty(id);
		this.name=new SimpleStringProperty(name);
		this.emailId=new SimpleStringProperty(emailId);
		this.mobileNo=new SimpleStringProperty(mobileNo);
		this.gender=new SimpleStringProperty(gender);
		this.address=new SimpleStringProperty(address);
    }
    
//    Getters for values
	public int getMemberId() {
		return memberId.get();
	}

	public String getName() {
		return name.get();
	}

	public String getEmailId() {
		return emailId.get();
	}

	public String getMobileNo() {
		return mobileNo.get();
	}

	public String getGender() {
		return gender.get();
	}

	public String getAddress() {
		return address.get();
	}
	
	public SimpleIntegerProperty bookIdProperty() {
		return memberId;
	}

//	getters for properties
	public SimpleStringProperty titleProperty() {
		return name;
	}

	public SimpleStringProperty authorProperty() {
		return emailId;
	}

	public SimpleStringProperty categoryProperty() {
		return mobileNo;
	}

	public SimpleStringProperty statusProperty() {
		return gender;
	}

	public SimpleStringProperty availabilityProperty() {
		return address;
	}
    
}
