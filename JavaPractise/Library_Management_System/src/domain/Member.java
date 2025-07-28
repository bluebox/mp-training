package domain;

public class Member {
	private int memberId;
    private String name;
    private String email;
    private String mobile;
    private Gender gender; 
    private String address;
    
    public Member()
    {
    	
    }
    public Member(int memberId,String name,String email,String mobile,Gender gender,String address)
    {
    	if (!mobile.matches("\\d{10}")) {
	        throw new IllegalArgumentException("Mobile number must be exactly 10 digits.");
	    }
    	if (!isValidEmail(email)) {
	        throw new IllegalArgumentException("Invalid email format.");
	    }
    	this.memberId=memberId;
    	this.name=name;
    	this.email=email;
    	this.mobile=mobile;
    	this.gender=gender;
    	this.address=address;
    }
	public Member(String name, String email, String mobile, Gender gender, String address) {
		if (!mobile.matches("\\d{10}")) {
	        throw new IllegalArgumentException("Mobile number must be exactly 10 digits.");
	    }
		if (!isValidEmail(email)) {
	        throw new IllegalArgumentException("Invalid email format.");
	    }
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.address = address;
	}
	private boolean isValidEmail(String email) {
	    if (email == null || email.trim().isEmpty()) 
	    	return false;
	    return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}  
}