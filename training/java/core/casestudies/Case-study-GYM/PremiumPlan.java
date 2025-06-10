public class PremiumPlan extends Plan {
     private static final String PREMIUM_FEATURES=    
        "Access to Strength Training\n"+
        "Access to Cardio Training\n"+
        "Locker room Access\n"+
        "24/7 gym access\n"+
        "Unlimited personal training\n"+
        "Access to Zumba Classes\n"+
        "10% Discount on MedPlus Mart for Suppliments\n"+
        "Access to Game Room\n";    
    public static int MonthlyFee=2000;
    public PremiumPlan(int duration){
        super("Premium",duration,MonthlyFee,PREMIUM_FEATURES);
    }
}