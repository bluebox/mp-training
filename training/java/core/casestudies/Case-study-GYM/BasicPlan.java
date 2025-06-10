public class BasicPlan extends Plan{
    private static final String BASIC_FEATURES=    
            "Access to Strength Training Section\n"+
            "No Cardio Access\n"+
            "Locker room Access\n"+
            "Standard Gym Hours (6AM - 10PM)\n";
    public static int MonthlyFee=1000;
    public BasicPlan(int duration){
        super("Basic",duration,MonthlyFee,BASIC_FEATURES);
    }

}
