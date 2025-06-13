public class MemberShip {
     private String avaiblePlans;
     private int duration;
     private int fee;
     public MemberShip(String plan,int duration) {
    	 this.avaiblePlans=plan;
    	 this.duration=duration;
    	 if(plan=="basic")this.fee=duration*1000;
    	 else if(plan=="gold")this.fee=duration*1500;
    	 else this.fee=duration*2000;
     }
     public String getPlan() {
    	 return this.avaiblePlans;
     }
     public int getDuration() {
    	 return this.duration;
     }
     public int getTotalFee() {
    	 return this.fee;
     }
     @Override
     public String toString() {
		return avaiblePlans.toUpperCase()+" plan  total fee "+this.getTotalFee();
     }
}
