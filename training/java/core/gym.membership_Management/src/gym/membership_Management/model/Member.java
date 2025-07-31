package gym.membership_Management.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Member extends Person{
	private int membershipId;
	private MembershipPlan plan;
	private MemberStatus status;
    private String removalReason;
    private String dateOfJoin;
	private String lastUpdatedDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    
	public Member(String name, int age, MembershipPlan subscribedPlan) {
        super(name, age);
        this.plan = subscribedPlan;
        this.status = MemberStatus.ACTIVE;
        this.removalReason = null;
        this.dateOfJoin=LocalDateTime.now().format(formatter);
        this.lastUpdatedDate=LocalDateTime.now().format(formatter);
    }
    
	public Member(int membershipId, String name, int age, MembershipPlan subscribedPlan, MemberStatus status,String removalReason,String date_Of_Join,String last_Date) {
        super(name, age);
        this.membershipId = membershipId;
        this.plan = subscribedPlan;
        this.status = status;
        this.removalReason = removalReason;
        this.dateOfJoin=date_Of_Join;
        this.lastUpdatedDate=last_Date;
    }
	 
    public void setMembershipId(int membershipId) {
		this.membershipId = membershipId;
	}
    
	public int getMembershipId() {
		return membershipId;
	}

	public MembershipPlan getPlan() {
		return plan;
	}

	public void setPlan(MembershipPlan plan) {
		this.plan = plan;
	}
	
	public MemberStatus getStatus() {
		return status;
	}
	
	public void setStatus(MemberStatus status) {
        this.status = status;
    }
	
	public String getRemovalReason() {
		return removalReason;
    }
	
	public void setRemovalReason(String removalReason) {
        this.removalReason = removalReason;
    }
	
	public String getDateOfJoin() {
		return dateOfJoin;
	}

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	
	@Override
	public void showDetails() {
		System.out.println("ID: "+this.membershipId+" - [name: "+this.getName()+", age: "+this.getAge()+", enrolledPlan: "+plan.getNameOfPlan()+" ]");
	}
	
}
