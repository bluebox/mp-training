package updatedbookselling.bookcatalog.domain;

public class Member {
	private Integer memberId;
    private String name;
    private String roles;
    private String memberPassword;
    
    public Member() {}

	public Member(Integer memberId, String name, String roles, String memberPassword) {
		this.memberId = memberId;
		this.name = name;
		this.roles = roles;
		this.memberPassword = memberPassword;
	}
	
	public Member(String name, String roles, String memberPassword) {
		this.name = name;
		this.roles = roles;
		this.memberPassword = memberPassword;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", name=" + name + ", roles=" + roles + ", memberPassword="
				+ memberPassword + "]";
	}
    
	
    
    

    // Getters and Setters


}
