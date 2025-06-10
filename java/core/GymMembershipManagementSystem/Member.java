package GymMembershipManagementSystem;

import java.time.LocalDate;

public class Member extends Person {
	private String memberId;
    private MembershipPlan plan;

    public Member(String memberId, String personName,String personGender,LocalDate personDob,double personWeight,LocalDate personJoinDate) {
        super(personName,personGender,personDob,personWeight,personJoinDate);
        this.memberId = memberId;
    }

    public String getMemberId() 
    { 
    	return memberId; 
    }
    public MembershipPlan getPlan() 
    {
    	return plan; 
    }
    public void assignPlan(MembershipPlan plan) 
    { 
    	this.plan = plan; 
    }

    @Override
    public void showDetails() {
        System.out.println("---------------------------------------------------------------------------------");
        
//        System.out.println("	Member ID	|	Name	|	Gender	|	Date of Birth	|	Age	|	Weight (kgs)	|	Joining Date	|	Membership Plan	|	Duration of Plan|Fee (Rs)	|");

//        System.out.print(memberId+"|"+getName()+"|"+getGender()+"|"+getDOB()+"|"+getAge()+"|"+getWeight()+"|"+getJoinDate()+"|"+plan!=null?plan:"	No membership plan assigned to "+getName()+".Please Add membership plan...");
        System.out.println("Member ID : " + memberId);
        System.out.println("Name : " + getName());
        System.out.println("Gender : " + getGender());
        System.out.println("Date of Birth : " + getDOB());
        System.out.println("Age : " + getAge());

        System.out.println("Weight (kgs) : " + getWeight());
        System.out.println("Member joining Date : "+getJoinDate());
        if (plan != null) {
            System.out.println("Membership Plan: " + plan);
        } 
        else 
        {
            System.out.println("	No membership plan assigned to "+getName()+".Please Add membership plan...");
        }
        
        System.out.println("---------------------------------------------------------------------------------");
    }

}