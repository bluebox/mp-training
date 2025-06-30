 public class MembershipPlan{
       private String planName;
       private int durationMonths;
       private int fee; 
       public MembershipPlan( String planName,int durationMonths,int fee){
              
              this.planName=planName;
              this.durationMonths=durationMonths;
              this.fee=fee;
       }
       public int getDurationMonths() {
           return durationMonths;
       }
       public int getFee() {
           return fee;
       }
       public String getPlanName() {
           return planName;
       }
       public void planDetails(){
        System.out.println("plan name is "+this.planName);
        System.out.println("plan duration is "+this.durationMonths);
        System.out.println("plan fee is "+this.fee);
       }
       
       
        



        
 }