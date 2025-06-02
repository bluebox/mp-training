package Gym;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Member extends Person{
    public String membershipPlan;
    public String membershipType;
    public String membershipFee;
    public String membershipDuration;
    public String startDate;
    public String endDate;
    private static int id=1;
    private String memberId;
    private long remainingDays;

    public void updatePlan(String membershipPlan){
        this.membershipPlan = membershipPlan;
    }

     public void updateName(String name){
        this.Name = name;
    }

    public long calculateRemainingDays(String end){
        LocalDate start=LocalDate.now();
        LocalDate end1=LocalDate.parse(end, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return ChronoUnit.DAYS.between(start,end1);
    }
    public Member(String name, String age, String gender, String membershipPlan, String startDate, String endDate) {
        super(name, age, gender);
        this.membershipPlan=membershipPlan;
        String[] membershipPlanArray = membershipPlan.split(" / ");
        this.membershipFee=membershipPlanArray[2];
        this.membershipType = membershipPlanArray[0];
        this.membershipDuration=membershipPlanArray[1];
        this.startDate=startDate;
        this.endDate=endDate;
        this.memberId=name.substring(0,3)+id++;
        this.remainingDays=calculateRemainingDays(endDate);
    }

    public String getMember(){
        String details="-".repeat(20)+"\nMember Id :"+memberId+"\nMember Name :"+getName()+"\nAge :"+getAge()+"\nGender :"+getGender()+"\nMembership Plan :"
        +membershipPlan+"\nDuration :"+startDate+" - "+endDate+"\n"+"-".repeat(20);
        return details;
    }


    // public long getRemainingDays(){
    //     return remainingDays;
    // }

    public String getMemberId(){
        return memberId;
    }
    // public String getName(){ return Name;}
    public void setAge(String age){ this.Age=age;}
    public String showMembersDetails(){
        return ""+memberId+"  "+Name+"  "+Age+"  "+Gender+"  ["+membershipPlan+"]  ["+startDate+" to "+endDate+"]  "+remainingDays+"-days left";
    }
    
}
