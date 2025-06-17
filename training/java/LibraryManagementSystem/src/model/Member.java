package model;

public class Member{
    private Integer memberId;
    private String name;
    private String email;
    private String address;
    private Long mobile;
    private Character gender;

    
    public Member(Integer memberId, String name, String email, Long mobile, Character gender, String address) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", mobile=" + mobile +
                ", gender=" + gender +
                '}';
    }
}

