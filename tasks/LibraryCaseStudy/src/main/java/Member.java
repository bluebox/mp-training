public class Member {
    private int memberId;
    private String name;
    private String email;
    private String mobile; // Changed from int to String
    private char gender; // 'M' or 'F'
    private String address;

    public Member() {}

    public Member(int memberId, String name, String email, String mobile, char gender, String address) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.address = address;
    }

    // Getters and Setters
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "Member [memberId=" + memberId + ", name=" + name + ", email=" + email +
                ", mobile=" + mobile + ", gender=" + gender + ", address=" + address + "]";
    }
}