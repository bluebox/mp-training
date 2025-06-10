public abstract class Plan {
    private int duration;
    private String planName;
    private int monthlyfee;
    private String features;
   public Plan(String planName,int duration,int monthlyfee,String features){
        this.planName=planName;
        this.duration=duration;
        this.monthlyfee=monthlyfee;
        this.features=features;
   }
   public int getDuration() {
    return duration;
   }
   public String getPlanName() {
    return planName;
   }
   public int getMonthlyfee() {
    return monthlyfee;
   }
   public String getFeatures() {
    return features;
   }

}
