public class GoldPlan extends Plan{
    
     private static final String GOLD_FEATURES=    
        "Access to Strength Training\n"+
        "Access to Cardio Training\n"+
        "Locker room Access\n"+
        "24/7 gym access\n"+
        "Unlimited personal training\n";       

    ;
    public static int MonthlyFee=1500;
    public GoldPlan(int duration){
        super("Gold",duration,MonthlyFee,GOLD_FEATURES);
    }
}
