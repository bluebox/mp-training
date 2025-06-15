package gymSystem;

//import java.util.ArrayList;

public class MembershipPlan {
    private String plan;
    private int duration;
    private int fee;
    public MembershipPlan(String plan,int duration,int fee){
        this.plan=plan;
        this.duration=duration;
        this.fee=fee;
    }
    public MembershipPlan() {
    }

    public String getPlan(){
        return this.plan;
    }
    public int getDuration(){
        return duration;
    }
    public int getFee(){
        return fee;
    }
   public String toString(){
        return "Plan name : "+plan+"\nDuration : "+duration+"\nFee : "+fee;
   }


}

	

