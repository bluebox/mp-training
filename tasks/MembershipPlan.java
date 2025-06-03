public class MembershipPlan {
    public enum Plan {
        BASIC,
        GOLD,
        PREMIUM
    }

    private Plan plan;
    private int durationMonths = -1;
    private double fee;

    public MembershipPlan(Plan plan, int durationMonths) {
        this.plan = plan;
        this.durationMonths = durationMonths;
        this.fee = getFee();
    }

    public Plan getPlan() {
        return plan;
    }

    public String getDurationMonths() {
        return durationMonths + "months";
    }

    public double getFee() {
        if(durationMonths>=6){
            return durationMonths * getMonthlyFee() * 0.9;
        } else if (durationMonths>=3) {
            return durationMonths * getMonthlyFee() * 0.95;
        }
        return durationMonths * getMonthlyFee();
    }
    public double getMonthlyFee(){
         return switch(plan){
             case GOLD ->  1200;
             case PREMIUM ->1000;
             case BASIC -> 700;
             default -> {
                 System.out.println("invalid value");
                 yield 0.0;
             }
        };
    }
}
