package Model;

public class Member {
    private int id;
    private String name;
    private String joinDate;
    private String membershipType;

    public Member() {}

    public Member(int id, String name, String joinDate, String membershipType) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.membershipType = membershipType;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getJoinDate() { return joinDate; }
    public void setJoinDate(String joinDate) { this.joinDate = joinDate; }

    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }
}
