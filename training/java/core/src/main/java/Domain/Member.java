package Domain;

public class Member {
    int MemberId;
    String Name;
    String Email;
    String Mobile;
    Gender gender;
    String Address;
	public Member(int id, String name, String email, String mobile, Gender gender, String address) {
		MemberId = id;
		Name = name;
		Email = email;
		Mobile = mobile;
		this.gender = gender;
		Address = address;
	}
	public Member() {
		
	}
	public int getId() {
		return MemberId;
	}
	public void setId(int id) {
		MemberId = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	@Override
	public String toString() {
		return "Member [Id=" + MemberId + ", Name=" + Name + ", Email=" + Email + ", Mobile=" + Mobile + ", gender=" + gender
				+ ", Address=" + Address + "]";
	}
	
	
}
