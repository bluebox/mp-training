package domain;
import domain.checking_enum.Gender;


public class Member {
  
	private int memberid;
	private String name;
	private String email;
	private Long mobile;
	private String address;;
	private Gender gender;

	
	public Member( String name, String email,Long mobile,String address,Gender gender) {
		//this.memberid = memberid;
		this.name = name;
		this.email = email;
	    this.mobile = mobile;
		this.address = address;
		this.gender=gender;
		
	}
	public Member( int memberid,String name, String email, Long mobile,String address,Gender gender) {
		this.memberid = memberid;
		this.name = name;
		this.email = email;
	    this.mobile = mobile;
		this.address = address;
		this.gender=gender;
		
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
		
		
				
	

