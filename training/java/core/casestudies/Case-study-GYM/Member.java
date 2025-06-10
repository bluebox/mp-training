public class Member extends Person{
    private int gymId;
    private String plan;
    private int duration;
    private int planCost;
    private int totalFee;
    public Member(String name, int age, String gender, int gymId, String  plan, int duration) {
        super(name, age, gender);
        this.gymId = gymId;
        this.plan = plan;
        this.duration = duration;
        this.planCost=calculateMonthlyFee(plan);
        this.totalFee= calculateTotalFee(plan,duration);
    }
    public Member(){
        // super();
    }
    public int getGymId() {
        return gymId;
    }
    public String getPlan() {
        return plan;
    }
    public int getDuration() {
        return duration;
    }
    public int getFee() {
        return totalFee;
    }
    public int calculateTotalFee(String plan,int duration){
        if(plan.equalsIgnoreCase("Basic")){
            return duration*BasicPlan.MonthlyFee;
        }else if(plan.equalsIgnoreCase("gold")){
            return duration*GoldPlan.MonthlyFee;
        }else if(plan.equalsIgnoreCase("Premium")){
            return duration*PremiumPlan.MonthlyFee;
        }
        return -1;
    }
    
    public int getPlanCost() {
        return planCost;
    }
    public int getTotalFee() {
        return totalFee;
    }
    public int calculateMonthlyFee(String plan){
        if(plan.equalsIgnoreCase("Basic")){
            return BasicPlan.MonthlyFee;
        }else if(plan.equalsIgnoreCase("gold")){
            return GoldPlan.MonthlyFee;
        }else if(plan.equalsIgnoreCase("Premium")){
            return PremiumPlan.MonthlyFee;
        }        
        return -1;
    }

}
