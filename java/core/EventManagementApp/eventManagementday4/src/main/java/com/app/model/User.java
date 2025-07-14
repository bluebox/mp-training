package com.app.model;
import com.app.enums.Gender;
import com.app.enums.Status;
import lombok.Data;

@Data
public class User {
    private int user_id;
    private String name;
    private String phn_number;
    private String email;
    private String role;
    private Gender gender;   // Enum: MALE, FEMALE, OTHER
    private Status status;   // Enum: ACTIVE, INACTIVE
    private String dept;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhn_number() {
		return phn_number;
	}
	public void setPhn_number(String phn_number) {
		this.phn_number = phn_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
}
