package dev.tulasidhar.lms.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int member_Id;
	private String member_Name;
	private String email;
	private String mobile_No;
	private char gender;
	private String address;
	
	
	
	public Member(String member_Name, String email, String mobile_No, char gender,String address) {
		this(0,member_Name,email,mobile_No,gender,address);
	}	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
		if (obj == null || getClass() != obj.getClass()) 
			return false;
		Member member = (Member) obj;
		return this.member_Id == member.member_Id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(member_Id);
	}
}
