

package model;

import java.time.LocalDate;

public class Member extends Person {
    private int memberId;  
    private MembershipType membershipType;
    private LocalDate joinedDate;
    private LocalDate modifiedDate;

    public Member() {
        super();
    }

    public Member(String name, int age, String email, Gender gender,
                  LocalDate joinedDate, LocalDate modifiedDate) {
        super(name, age, email, gender);
        this.membershipType = MembershipType.BASIC;
        this.joinedDate = joinedDate;
        this.modifiedDate = modifiedDate;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}

