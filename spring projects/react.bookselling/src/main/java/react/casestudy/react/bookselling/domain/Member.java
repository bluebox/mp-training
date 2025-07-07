package react.casestudy.react.bookselling.domain;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private long mobile;
    private int age;
    private char gender; // expected to be 'M' or 'F'
    private String address;

    // Default constructor
    public Member() {}

    // All-args constructor
    public Member(int memberId, String name, String email, long mobile, int age, char gender, String address) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }
    
    public Member(String name, String email, long mobile, int age, char gender, String address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    // Getters and Setters

    public int getMemberId() {
        return memberId;
    }
    public void setMemberId(int memberId) {
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
    public long getMobile() {
        return mobile;
    }
    public void setMobile(long mobile) {
        this.mobile = mobile;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        if(gender != 'M' && gender != 'F'){
            throw new IllegalArgumentException("Gender must be 'M' or 'F'");
        }
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        return "Member [memberId=" + memberId + ", name=" + name + ", email=" + email +
                ", mobile=" + mobile + ", age=" + age + ", gender=" + gender +
                ", address=" + address + "]";
    }
}
